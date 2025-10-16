# Etapa 1: Build do Angular
FROM node:20 AS build
WORKDIR /app

# Passados via docker build args
ARG API_URL
ARG APP_NAME="Reserva de Restaurante"
ENV API_URL=${API_URL}
ENV APP_NAME=${APP_NAME}

# Copia arquivos do projeto
COPY package*.json ./
RUN npm ci || npm install
COPY . .

# Gera os arquivos de ambiente substituindo placeholders
# environment.ts -> production=false
# environment.prod.ts -> production=true
RUN sh -c "sed -e 's|__API_URL__|'\"$API_URL\"'|g' -e 's|__APP_NAME__|'\"$APP_NAME\"'|g' -e 's|__PRODUCTION__|false|g' src/environments/environment.template > src/environments/environment.ts" \
 && sh -c "sed -e 's|__API_URL__|'\"$API_URL\"'|g' -e 's|__APP_NAME__|'\"$APP_NAME\"'|g' -e 's|__PRODUCTION__|true|g' src/environments/environment.template > src/environments/environment.prod.ts"

# Builda a aplicação Angular (modo produção)
RUN npm run build

# Etapa 2: Servir com NGINX
FROM nginx:alpine
COPY --from=build /app/dist/reserva-de-restaurante-front/browser /usr/share/nginx/html

EXPOSE 80
