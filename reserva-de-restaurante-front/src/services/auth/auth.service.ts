import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError , tap} from 'rxjs/operators';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor(private http: HttpClient) {}

  login(user: { email: string, senha: string }): Observable<any> {
    return this.http.post(`${environment.apiUrl}/auth/login`, user).pipe(
      tap((response) => {
        // Armazena o usuário logado no localStorage ou sessionStorage
        localStorage.setItem('currentUser', JSON.stringify(response));
      }),
      catchError(this.handleError)
    );
  }

  // Método para fazer logout
  logout(): void {
    localStorage.removeItem('currentUser'); // Remove o usuário logado
  }

  // Método para verificar se o usuário está logado
  isLoggedIn(): boolean {
    return !!localStorage.getItem('currentUser');
  }

  // Método para obter o usuário logado
  getCurrentUser(): any {
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
  }

  // Método para tratar erros
  private handleError(error: HttpErrorResponse) {
    console.error('Erro na requisição:', error);
    return throwError(() => new Error('Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.'));
  }
}
