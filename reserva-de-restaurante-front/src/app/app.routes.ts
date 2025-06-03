import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from '../pages/auth/login/login.component';
import { RegistrarComponent } from '../pages/auth/registrar/registrar.component';
import { ReservaComponent } from '../pages/public/reserva/reserva.component';
import { RestauranteComponent } from '../pages/public/restaurante/restaurante.component';
import { HomeComponent } from '../pages/home/home.component';
import { EsquecerSenhaComponent } from '../pages/auth/esquecer-senha/esquecer-senha.component';
import { MinhaReservaComponent } from '../pages/private/minha-reserva/minha-reserva.component';
import { PerfilComponent } from '../pages/private/perfil/perfil/perfil.component';
import { Erro401Component } from '../pages/error/erro401/erro401.component';
import { Erro404Component } from '../pages/error/erro404/erro404.component';
import { AuthGuard } from '../core/guards/auth.guard'; 

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrar', component: RegistrarComponent },
  { path: 'reserva', component: ReservaComponent },
  { path: 'restaurante', component: RestauranteComponent },
  { path: 'esqueceu-senha', component: EsquecerSenhaComponent },

  // Rotas protegidas com AuthGuard
  { path: 'user/minha-reserva', component: MinhaReservaComponent, canActivate: [AuthGuard] },
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] },

  // Rotas de erro
  { path: 'error/401', component: Erro401Component },
  { path: 'error/404', component: Erro404Component },

  // Wildcard para rotas não encontradas, redireciona para página 404
  { path: '**', redirectTo: '/error/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

