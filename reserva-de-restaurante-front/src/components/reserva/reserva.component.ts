import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ItemService } from '../../app/services/item.service'; 
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule],
  templateUrl: './reserva.component.html',
})
export class ReservaComponent {
  
  reservas: any[] = [];

  reserva = {
    nome_restaurante: '',
    data_reserva: '',
    hora_reserva: '',
    numero_pessoas: 1,
    nome_responsavel: '',
    cpf_responsavel: '',
    telefone_responsavel: '',
    id_restaurante: 0
  };

  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.carregarReservas();
  }

  carregarReservas(): void {
    this.itemService.getReservas().subscribe({
      next: (data) => {
        this.reservas = data;
      },
      error: (error) => {
        console.error('Erro ao carregar reservas:', error);
      }
    });
  }

  onSubmit(): void {
    console.log('Enviando reserva para o backend:', this.reserva);

    this.itemService.postReserva(this.reserva).subscribe({
      next: (response) => {
        console.log('Reserva criada com sucesso:', response);

        // Adiciona a reserva à lista SEM modificar o objeto original
        this.reservas.push({ ...response });

        // Limpa o formulário após o envio bem-sucedido
        this.limparFormulario();
      },
      error: (error) => {
        console.error('Erro ao criar reserva:', error);
      }
    });
  }

  excluirReserva(id: number): void {
    this.itemService.deleteReserva(id).subscribe({
      next: () => {
        console.log('Reserva excluída com sucesso');
        this.reservas = this.reservas.filter(reserva => reserva.id !== id);
      },
      error: (error) => {
        console.error('Erro ao excluir reserva:', error);
      }
    });
  }

  abrirModalAtualizacao(reserva: any): void {
    console.log('Abrindo modal de atualização para reserva:', reserva);
    // Aqui você pode implementar lógica para edição
  }

  atualizarReserva(id: number, dadosAtualizados: any): void {
    this.itemService.updateReserva(id, dadosAtualizados).subscribe({
      next: (response) => {
        console.log('Reserva atualizada com sucesso:', response);

        const index = this.reservas.findIndex(reserva => reserva.id === id);
        if (index !== -1) {
          this.reservas[index] = { ...this.reservas[index], ...dadosAtualizados };
        }
      },
      error: (error) => {
        console.error('Erro ao atualizar reserva:', error);
      }
    });
  }

  limparFormulario(): void {
    this.reserva = {
      nome_restaurante: '',
      data_reserva: '',
      hora_reserva: '',
      numero_pessoas: 1,
      nome_responsavel: '',
      cpf_responsavel: '',
      telefone_responsavel: '',
      id_restaurante: 0
    };
  }
}
