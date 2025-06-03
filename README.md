# Projeto Reserva Restaurante

## Requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- **Java 17**  
  https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

- **Maven** (versão compatível com o projeto)  
  https://maven.apache.org/download.cgi

- **PostgreSQL 17**  
  https://www.postgresql.org/download/

- **Node.js e Angular CLI**  
  Node.js: https://nodejs.org/  
  Angular CLI: https://angular.io/cli

---

## Configuração do Banco de Dados

1. Instale o PostgreSQL 17
2. Crie um banco de dados para o projeto (exemplo: `reserva_restaurante`)
3. Configure as credenciais e URL de conexão no arquivo `application.properties` (ou `application.yml`) do backend Spring Boot:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/reserva_restaurante
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update


Rodando o Backend (Spring Boot)
No diretório do backend:

mvn clean install
mvn spring-boot:run
O backend estará disponível em: http://localhost:8080

Rodando o Frontend (Angular)
No diretório do frontend:

npm install
ng serve
O frontend estará disponível em: http://localhost:4200

O DOCKER ESTA EM MANUTENÇÃO(NÃO ESTA FUNCIONANDO )

PEGUE OS SCRIPTS no arquivo chamado shema.sql onde vai ter tudo que precisa para rodar o backend 

