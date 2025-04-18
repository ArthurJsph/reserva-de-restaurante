import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { provideNgxMask } from 'ngx-mask'; // Importe o provider do ngx-mask

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(), // Fornece suporte para HTTP requests
    provideRouter([]),   // Configura as rotas (substitua [] pelas suas rotas, se necessário)
    provideNgxMask(),    // Adiciona o provider do ngx-mask
    ...appConfig.providers // Inclui os providers adicionais do appConfig, se houver
  ]
}).catch((err) => console.error(err));