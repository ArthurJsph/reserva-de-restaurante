import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { ReservaService } from '../../services/reserva/reserva.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [FormsModule ],
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent {
  // Modelo de reserva
  reserva = {
    nome_restaurante: '',
    id_restaurante: null,
    data_reserva: '',
    hora_reserva: '',
    numero_pessoas: 1,
    nome_responsavel: '',
    cpf_responsavel: '',
    telefone_responsavel: ''
  };

  submitted = false; // Controla a exibição da mensagem de sucesso

  constructor(
    private authService: AuthService,
    private reservaService: ReservaService
  ) {}

  ngOnInit() {
    this.preencherDadosUsuarioLogado();
  }

  /**
   * Preenche os campos do responsável com os dados do usuário logado.
   */
  private preencherDadosUsuarioLogado() {
    const usuarioLogado = this.authService.getCurrentUser();

    if (usuarioLogado) {
      this.reserva.nome_responsavel = usuarioLogado.nome || '';
      this.reserva.cpf_responsavel = usuarioLogado.cpf || '';
      this.reserva.telefone_responsavel = usuarioLogado.telefone || '';
    }
  }

  /**
   * Envia os dados da reserva ao backend.
   */
  onSubmit() {
    this.reservaService.postReserva(this.reserva).subscribe({
      next: (response) => {
        console.log('Reserva enviada com sucesso:', response);
        this.submitted = true; // Exibe a mensagem de sucesso
        this.limparFormulario(); // Limpa o formulário após o envio
      },
      error: (error) => {
        console.error('Erro ao enviar a reserva:', error);
        alert(error.message || 'Erro ao confirmar a reserva. Por favor, tente novamente.');
      }
    });
  }

  /**
   * Limpa o formulário após o envio bem-sucedido.
   */
  private limparFormulario() {
    this.reserva = {
      nome_restaurante: '',
      id_restaurante: null,
      data_reserva: '',
      hora_reserva: '',
      numero_pessoas: 1,
      nome_responsavel: this.reserva.nome_responsavel, // Mantém os dados do responsável
      cpf_responsavel: this.reserva.cpf_responsavel,   // Mantém os dados do responsável
      telefone_responsavel: this.reserva.telefone_responsavel // Mantém os dados do responsável
    };
  }
}