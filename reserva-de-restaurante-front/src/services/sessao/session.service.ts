import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class SessionService {
  private sessionData: { [key: string]: any } = {};

  constructor() { }

  // Armazena um valor na sessão
  set(key: string, value: any): void {
    this.sessionData[key] = value;
  }

  // Obtém um valor da sessão
  get(key: string): any {
    return this.sessionData[key];
  }

  // Remove um valor da sessão
  remove(key: string): void {
    delete this.sessionData[key];
  }

  // Limpa toda a sessão
  clear(): void {
    this.sessionData = {};
  }
}