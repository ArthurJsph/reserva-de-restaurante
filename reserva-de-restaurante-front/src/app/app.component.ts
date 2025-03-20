import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NavComponent } from '../layout/nav/nav.component';
import { FooterComponent } from '../layout/footer/footer.component';
import { LayoutService } from '../services/layout/layout.service';
import { AsyncPipe } from '@angular/common';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterModule, NavComponent, FooterComponent, AsyncPipe, NgIf],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  showNavbar$: any; // Declare a propriedade sem inicialização

  constructor(private layoutService: LayoutService) {
    this.showNavbar$ = this.layoutService.showNavbar$; // Inicialize no construtor
  }

  
}
