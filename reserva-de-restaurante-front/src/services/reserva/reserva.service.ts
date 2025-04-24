import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';
@Injectable({
  providedIn: 'root'
})
export class ReservaService {

constructor(private http: HttpClient) {}
  getReservas(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/reserva`).pipe(catchError(this.handleError));
  }
  
  // Método para buscar reserva por ID
  getReservaById(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/reserva/${id}`).pipe(catchError(this.handleError));
  }
  
  // Método para buscar reserva por data
  getReservaByDate(date: string): Observable<any> {
    return this.http.get(`${environment.apiUrl}/reserva?date=${date}`).pipe(catchError(this.handleError));
  }
  
  // Criar uma nova reserva
  postReserva(reserva: any): Observable<any> {
    return this.http.post(`${environment.apiUrl}/reserva`, reserva).pipe(catchError(this.handleError));
  }
  
  // Atualizar uma reserva existente
  updateReserva(id: number, dadosAtualizados: any): Observable<any> {
    return this.http.put(`${environment.apiUrl}/reserva/${id}`, dadosAtualizados).pipe(catchError(this.handleError));
  }
  
  // Deletar uma reserva
  deleteReserva(id: number): Observable<any> {
    return this.http.delete(`${environment.apiUrl}/reserva/${id}`).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    console.error('Erro na requisição:', error);
    return throwError(() => new Error('Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.'));
  }
}
