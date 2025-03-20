import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
@Component({
  selector: 'app-admin',
  imports: [],
  standalone: true,
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {

  usuario: any;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    // Obtém o usuário logado ao inicializar o componente
    this.usuario = this.authService.getCurrentUser();
  }

  logout(): void {
    this.authService.logout();
    this.usuario = null; // Limpa o usuário no componente
  }
}
