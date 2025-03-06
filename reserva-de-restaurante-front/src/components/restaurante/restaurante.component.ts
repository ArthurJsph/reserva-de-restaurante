import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../app/services/item.service'; 
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-restaurante',
  imports: [CommonModule],
  templateUrl: './restaurante.component.html',
  styleUrls: ['./restaurante.component.css'] // Corrigido aqui
})
export class RestauranteComponent implements OnInit {
  restaurantes: Restaurante[] = [];
  pratos: Prato[] = [];

  constructor(private itemService: ItemService) {}


  ngOnInit(): void {
    this.carregarRestaurantes();
  }

  carregarRestaurantes(): void {
    this.itemService.getRestaurantes().subscribe(
      (data: Restaurante[]) => {
        this.restaurantes = data;
      },
      error => console.error('Erro ao carregar restaurantes:', error)
    );
  }

}

export interface Restaurante {
  nome: String;
  endereco: String;
  horario_abertura: Date;
  horario_fechamento: Date;
  imagem_url: String;
}

export interface Prato {
  id: number;
  nome: string;
  imagem_url: string;
  avaliacao: number;
}