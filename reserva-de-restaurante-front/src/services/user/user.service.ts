import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly apiUrl = `${environment.apiUrl}/usuario`; // Ajuste se necessário
  private userCache: any = null;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  /** Retorna o usuário da sessão ou do cache local */
  getUser(): any {
    if (this.userCache) {
      return this.userCache;
    }
    const user = this.authService.getCurrentUser();
    this.userCache = user;
    return user;
  }

  /** Carrega os dados do usuário do backend (caso precise atualizar ou forçar uma nova consulta) */
  fetchUserFromBackend(): Observable<any> {
    const token = this.authService.getToken();
    if (!token) return of(null);

    return this.http.get<any>(`${this.apiUrl}/usuario`).pipe(
      tap((userData) => {
        this.userCache = userData;
      }),
      catchError((error) => {
        console.error('Erro ao buscar dados do usuário:', error);
        return of(null);
      })
    );
  }

  /** Atualiza os dados do usuário */
  updateUserProfile(data: any): Observable<any> {
    const userId = this.authService.getCurrentUser()?.id; // Obtém o ID do usuário autenticado
    if (!userId) {
      console.error('ID do usuário não encontrado.');
      return of(null);
    }

    return this.http.put<any>(`${this.apiUrl}/register${userId}`, data).pipe(
      tap((updatedUser) => {
        this.userCache = { ...this.userCache, ...updatedUser }; // Atualiza o cache com os novos dados
      }),
      catchError((error) => {
        console.error('Erro ao atualizar perfil:', error);
        throw error;
      })
    );
  }

  /** Limpa o cache e os dados da sessão */
  clearUserData() {
    this.userCache = null;
    this.authService.logout();
  }
}
