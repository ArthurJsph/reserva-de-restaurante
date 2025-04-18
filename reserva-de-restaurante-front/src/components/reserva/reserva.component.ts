import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReservaService } from '../../services/reserva/reserva.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule],
  templateUrl: './reserva.component.html',
})
export class ReservaComponent {
  
  submitted = false;

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

  constructor(private reservaService: ReservaService) {}

  onSubmit(): void {
    this.submitted = true;

    this.reservaService.postReserva(this.reserva).subscribe({
      next: () => {
        alert('Reserva criada com sucesso!');
        this.limparFormulario();
        
        // Reset submitted status after 3 seconds
        setTimeout(() => {
          this.submitted = false;
        }, 3000);
      },
      error: (error) => {
        console.error('Erro ao criar reserva:', error);
        this.submitted = false;
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
