import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { ReservaService } from '../../../services/reserva/reserva.service';
import { RestauranteService } from '../../../services/restaurante/restaurante.service'; // <- import do serviço restaurante
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent {
  // Modelo reserva sem nome_restaurante
  reserva = {
    id_restaurante: null as number | null,
    data_reserva: '',
    hora_reserva: '',
    numero_pessoas: 1,
    nome_responsavel: '',
    cpf_responsavel: '',
    telefone_responsavel: ''
  };

  restaurantes: { id: number; nome: string }[] = []; // array de restaurantes para popular o select

  submitted = false;
  erroCarregamentoRestaurantes = '';

  constructor(
    private authService: AuthService,
    private reservaService: ReservaService,
    private restauranteService: RestauranteService // injeta o serviço
  ) {}

  ngOnInit() {
    this.preencherDadosUsuarioLogado();
    this.carregarRestaurantes();
  }

  private preencherDadosUsuarioLogado() {
    const usuarioLogado = this.authService.getCurrentUser();

    if (usuarioLogado) {
      this.reserva.nome_responsavel = usuarioLogado.nome || '';
      this.reserva.cpf_responsavel = usuarioLogado.cpf || '';
      this.reserva.telefone_responsavel = usuarioLogado.telefone || '';
    }
  }

  // Método para buscar restaurantes do backend
  private carregarRestaurantes() {
    this.restauranteService.getRestaurantes().subscribe({
      next: (data: any) => {
        if (Array.isArray(data)) {
          this.restaurantes = data.map((r: any) => ({
            id: r.id,
            nome: r.nome
          }));
        } else {
          this.restaurantes = [];
        }
      },
      error: (error) => {
        this.erroCarregamentoRestaurantes = 'Erro ao carregar restaurantes. Tente novamente mais tarde.';
        console.error('Erro ao carregar restaurantes:', error);
      }
    });
  }

  onSubmit() {
    if (!this.reserva.id_restaurante) {
      alert('Por favor, selecione um restaurante');
      return;
    }

    this.reservaService.postReserva(this.reserva).subscribe({
      next: (response) => {
        console.log('Reserva enviada com sucesso:', response);
        this.submitted = true;
        this.limparFormulario();
      },
      error: (error) => {
        console.error('Erro ao enviar a reserva:', error);
        alert(error.message || 'Erro ao confirmar a reserva. Por favor, tente novamente.');
      }
    });
  }

  private limparFormulario() {
    this.reserva = {
      id_restaurante: null,
      data_reserva: '',
      hora_reserva: '',
      numero_pessoas: 1,
      nome_responsavel: this.reserva.nome_responsavel,
      cpf_responsavel: this.reserva.cpf_responsavel,
      telefone_responsavel: this.reserva.telefone_responsavel
    };
  }
}
