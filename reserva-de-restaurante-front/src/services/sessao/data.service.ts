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

  constructor(private http: HttpClient, private sessionService: SessionService) { }

  // Método para buscar dados do banco de dados
  getDados(): Observable<any> {
    const dadosArmazenados = this.sessionService.get('dados');

    // Se os dados já estão na sessão, retorna-os
    if (dadosArmazenados) {
      return of(dadosArmazenados); // Retorna um Observable com os dados armazenados
    }

    // Se os dados não estão na sessão, faz a requisição ao banco de dados
    return this.http.get(environment.apiUrl).pipe(
      tap((dados) => {
        // Armazena os dados na sessão
        this.sessionService.set('dados', dados);
      })
    );
  }
}