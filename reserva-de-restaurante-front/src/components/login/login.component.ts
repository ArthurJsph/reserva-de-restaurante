import { Component, EventEmitter, Output } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports:[RouterModule, FormsModule, HttpClientModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  senha: string = '';
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,

  ) {}

  login(): void {
    if (!this.email || !this.senha) {
      this.errorMessage = 'Por favor, preencha todos os campos';
      return;
    }
  
    this.isLoading = true;
    this.errorMessage = '';
  
    this.authService.login({ email: this.email, senha: this.senha }).subscribe({
      next: (response) => {
        console.log('Login bem-sucedido:', response);
        
        // Redireciona diretamente (os dados JÁ foram salvos no SessionService)
        this.router.navigate(['/perfil']);
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = error.message;
        this.isLoading = false;
      }
    });
  }
}
