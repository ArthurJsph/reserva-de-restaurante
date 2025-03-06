import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ItemService {
  //URL do backend
private URL = 'http://localhost:8080/api';
//Injeção de dependência do HttpClient
constructor(private http: HttpClient) {}
//Método para buscar reservas
getReservas(): Observable<any> {
return this.http.get(this.URL + '/reserva');
}
getReservaById(): Observable<any> {
return this.http.get(this.URL + '/reserva/:id');
}

getReservaByDate(): Observable<any> {
return this.http.get(this.URL + '/reserva/:date');
}

postReserva(reserva: any): Observable<any> {
return this.http.post(this.URL + '/reserva', reserva);
}

updateReserva(id: number, dadosAtualizados: any): Observable<any> {
  return this.http.put(`${this.URL}/reserva/${id}`, dadosAtualizados);
}

deleteReserva(id: number): Observable<any> {
return this.http.delete(this.URL + '/reserva/' + id);
}

getRestaurantes(): Observable<any> {
return this.http.get(this.URL + '/restaurante');}

getRestauranteById(): Observable<any> {
return this.http.get(this.URL + '/restaurante/:id');
}

postRestaurante(restaurante: any): Observable<any> {
return this.http.post(this.URL + '/restaurante', restaurante);
}

updateRestaurante(restaurante: any): Observable<any> {
return this.http.put(this.URL + '/restaurante/' + restaurante.id, restaurante);
}

deleteRestaurante(id: number): Observable<any> {
return this.http.delete(this.URL + '/restaurante/' + id);
}








}
