import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'  // ← Isso é crucial!
})
export class SessionService {
  private sessionCache: { [key: string]: any } = {};

  set(key: string, value: any): void {
    console.log(`Salvando no sessionStorage -> Chave: ${key}, Valor:`, value);
    this.sessionCache[key] = value;
    sessionStorage.setItem(key, JSON.stringify(value));
  }

  get(key: string): any {
    if (this.sessionCache[key] !== undefined) {
      return this.sessionCache[key];
    }
    try {
      const data = sessionStorage.getItem(key);
      this.sessionCache[key] = data ? JSON.parse(data) : null;
      return this.sessionCache[key];
    } catch (e) {
      console.error('Error reading from sessionStorage', e);
      return null;
    }
  }

  remove(key: string): void {
    delete this.sessionCache[key];
    sessionStorage.removeItem(key);
  }

  clear(): void {
    this.sessionCache = {};
    sessionStorage.clear();
  }
}
