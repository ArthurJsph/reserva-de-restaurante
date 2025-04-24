# Etapa 1: Build do Angular
FROM node:20 AS build
WORKDIR /app

# Copia arquivos do projeto
COPY . .

# Instala o dotenv-cli para ler o .env
RUN npm install -g dotenv-cli

# Instala as dependências do Angular
RUN npm install

# Gera os arquivos de ambiente substituindo o placeholder __API_URL__
RUN dotenv -e .env -- \
  sh -c "sed \"s|__API_URL__|$API_URL|g\" src/environments/env.template.ts > src/environments/environment.ts && \
         sed \"s|__API_URL__|$API_URL|g\" src/environments/env.template.ts > src/environments/environment.prod.ts"


# Builda a aplicação Angular (modo produção)
RUN npm run build

# Etapa 2: Servir com NGINX
FROM nginx:alpine
COPY --from=build /app/dist/reserva-de-restaurante-front/browser /usr/share/nginx/html

EXPOSE 80
