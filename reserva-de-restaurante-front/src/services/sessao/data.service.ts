import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SessionService } from './session.service';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class DataService {
  private readonly sessionKey = 'dados';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) {}

  getDados(): Observable<any> {
    const dados = this.sessionService.get(this.sessionKey);

    if (dados) {
      return of(dados);
    }

    return this.http.get(`${environment.apiUrl}/usuario`).pipe(
      tap((dadosRecebidos) => {
        this.sessionService.set(this.sessionKey, dadosRecebidos);
      })
    );
  }
}