import { Component, OnInit, Pipe } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SessionService } from '../../../services/sessao/session.service';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { UserService } from '../../../services/user/user.service';
import { take } from 'rxjs';
interface UserData {
  nome: string;
  cpf: string;
  telefone: string;
  email: string;
  role: string;
}
@Component({
  selector: 'app-perfil',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent implements OnInit {
  usuario: UserData = {
    nome: '',
    cpf: '',
    telefone: '',
    email: '',
    role: ''
  };

  editMode = false;
  novaSenha = '';
  confirmarNovaSenha = '';
  isLoggedIn = false;
  constructor(
    private sessionService: SessionService,
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}
  formatCpf(cpf: string): string {
    if (!cpf) return '';
    cpf = cpf.replace(/\D/g, ''); // Remove caracteres não numéricos
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }
  
  formatTelefone(telefone: string): string {
    if (!telefone) return '';
    telefone = telefone.replace(/\D/g, ''); // Remove caracteres não numéricos
    return telefone.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
  }
  ngOnInit(): void {

    this.isLoggedIn = this.authService.isLoggedIn();
    if (!this.authService.isLoggedIn()) {
      alert('Nenhum usuário logado. Redirecionando para login.');
      this.router.navigate(['/login']);
      return;
    }
  
    // Depois busca os dados do usuário
    const usuario = this.sessionService.get('usuario');
    if (usuario) {
      this.usuario = {
        ...usuario,
        cpf: this.formatCpf(usuario.cpf), // Aplica máscara ao CPF
        telefone: this.formatTelefone(usuario.telefone) // Aplica máscara ao telefone
      };
    } else {
      // Se não tiver dados do usuário mas estiver autenticado, busca do backend
      this.carregarDadosUsuario();
    }
  }
  
  carregarDadosUsuario(): void {
    const userDataObservable = this.authService.getUserData();
    if (userDataObservable) {
      userDataObservable['pipe'](take(1)).subscribe({ // Limita a chamada a uma única vez
        next: (userData: UserData) => {
          this.usuario = userData;
          this.sessionService.set('usuario', userData); // Armazena no cache
        },
        error: (error: Error) => {
          console.error('Erro ao carregar dados do usuário:', error);
          this.router.navigate(['/login']);
        }
      });
    } else {
      console.error('Erro: getUserData retornou null.');
      this.router.navigate(['/login']);
    }
  }

salvarAlteracoes(): void {
  this.userService.updateUserProfile(this.usuario).subscribe({
    next: (updatedUser) => {
      console.log('Usuário atualizado com sucesso:', updatedUser);
      this.sessionService.set('usuario', updatedUser); // Atualiza os dados na sessão
      alert('Alterações salvas com sucesso!');
    },
    error: (error) => {
      console.error('Erro ao atualizar usuário:', error);
      alert('Ocorreu um erro ao salvar as alterações.');
    }
  });
}

logout(): void {
  // Chama o serviço de autenticação para fazer logout
  this.authService.logout();
  this.isLoggedIn = false;
  this.router.navigate(['/login']); // Redireciona para a página de login após o logout
}

}
