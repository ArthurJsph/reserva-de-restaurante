import { Component } from '@angular/core';
import { RegistrarService } from '../../../services/registrar/registrar.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar',
  standalone: true,
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css'],
  imports: [CommonModule, FormsModule]
})
export class RegistrarComponent {
  usuario = {
    nome: '',
    cpf: '',
    telefone: '',
    email: '',
    senha: '',
    confirmarSenha: '',
    role: 'ROLE_USER'
  };

  constructor(
    private registrarService: RegistrarService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (this.usuario.senha !== this.usuario.confirmarSenha) {
      alert('As senhas não coincidem!');
      return;
    }

    const usuarioParaEnvio = {
      nome: this.usuario.nome,
      cpf: this.usuario.cpf,
      telefone: this.usuario.telefone,
      email: this.usuario.email,
      senha: this.usuario.senha,
      role: this.usuario.role
    };

    this.registrarService.registrar(usuarioParaEnvio).subscribe({
      next: () => {
        alert('Cadastro realizado com sucesso!');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Erro ao cadastrar:', error);
        alert('Erro ao cadastrar. Tente novamente.');
      }
    });
  }
}
