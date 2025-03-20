import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RestauranteService {
constructor(private http: HttpClient) {}

// Buscar todos os restaurantes
getRestaurantes(): Observable<any> {
  return this.http.get(`${environment.apiUrl}/restaurante`).pipe(catchError(this.handleError));
}

// Buscar restaurante por ID
getRestauranteById(id: number): Observable<any> {
  return this.http.get(`${environment.apiUrl}/restaurante/${id}`).pipe(catchError(this.handleError));
}

// Criar um novo restaurante
postRestaurante(restaurante: any): Observable<any> {
  return this.http.post(`${environment.apiUrl}/restaurante`, restaurante).pipe(catchError(this.handleError));
}

// Atualizar um restaurante existente
updateRestaurante(id: number, restaurante: any): Observable<any> {
  return this.http.put(`${environment.apiUrl}/restaurante/${id}`, restaurante).pipe(catchError(this.handleError));
}

// Deletar um restaurante
deleteRestaurante(id: number): Observable<any> {
  return this.http.delete(`${environment.apiUrl}/restaurante/${id}`).pipe(catchError(this.handleError));
}

// Buscar todos os pratos
getPratos(): Observable<any> {
  return this.http.get(`${environment.apiUrl}/pratosPrincipais`).pipe(catchError(this.handleError));
}

// Buscar prato por ID
getPratoById(id: number): Observable<any> {
  return this.http.get(`${environment.apiUrl}/pratosPrincipais/${id}`).pipe(catchError(this.handleError));
}

// Deletar um prato
deletePrato(id: number): Observable<any> {
  return this.http.delete(`${environment.apiUrl}/pratosPrincipais/${id}`).pipe(catchError(this.handleError));
}

// Método para tratar erros
private handleError(error: HttpErrorResponse) {
  console.error('Erro na requisição:', error);
  return throwError(() => new Error('Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.'));
}
  
}
