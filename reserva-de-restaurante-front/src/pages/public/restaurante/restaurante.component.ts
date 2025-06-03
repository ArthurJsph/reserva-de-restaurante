import { Component, OnInit } from '@angular/core';
import { RestauranteService } from '../../../services/restaurante/restaurante.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

interface Restaurante {
  id: number;
  nome: string;
  endereco: string;
  tipoCozinha: string;
  capacidade: number;
  imagem_url: string;
}

@Component({
  selector: 'app-restaurante',
  imports: [CommonModule, RouterModule],
  templateUrl: './restaurante.component.html',
  styleUrls: ['./restaurante.component.css'] 
})
export class RestauranteComponent implements OnInit {
  restaurantes: Restaurante[] = [];
  erroCarregamento: string = ''; 
  isLoading = true; 

  constructor(private restauranteService: RestauranteService) {}

  ngOnInit(): void {
    this.carregarDados();
  }

 carregarDados(): void {
  this.isLoading = true;
  this.erroCarregamento = ''; 

  this.restauranteService.getRestaurantes().subscribe({
    next: (data: any) => {
      if (Array.isArray(data)) {
        this.restaurantes = data.map(restaurante => ({
          ...restaurante,
          imagem_url: restaurante.imagem_url 
          ? `${restaurante.imagem_url}` 
          : 'default_restaurant.jpg'
        }));
      } else {
        this.restaurantes = [];
      }
    },
    error: (error) => {
      this.erroCarregamento = 'Erro ao carregar restaurantes. Tente novamente mais tarde.';
      console.error('Erro ao carregar restaurantes:', error);
    },
    complete: () => this.isLoading = false
  });
}
}
