import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  //URL do backend
private URL = 'http://localhost:8080/api';
//Injeção de dependência do HttpClient
constructor(private http: HttpClient) {}

// Buscar todos os restaurantes
getRestaurantes(): Observable<any> {
  return this.http.get(`${this.URL}/restaurante`).pipe(catchError(this.handleError));
}

// Buscar restaurante por ID
getRestauranteById(id: number): Observable<any> {
  return this.http.get(`${this.URL}/restaurante/${id}`).pipe(catchError(this.handleError));
}

// Criar um novo restaurante
postRestaurante(restaurante: any): Observable<any> {
  return this.http.post(`${this.URL}/restaurante`, restaurante).pipe(catchError(this.handleError));
}

// Atualizar um restaurante existente
updateRestaurante(id: number, restaurante: any): Observable<any> {
  return this.http.put(`${this.URL}/restaurante/${id}`, restaurante).pipe(catchError(this.handleError));
}

// Deletar um restaurante
deleteRestaurante(id: number): Observable<any> {
  return this.http.delete(`${this.URL}/restaurante/${id}`).pipe(catchError(this.handleError));
}

// Buscar todos os pratos
getPratos(): Observable<any> {
  return this.http.get(`${this.URL}/pratosPrincipais`).pipe(catchError(this.handleError));
}

// Buscar prato por ID
getPratoById(id: number): Observable<any> {
  return this.http.get(`${this.URL}/pratosPrincipais/${id}`).pipe(catchError(this.handleError));
}

// Deletar um prato
deletePrato(id: number): Observable<any> {
  return this.http.delete(`${this.URL}/pratosPrincipais/${id}`).pipe(catchError(this.handleError));
}

// Método para tratar erros
private handleError(error: HttpErrorResponse) {
  console.error('Erro na requisição:', error);
  return throwError(() => new Error('Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.'));
}

}

