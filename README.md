# API de Integração Contábil - Teste Técnico Magalu

Este projeto é uma API de integração desenvolvida como parte de um teste técnico para a **Magalu**. A API simula uma plataforma de integração contábil que recebe requisições contendo dados a serem contabilizados no sistema **Oracle EBS**.

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
    url: jdbc:postgresql://localhost:5432/oracledb
    username: postgres
    password: root
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        format_sql: true
    open-in-view: false
queue:
  name:
    success: response-queue-success
    error: response-queue-error
jwt:
  private:
    key: classpath:app.key
  public:
    key: classpath:app.pub
```
## 🚀 Como Executar


## 🗂️ Estrutura do Projeto


## 📦 Docker Compose

O arquivo `docker-compose.yml` configura o banco de dados PostgreSQL e o RabbitMQ:

```yaml
services:
  db:
    image: postgres:15
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
      POSTGRES_DB: oracledb
    ports:
      - "5432:5432"
  rabbitmq:
      image: rabbitmq:management
      ports:
        - 15672:15672
        - 5672:5672
```

## 🌟 Funcionalidades
- Autenticação e Autorização: Gerenciamento de usuários e permissões.
- Integração com RabbitMQ: Comunicação assíncrona para processamento de mensagens.
- Persistência com PostgreSQL: Armazenamento e consulta de dados e logs.
- Validações Customizadas: Garantia de integridade dos dados de entrada.
- Boas Práticas: Código limpo, testável e documentado
