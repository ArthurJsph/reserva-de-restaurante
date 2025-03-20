import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LayoutService } from '../services/layout/layout.service';

@Injectable({
  providedIn: 'root'
})
export class LayoutGuard implements CanActivate {

  constructor(private layoutService: LayoutService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (state.url === '/user') {
      this.layoutService.toggleNavbar(false); // Oculta a navbar no dashboard
    } else {
      this.layoutService.toggleNavbar(true); // Exibe a navbar nas outras rotas
    }
    return true;
  }
}