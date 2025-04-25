import { Component, OnInit } from '@angular/core';
import { ReservaService } from '../../services/reserva/reserva.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-minha-reserva',
  imports: [RouterModule, CommonModule],
  standalone: true,
  templateUrl: './minha-reserva.component.html',
  styleUrl: './minha-reserva.component.css'
})
export class MinhaReservaComponent implements OnInit {

  myReservations: any[] = [];
  isLoading = false;
  errorMessage = '';

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.carregarReservasDoUsuario();
  }

  carregarReservasDoUsuario(): void {
    this.isLoading = true;

    // Obtém os dados do usuário da sessão
    const userData = this.authService.getCurrentUser();
    if (!userData) {
      this.errorMessage = 'Dados do usuário não encontrados.';
      this.isLoading = false;
      return;
    }

    // Extrai a lista de reservas
    this.myReservations = userData.reservas || [];

    // Verifica se a lista está vazia
    if (this.myReservations.length === 0) {
      this.errorMessage = 'Nenhuma reserva encontrada.';
    }

    this.isLoading = false;
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'confirmado':
        return 'px-3 py-1 rounded-full text-sm font-medium bg-green-100 text-green-800';
      case 'pendente':
        return 'px-3 py-1 rounded-full text-sm font-medium bg-yellow-100 text-yellow-800';
      case 'cancelado':
        return 'px-3 py-1 rounded-full text-sm font-medium bg-red-100 text-red-800';
      default:
        return 'px-3 py-1 rounded-full text-sm font-medium bg-blue-100 text-blue-800';
    }
  }
}
