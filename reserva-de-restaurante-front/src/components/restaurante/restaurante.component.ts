import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../app/services/item.service'; 
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-restaurante',
  imports: [CommonModule, RouterModule],
  templateUrl: './restaurante.component.html',
  styleUrls: ['./restaurante.component.css'] // Corrigido aqui
})
export class RestauranteComponent implements OnInit {
  restaurantes: Restaurante[] = [];
  pratos: Prato[] = [];
  erroCarregamento = false; // Flag para controle de erro
  isLoading = true; // Flag para estado de carregamento

  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.isLoading = true;
    this.erroCarregamento = false;

    // Carregar restaurantes
    this.itemService.getRestaurantes().subscribe({
      next: (data: Restaurante[]) => {
        this.restaurantes = data;
      },
      error: (error) => {
        this.erroCarregamento = true;
        console.error('Erro ao carregar restaurantes:', error);
      },
      complete: () => this.isLoading = false
    });

    // Carregar pratos
    this.itemService.getPratos().subscribe({
      next: (data: Prato[]) => {
        this.pratos = data;
      },
      error: (error) => {
        this.erroCarregamento = true;
        console.error('Erro ao carregar pratos:', error);
      }
    });
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