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
return this.http.get(this.URL + '/reservas');
}
getReservaById(): Observable<any> {
return this.http.get(this.URL + '/reservas/:id');
}

getReservaByDate(): Observable<any> {
return this.http.get(this.URL + '/reservas/:date');
}

postReserva(reserva: any): Observable<any> {
return this.http.post(this.URL + '/reservas', reserva);
}

updateReserva(id: number, dadosAtualizados: any): Observable<any> {
  return this.http.put(`${this.URL}/reservas/${id}`, dadosAtualizados);
}

deleteReserva(id: number): Observable<any> {
return this.http.delete(this.URL + '/reservas/' + id);
}

getRestaurantes(): Observable<any> {
return this.http.get(this.URL + '/restaurantes');}

getRestauranteById(): Observable<any> {
return this.http.get(this.URL + '/restaurantes/:id');
}

postRestaurante(restaurante: any): Observable<any> {
return this.http.post(this.URL + '/restaurantes', restaurante);
}

updateRestaurante(restaurante: any): Observable<any> {
return this.http.put(this.URL + '/restaurantes/' + restaurante.id, restaurante);
}

deleteRestaurante(id: number): Observable<any> {
return this.http.delete(this.URL + '/restaurantes/' + id);
}








}
