import { Component, OnInit } from '@angular/core';
import { RestauranteService } from '../../services/restaurante/restaurante.service';
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
  styleUrls: ['./restaurante.component.css'] // Corrigido aqui
})
export class RestauranteComponent {
 restaurantes: Restaurante[] = [];
  erroCarregamento : String = ''; // Flag para controle de erro
  isLoading = true; // Flag para estado de carregamento

  constructor(private restauranteService: RestauranteService) {}

  gOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.isLoading = true;
    this.erroCarregamento = '';

    this.restauranteService.getRestaurantes().subscribe({
      next: (data: Restaurante[]) => {
        // Adiciona fallback para imagem_url
        this.restaurantes = data.map(restaurante => ({
          ...restaurante,
          imagem_url: restaurante.imagem_url || 'assets/images/default-restaurant.jpg'
        }));
      },
      error: (error) => {
        this.erroCarregamento = 'Erro ao carregar restaurantes. Tente novamente mais tarde.';
        console.error('Erro ao carregar restaurantes:', error);
      },
      complete: () => this.isLoading = false
    });
  }
    
}
