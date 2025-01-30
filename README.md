# API de Integração Contábil - Teste Técnico Magalu

Este projeto é uma API de integração desenvolvida como parte de um teste técnico para a **Magalu**. A API simula uma plataforma de integração contábil que recebe requisições contendo dados a serem contabilizados no sistema **Oracle EBS**.

Documentação: https://github.com/ChigorGomes/teste_luiza/wiki 

Collection: https://github.com/ChigorGomes/teste_luiza/blob/main/oracleebs/collection/collection.json 

---

## 📝 Requisitos do Teste Técnico

- Desenvolver aplicações back-end em Java utilizando **Spring Boot**.
- Implementar mecanismos de autenticação e autorização.
- Interagir com bancos de dados relacionais (**PostgreSQL**).
- Utilizar filas de mensagens (**RabbitMQ**) para comunicação assíncrona.
- Modelar e resolver problemas de integração entre sistemas.
- Demonstrar boas práticas de desenvolvimento de software.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**: Linguagem principal para desenvolvimento.
- **Spring Boot 3**: Framework para construção da API.
- **PostgreSQL**: Banco de dados relacional.
- **RabbitMQ**: Fila de mensagens para comunicação assíncrona.
- **MapStruct**: Mapeamento de objetos.
- **Flyway**: Controle de versões do banco de dados.
- **Docker**: Containerização da aplicação e banco de dados.
- **JUnit 5** e **Mockito**: Testes unitários e mocks.

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- **Java 21** instalado
- **Docker** e **Docker Compose** instalados
- **Maven**

### Configuração do Banco de Dados

O projeto utiliza PostgreSQL como banco de dados. Certifique-se de configurar as credenciais no arquivo `application.yml` ou use variáveis de ambiente no Docker Compose.

Exemplo de configuração no `application.yml`:

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO}
    properties:
      hibernate:
        format_sql: ${SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL}
    open-in-view: false
  rabbitmq:
    host: ${RABBIT_HOST}
    port: ${RABBIT_PORT}
    username: ${RABBIT_USERNAME}
    password: ${RABBIT_PASSWORD}
queue:
  name:
    success: ${QUEUE_NAME_SUCCESS}
    error: ${QUEUE_NAME_ERROR}
jwt:
  private:
    key: ${JWT_PRIVATE_KEY}
  public:
    key: ${JWT_PUBLIC_KEY
```
## 🚀 Como Executar

1. **Compilar o projeto**:
   Navegue até a pasta `oracleebs` e execute o Maven para gerar o pacote JAR:

   ```bash
   cd oracleebs
   mvn clean package

2. **Subir os containers Docker**:
  Após compilar o JAR, execute o comando abaixo para construir e rodar os containers:


 ```bash
     docker-compose up --build
```

3. **Acessar a aplicação**:
   
- **Aplicação Spring Boot**: Acesse em http://localhost:9080.
- **RabbitMQ**: Acesse o painel em http://localhost:15672 (usuário: `guest`, senha: `guest`).
- **PostgreSQL**: Acesse em `localhost:5432` com usuário `postgres` e senha `root`.
   
## 📦 Docker Compose

O arquivo `docker-compose.yml` configura o banco de dados PostgreSQL e o RabbitMQ:

```yaml
version: '3.8'

services:
  app:
    build: .
    container_name: springboot_app
    depends_on:
      - db
      - rabbitmq
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/oracledb
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: root
      SPRING_JPA_HIBERNATE_DDL_AUTO: none
      SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL: "true"
      QUEUE_NAME_SUCCESS: response-queue-success
      QUEUE_NAME_ERROR: response-queue-error
      JWT_PRIVATE_KEY: classpath:app.key
      JWT_PUBLIC_KEY: classpath:app.pub
      RABBIT_HOST: rabbitmq
      RABBIT_PORT: 5672
      RABBIT_USERNAME: guest
      RABBIT_PASSWORD: guest
    ports:
      - "9080:8080"
    restart: always
    networks:
      - app_network

  db:
    image: postgres:15
    container_name: postgres_db
    environment:
      POSTGRES_DB: oracledb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - app_network

  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
    ports:
      - "5672:5672"
      - "15672:15672"
    networks:
      - app_network

networks:
  app_network:

volumes:
  postgres_data:
```

## 🌟 Funcionalidades
- Autenticação e Autorização: Gerenciamento de usuários e permissões.
- Integração com RabbitMQ: Comunicação assíncrona para processamento de mensagens.
- Persistência com PostgreSQL: Armazenamento e consulta de dados e logs.
- Validações Customizadas: Garantia de integridade dos dados de entrada.
- Boas Práticas: Código limpo, testável e documentado
