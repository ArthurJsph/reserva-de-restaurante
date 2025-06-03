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
  private readonly apiUrl = `${environment.apiUrl}/usuario/listar`; // Ajuste se necessário
  private userCache: any = null;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  getUser(): any {
    if (this.userCache) {
      return this.userCache;
    }
    const user = this.authService.getCurrentUser();
    this.userCache = user;
    return user;
  }
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

  updateUserProfile(data: any): Observable<any> {
    const userId = this.authService.getCurrentUser()?.id; 
    if (!userId) {
      console.error('ID do usuário não encontrado.');
      return of(null);
    }

    return this.http.put<any>(`${this.apiUrl}/register${userId}`, data).pipe(
      tap((updatedUser) => {
        this.userCache = { ...this.userCache, ...updatedUser }; 
      }),
      catchError((error) => {
        console.error('Erro ao atualizar perfil:', error);
        throw error;
      })
    );
  }
  
  clearUserData() {
    this.userCache = null;
    this.authService.logout();
  }
}
