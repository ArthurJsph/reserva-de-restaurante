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
export class RestauranteComponent implements OnInit {
  restaurantes: Restaurante[] = [];
  erroCarregamento: string = ''; // Flag para controle de erro
  isLoading = true; // Flag para estado de carregamento

  constructor(private restauranteService: RestauranteService) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.isLoading = true;
    this.erroCarregamento = ''; // Resetando a mensagem de erro

    this.restauranteService.getRestaurantes().subscribe({
      next: (data: any) => {
        // Verificando se a resposta é um array e tratando dados nulos
        if (Array.isArray(data)) {
          this.restaurantes = data.map(restaurante => ({
            ...restaurante,
            imagem_url: restaurante.imagem_url || 'assets/images/default-restaurant.jpg' // Fallback para imagem
          }));
        } else {
          // Se a resposta não for um array, iniciamos com um array vazio
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
