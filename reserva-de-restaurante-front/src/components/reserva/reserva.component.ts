import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ItemService } from '../../app/services/item.service'; 
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [FormsModule, HttpClientModule,],
  templateUrl: './reserva.component.html',
})
export class ReservaComponent {
  
reservas: any[] = [];


  reserva = {
  nome_restaurante: '',
  data_reserva: '',
  hora_reserva: '',
  numero_pessoas: 0,
  nome_responsavel: '',
  cpf_responsavel: '',
  telefone_responsavel: '',
  id_restaurante: 0
};

constructor(private itemService: ItemService) {}

onSubmit() {
  console.log('Enviando dados para o backend:', this.reserva);

  // Enviar os dados para o backend
  this.itemService.postReserva(this.reserva).subscribe(
    (response) => {
      console.log('Reserva criada com sucesso:', response);

      // Adicionar a nova reserva à lista
      this.reservas.push({ ...this.reserva, id: response.id });

      // Limpar o formulário após o envio bem-sucedido
      this.reserva = {
          nome_restaurante: '',
          data_reserva: '',
          hora_reserva: '',
          numero_pessoas: 0,
          nome_responsavel: '',
          cpf_responsavel: '',
          telefone_responsavel: '',
          id_restaurante: 0
      };
    },
    (error) => {
      console.error('Erro ao criar reserva:', error);
    }
  );
}

// Método para excluir uma reserva
excluirReserva(id: number) {
  this.itemService.deleteReserva(id).subscribe(
    () => {
      console.log('Reserva excluída com sucesso');
      // Remover a reserva da lista local
      this.reservas = this.reservas.filter((reserva) => reserva.id !== id);
    },
    (error) => {
      console.error('Erro ao excluir reserva:', error);
    }
  );
}

// Método para abrir um modal ou formulário de atualização
abrirModalAtualizacao(reserva: any) {
  console.log('Abrindo modal de atualização para reserva:', reserva);
  // Aqui você pode preencher um formulário de atualização ou abrir um modal
  // Exemplo: this.formData = { ...reserva };
}

// Método para atualizar uma reserva
atualizarReserva(id: number, dadosAtualizados: any) {
  this.itemService.updateReserva(id, dadosAtualizados).subscribe(
    (response) => {
      console.log('Reserva atualizada com sucesso:', response);

      // Atualizar a reserva na lista local
      const index = this.reservas.findIndex((reserva) => reserva.id === id);
      if (index !== -1) {
        this.reservas[index] = { ...this.reservas[index], ...dadosAtualizados };
      }
    },
    (error) => {
      console.error('Erro ao atualizar reserva:', error);
    }
  );
}
}
