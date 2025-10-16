import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class RestauranteService {
constructor(private http: HttpClient) {}

getRestaurantes(): Observable<any> {
  return this.http.get(`${environment.apiUrl}/restaurante/listar`).pipe(catchError(this.handleError));
}
getRestauranteById(id: number): Observable<any> {
  return this.http.get(`${environment.apiUrl}/restaurante/listar/${id}`).pipe(catchError(this.handleError));
}
postRestaurante(restaurante: any): Observable<any> {
  return this.http.post(`${environment.apiUrl}/restaurante/registrar`, restaurante).pipe(catchError(this.handleError));
}
updateRestaurante(id: number, restaurante: any): Observable<any> {
  return this.http.put(`${environment.apiUrl}/restaurante/atualizar/${id}`, restaurante).pipe(catchError(this.handleError));
}
deleteRestaurante(id: number): Observable<any> {
  return this.http.delete(`${environment.apiUrl}/restaurante/deletar/${id}`).pipe(catchError(this.handleError));
}

private handleError(error: HttpErrorResponse) {
  console.error('Erro na requisição:', error);
  return throwError(() => new Error('Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.'));
} 
}
