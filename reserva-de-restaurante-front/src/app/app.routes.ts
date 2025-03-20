import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { RegistrarComponent } from '../components/registrar/registrar.component';
import { ReservaComponent } from '../components/reserva/reserva.component';
import { RestauranteComponent } from '../components/restaurante/restaurante.component';
import { HomeComponent } from '../components/home/home.component';
import { EsquecerSenhaComponent } from '../components/esquecer-senha/esquecer-senha.component';
import { AdminComponent } from '../components/dashboard/admin/admin.component';
import { LayoutGuard } from '../guards/layout.guard';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
export const routes: Routes = [
    {
        path: 'user',
        component: AdminComponent,
        canActivate: [LayoutGuard] // Usaremos um guard para alternar o layout
      },
{path: '', component: HomeComponent},
{path: 'login', component: LoginComponent},
{path: 'registrar', component: RegistrarComponent},
{path: 'reserva', component: ReservaComponent},
{path: 'restaurante', component: RestauranteComponent},
{path: 'esqueceu-senha', component: EsquecerSenhaComponent},
{path: 'user', component: AdminComponent},


];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }