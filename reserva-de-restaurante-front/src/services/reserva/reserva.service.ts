import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private readonly apiUrl = `${environment.apiUrl}/reserva`;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  /**
   * Envia os dados da reserva ao backend.
   * @param reserva Os dados da reserva a serem enviados.
   * @returns Um Observable com a resposta do backend.
   */
  postReserva(reserva: any): Observable<any> {
    const token = this.authService.getToken(); // Obtém o token JWT do AuthService

    if (!token) {
      throw new Error('Token de autenticação não encontrado.');
    }

    return this.http.post(this.apiUrl, reserva, {
      headers: {
        Authorization: `Bearer ${token}` // Adiciona o token JWT ao cabeçalho da requisição
      }
    }).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'Erro ao enviar a reserva.';

        if (error.status === 401) {
          errorMessage = 'Sessão expirada. Por favor, faça login novamente.';
        } else if (error.status === 0) {
          errorMessage = 'Erro de conexão com o servidor.';
        } else if (error.error?.message) {
          errorMessage = error.error.message;
        }

        console.error('Erro ao enviar a reserva:', error);
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}