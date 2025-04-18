import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { RegistrarComponent } from '../components/registrar/registrar.component';
import { ReservaComponent } from '../components/reserva/reserva.component';
import { RestauranteComponent } from '../components/restaurante/restaurante.component';
import { HomeComponent } from '../components/home/home.component';
import { EsquecerSenhaComponent } from '../components/esquecer-senha/esquecer-senha.component';
import { MinhaReservaComponent } from '../components/minha-reserva/minha-reserva.component';
import { AdminComponent } from '../components/dashboard/admin/admin.component';
import { PerfilComponent } from '../components/perfil/perfil/perfil.component';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

export const routes: Routes = [
{path: 'user', component: AdminComponent },
{path: '', component: HomeComponent},
{path: 'user/minha-reserva', component: MinhaReservaComponent},
{path: 'login', component: LoginComponent},
{path: 'registrar', component: RegistrarComponent},
{path: 'reserva', component: ReservaComponent},
{path: 'restaurante', component: RestauranteComponent},
{path: 'esqueceu-senha', component: EsquecerSenhaComponent},
{path: 'perfil', component: PerfilComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }