import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NavComponent } from '../layout/nav/nav.component';
import { FooterComponent } from '../layout/footer/footer.component';
import {LoginComponent} from '../components/login/login.component';
import {RegistrarComponent} from '../components/registrar/registrar.component';
import { HomeComponent } from '../components/home/home.component';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterModule, NavComponent, FooterComponent, LoginComponent, RegistrarComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'reserva de restaurante';
}
