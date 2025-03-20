import { Component } from '@angular/core';
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
  styleUrl: './login.component.css'
})
export class LoginComponent {
email: string = '';
senha: string = '';

constructor(private AuthService: AuthService,  private router: Router) {}


login(): void {
  if (this.AuthService.login({ email: this.email, senha: this.senha })) {
    this.router.navigate(['/dashboard']); // Redireciona para a página inicial após o login
  } else {
    alert('Credenciais inválidas');
  }
}
}
