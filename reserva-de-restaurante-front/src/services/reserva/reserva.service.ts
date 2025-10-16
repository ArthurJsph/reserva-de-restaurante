import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private readonly apiUrl = `${environment.apiUrl}/reserva/registrar`;

  constructor(
    private http: HttpClient
  ) {}

 postReserva(reserva: any): Observable<any> {
  return this.http.post(this.apiUrl, reserva).pipe(
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