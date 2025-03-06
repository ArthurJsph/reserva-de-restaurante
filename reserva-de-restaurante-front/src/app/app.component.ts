import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NavComponent } from '../layout/nav/nav.component';
import { FooterComponent } from '../layout/footer/footer.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterModule, NavComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'reserva de restaurante';
}
