import { Component } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  imports: [],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {
  menuItems = [
    { label: 'Dashboard', icon: '📊', link: '/dashboard' },
    { label: 'Restaurantes', icon: '🍽️', link: '/restaurants' },
    { label: 'Reservas', icon: '📅', link: '/reservations' },
    { label: 'Usuários', icon: '👥', link: '/users' },
    { label: 'Configurações', icon: '⚙️', link: '/settings' },
    { label: 'Relatórios', icon: '📊', link: '/reports' },
    { label: 'Sair', icon: '📤', link: '/logout' }
  ];
}
