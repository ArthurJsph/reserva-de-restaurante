<div align="center">

# Reserva de Restaurante

Aplicação full-stack para gerenciamento de restaurantes e reservas. Backend em Spring Boot e Frontend em Angular.

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-3.9.x-blue)](https://maven.apache.org/)
[![Angular](https://img.shields.io/badge/Angular-19-red)](https://angular.dev/)
[![Node](https://img.shields.io/badge/Node-20-green)](https://nodejs.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

</div>


## Sumário

- Visão Geral
- Requisitos
- Estrutura do Projeto
- Variáveis de Ambiente
- Executar com Docker (recomendado)
- Executar localmente (sem Docker)
- Scripts úteis
- Troubleshooting (erros comuns)
- Contribuição
- Licença

---

## Visão Geral

Este repositório contém duas aplicações:

- Backend: `reserva-restaurantes` — API REST em Spring Boot
- Frontend: `reserva-de-restaurante-front` — SPA em Angular

Um arquivo `docker-compose.yml` orquestra os serviços (PostgreSQL, backend e frontend).

---

## Requisitos

Para rodar localmente sem Docker:

- Java 17
- Maven 3.9+
- Node.js 20+ e Angular CLI 19+
- PostgreSQL 16/17

Para rodar com Docker:

- Docker Desktop atualizado (com Docker Compose)

---

## Estrutura do Projeto

```
reserva-de-restaurante/
├─ docker-compose.yml
├─ .env.example
├─ LICENSE
├─ schema.sql
├─ reserva-restaurantes/           # Spring Boot
│  ├─ src/main/resources/application.properties
│  └─ Dockerfile
└─ reserva-de-restaurante-front/   # Angular
   ├─ src/environments/
   │  ├─ environment.ts            # dev local
   │  ├─ environment.prod.ts       # prod fallback
   │  └─ environment.template      # usado no build Docker
   └─ Dockerfile
```

---

## Variáveis de Ambiente

Copie `.env.example` para `.env` e ajuste os valores conforme seu ambiente:

```powershell
Copy-Item .env.example .env
# Edite .env conforme necessário
```

Variáveis principais (arquivo `.env`):

- API_URL: URL utilizada pelo frontend para acessar o backend (ex.: http://localhost:8080/api)
- DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD: credenciais do PostgreSQL
- FRONTEND_PORT, BACKEND_PORT, POSTGRES_PORT: portas expostas localmente
- SPRING_PROFILES_ACTIVE: perfil do Spring (ex.: prod)

O backend também aceita variáveis padrão do Spring, por exemplo:

- SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD
- SERVER_PORT, SPRING_JPA_HIBERNATE_DDL_AUTO

---

## Executar com Docker (recomendado)

1) Gere o `.env` conforme seção anterior.

2) Suba os serviços:

```powershell
docker compose up --build
```

3) Acesse:

- Frontend: http://localhost:4200
- Backend: http://localhost:8080
- Banco: localhost:5432

4) Parar e remover containers:

```powershell
docker compose down
```

Notas:

- O `docker-compose.yml` cria um banco Postgres, passa configurações para o Spring Boot e embute a URL do backend no build do frontend.
- Para rebuild do frontend com nova `API_URL`, altere o valor no `.env` e rode `docker compose build frontend; docker compose up -d`.

---

## Executar localmente (sem Docker)

Backend (Spring Boot):

```powershell
# No diretório reserva-restaurantes
mvn clean install
mvn spring-boot:run
# http://localhost:8080
```

Configure o `src/main/resources/application.properties` ou use variáveis de ambiente do Spring (SPRING_DATASOURCE_URL, etc.). Um arquivo de exemplo está em `reserva-restaurantes/application.properties.example`.

Frontend (Angular):

```powershell
# No diretório reserva-de-restaurante-front
npm install
npm start # ou: ng serve
# http://localhost:4200
```

Se necessário, ajuste `src/environments/environment.ts` com a `apiUrl` do backend local.

---

## Scripts úteis

Docker:

```powershell
docker compose build
docker compose up -d
docker compose logs -f backend
docker compose down
```

Frontend:

```powershell
cd reserva-de-restaurante-front
npm run build
npm test
```

Backend:

```powershell
cd reserva-restaurantes
mvn -DskipTests package
mvn test
```

---

## Troubleshooting (erros comuns)

- Porta em uso: altere FRONTEND_PORT/BACKEND_PORT/POSTGRES_PORT no `.env`.
- Erros de conexão com DB: confira `DB_*` no `.env` e logs do serviço `postgres`.
- Frontend chamando URL errada: ajuste `API_URL` no `.env` e refaça o build do frontend.
- Build do backend falhando por código: verifique classes e pacotes; rode `mvn -X` para logs detalhados.

---

## Contribuição

Contribuições são bem-vindas! Abra uma issue ou envie um PR. Antes, por favor:

- Siga as boas práticas do commit (mensagens claras)
- Adicione/atualize a documentação quando necessário
- Não faça commit de segredos (veja `.gitignore` e use `.env`)

---

## Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

