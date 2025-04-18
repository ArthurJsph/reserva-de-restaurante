import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { SessionService } from '../sessao/session.service'; // ajuste o caminho conforme necessário

interface User {
  email: string;
  token?: string;
  [key: string]: any;
}

interface LoginResponse {
  user: User;
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly apiUrl = `${environment.apiUrl}/auth`;
  private readonly sessionKey = 'currentUser';
  private loggedIn = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.loggedIn.asObservable();

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) {
    this.loggedIn.next(this.isLoggedIn());
  }

  login(credentials: { email: string; senha: string }): Observable<{ jwt: string; usuario: any }> {
    return this.http.post<{ jwt: string; usuario: any }>(`${this.apiUrl}/login`, credentials).pipe(
      tap((response) => {
        console.log('Resposta completa do servidor:', response);
  
        if (!response.jwt) {
          console.error('A resposta do servidor não contém o campo "jwt".');
          return;
        }
  
        if (!response.usuario) {
          console.error('A resposta do servidor não contém o campo "usuario".');
        }
  
        console.log('Salvando token no sessionStorage...');
        this.sessionService.set('token', response.jwt);
        console.log('Token salvo:', this.sessionService.get('token'));
  
        console.log('Salvando usuário no sessionStorage...');
        this.sessionService.set('usuario', response.usuario);
        console.log('Usuário salvo:', this.sessionService.get('usuario'));
  
        this.loggedIn.next(true);
        console.log('Estado de autenticação atualizado para true.');
      }),
      catchError(this.handleError)
    );
  }
  

  logout(): void {
    this.sessionService.clear();
    this.loggedIn.next(false); // Atualiza o estado
  }

  private isLoggedInCache: boolean | null = null;

  isLoggedIn(): boolean {
    if (this.isLoggedInCache === null) {
      const token = this.sessionService.get('token');
      this.isLoggedInCache = !!token;
      console.log('Verificando se o usuário está logado. Token encontrado:', token);
    }
    return this.isLoggedInCache;
  }

  invalidateCache(): void {
    this.isLoggedInCache = null; // Limpa o cache quando necessário (ex.: logout)
  }

  getCurrentUser(): any {
    return this.sessionService.get('usuario');
  }

  getToken(): string | null {
    return this.sessionService.get('token');
  }

  private storeUserData(response: LoginResponse): void {
    const userData = {
      ...response.user,
      token: response.token
    };
    this.sessionService.set(this.sessionKey, userData);
  }

  public getUserData(): User | null {
    return this.sessionService.get(this.sessionKey) || null;
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Ocorreu um erro ao processar sua solicitação';

    if (error.status === 401) {
      errorMessage = 'Credenciais inválidas';
    } else if (error.status === 0) {
      errorMessage = 'Erro de conexão com o servidor';
    } else if (error.error?.message) {
      errorMessage = error.error.message;
    }

    console.error('Erro na requisição de login:', error);
    return throwError(() => new Error(errorMessage));
  }
}
