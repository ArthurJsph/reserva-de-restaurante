import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { RegistrarComponent } from '../components/registrar/registrar.component';
import { ReservaComponent } from '../components/reserva/reserva.component';
import { RestauranteComponent } from '../components/restaurante/restaurante.component';
import { HomeComponent } from '../components/home/home.component';
import { EsquecerSenhaComponent } from '../components/esquecer-senha/esquecer-senha.component';
export const routes: Routes = [
{path: '', component: HomeComponent},
{path: 'login', component: LoginComponent},
{path: 'registrar', component: RegistrarComponent},
{path: 'reserva', component: ReservaComponent},
{path: 'restaurante', component: RestauranteComponent},
{path: 'esqueceu-senha', component: EsquecerSenhaComponent},


];
