# SUMÁRIO: Atividade Complementar 4 - Continuação da API para Gestão de Livros

API REST desenvolvida com Spring Boot para gerenciamento de livros, autores e editoras.

![Java 17+](https://img.shields.io/badge/Java%2017%2B-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot 3.5.9](https://img.shields.io/badge/Spring%20Boot%203.5.9-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Maven 3.6+](https://img.shields.io/badge/Maven%203.6%2B-C71A36?style=flat-square&logo=apachemaven&logoColor=white)


        🔴 🟩🟩    __/\\\\\\\\\\\__/\\\\\\\\\\\\\\\_____/\\\\\\\\\\\__________/\\\\\\\\\_        
        🟩 🟩        _\/////\\\///__\/\\\///////////____/\\\/////////\\\_____/\\\////////__       
        🟩 🟩🟩      _____\/\\\_____\/\\\______________\//\\\______\///____/\\\/___________      
        🟩 🟩          _____\/\\\_____\/\\\\\\\\\\\_______\////\\\__________/\\\_____________     
                         _____\/\\\_____\/\\\///////___________\////\\\______\/\\\_____________    
                          _____\/\\\_____\/\\\_____________________\////\\\___\//\\\____________   
                            _____\/\\\_____\/\\\______________/\\\______\//\\\___\///\\\__________  
                             __/\\\\\\\\\\\_\/\\\_____________\///\\\\\\\\\\\/______\////\\\\\\\\\_ 
                              _\///////////__\///________________\///////////___________\/////////__
                              

> ⚡ **[Acessar instruções "INSTRUCTIONS.md" →](INSTRUCTIONS.md)**

- ⭐ **[Repositório GitHub ↗](https://github.com/andreriffen/api-livros)**

- Acessar trecho do terminal [populando dados →](INSTRUCTIONS.md#-exemplo-de-execução)

- Acessar trecho do terminal [testando as endpoints →](INSTRUCTIONS.md#-exemplo-de-teste-após-inserção-de-dados)

---

> *Aluno:* **ANDRE GUILHERME BARRETO DE FARIAS**

> *Matrícula:* **202111701842**

> *Disciplina:* **SWB | Serviços Web**

> *Curso:* **CTDS [3010]/FLN**

---

## 📑 Índice (Sumário/Readme)

- [1. 📋 Descrição](#1--descrição)
- [2. 🎯 Objetivo](#2--objetivo)
- [3. 🏗️ Modelo de Dados](#3-️-modelo-de-dados)
  - [3.1. Entidade Autor](#31-entidade-autor)
  - [3.2. Entidade Editora](#32-entidade-editora)
  - [3.3. Entidade Livro](#33-entidade-livro)
- [4. 📡 Endpoints da API](#4--endpoints-da-api)
  - [4.1. Autores](#41-autores)
  - [4.2. Editoras](#42-editoras)
  - [4.3. Livros](#43-livros)
- [5. 🛠️ Tecnologias Utilizadas](#5-️-tecnologias-utilizadas)
- [6. 📂 Estrutura do Projeto](#6--estrutura-do-projeto)
- [7. ⚙️ Configuração](#7-️-configuração)
- [8. 🚀 Como Executar](#8--como-executar)
- [9. 📦 Build e Distribuição](#9--build-e-distribuição)
- [10. 🔒 Extras](#10--extras)
- [11. 📝 Entrega da Atividade](#11--entrega-da-atividade)
- [12. 🔗 Links Úteis](#12--links-úteis)
- [13. 📜 Licença](#-licença)
- [14. ☕ Autor @andreriffen](#-autor)

---

## 1. 📋 Descrição

Este projeto implementa uma API REST completa para gerenciar um sistema de livros, incluindo operações CRUD (Create, Read, Update, Delete) para três entidades principais:

- **Livros**: Gerenciamento de livros com cálculo automático de preço de venda
- **Autores**: Cadastro e controle de autores
- **Editoras**: Registro e gerenciamento de editoras

## 2. 🎯 Objetivo

Desenvolver uma API REST robusta utilizando Spring Boot, seguindo boas práticas de desenvolvimento como:

- Arquitetura em camadas (Controller, Service, Repository, Model)
- DTOs para transferência de dados
- Tratamento de exceções personalizado
- Validação de dados
- Documentação com Swagger
- Uso de Lombok para redução de código boilerplate

## 3. 🏗️ Modelo de Dados

### 3.1. Entidade Autor

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| id | Integer | Identificador único |
| nome | String | Nome do autor |
| email | String | E-mail do autor |
| nacionalidade | String | Nacionalidade do autor |

### 3.2. Entidade Editora

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| id | Integer | Identificador único |
| nome | String | Nome da editora |
| email | String | E-mail da editora |
| cidade | String | Cidade da editora |
| estado | String | Estado da editora |

### 3.3. Entidade Livro

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| id | Integer | Identificador único |
| titulo | String | Título do livro |
| isbn | String | Código ISBN |
| precoDeCusto | BigDecimal | Preço de custo |
| precoDeVenda | BigDecimal | Preço de venda (calculado) |
| margemDeLucro | BigDecimal | Margem de lucro |
| dataDeCadastro | LocalDate | Data de cadastro |
| status | StatusLivro | Status do livro (enum) |
| autor | Autor | Autor do livro (ManyToOne) |
| editora | Editora | Editora do livro (ManyToOne) |

**Enum StatusLivro:**
- `DISPONIVEL`
- `ESGOTADO`
- `BLOQUEADO`

**Método de Negócio:**
- `calcularPrecoVenda()`: Calcula o preço de venda baseado em: `precoDeVenda = precoDeCusto + (precoDeCusto * margemDeLucro)`

## 4. 📡 Endpoints da API

### 4.1. Autores

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/autores` | Criar novo autor |
| GET | `/autores/{id}` | Buscar autor por ID |
| GET | `/autores` | Listar todos os autores |
| PUT | `/autores/{id}` | Atualizar autor |
| DELETE | `/autores/{id}` | Remover autor |

### 4.2. Editoras

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/editoras` | Criar nova editora |
| GET | `/editoras/{id}` | Buscar editora por ID |
| GET | `/editoras` | Listar todas as editoras |
| PUT | `/editoras/{id}` | Atualizar editora |
| DELETE | `/editoras/{id}` | Remover editora |

### 4.3. Livros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/livros` | Criar novo livro |
| GET | `/livros/{id}` | Buscar livro por ID |
| GET | `/livros` | Listar todos os livros |
| PUT | `/livros/{id}` | Atualizar livro |
| DELETE | `/livros/{id}` | Remover livro |

## 5. 🛠️ Tecnologias Utilizadas

- **Spring Boot**: Framework principal
- **Spring Data JPA**: Persistência de dados
- **Hibernate**: ORM
- **Lombok**: Redução de código boilerplate
- **Jakarta Bean Validation**: Validação de dados
- **ModelMapper**: Conversão entre DTOs e entidades
- **SpringDoc OpenAPI (Swagger)**: Documentação da API
- **H2 Database** ou **MySQL**: Banco de dados
- **Maven**: Gerenciamento de dependências

## 6. 📂 Estrutura do Projeto

```
src/main/java/
├── model/          # Entidades JPA
│   ├── Autor.java
│   ├── Editora.java
│   ├── Livro.java
│   └── StatusLivro.java (enum)
├── repository/     # Interfaces JpaRepository
│   ├── AutorRepository.java
│   ├── EditoraRepository.java
│   └── LivroRepository.java
├── service/        # Lógica de negócio
│   ├── AutorService.java
│   ├── EditoraService.java
│   └── LivroService.java
├── controller/     # Endpoints REST
│   ├── AutorController.java
│   ├── EditoraController.java
│   └── LivroController.java
├── dto/            # Data Transfer Objects
│   └── ModelMapperConfig.java
└── exception/      # Tratamento de exceções
    └── GlobalExceptionHandler.java
```

![Diagrama UML](https://www.plantuml.com/plantuml/svg/dPB1IiGm48RlVOeS5UaBz22xQueKgbtfklSn6KeWcKWoMH3ntKtNOeFkQMyp-V_vCZ_99Z84JivMICc9aO5JxCqfUF5P2I6xSJyyTyUspqqxFWw7hHnokUk7bzTshh-gIbc8KQn3jT6Dw8XnmZ2tR3XPtsHuC3IT3V6DoluzeF8I7rBaxDkPIQ8o3coX7P4q_DKSX0cTn3wfy6zG0qDcWeR8izfx1LO2OnRlSgcIXR2VwMVspMqZJjxeDT4siGyN4v5tMEwF3emj3mYKyGJMQD2hyvVSQfFHS1rR5T354XcqBw_vUOowlbz2DE89wEFW1y92MzQuh9P_eTeWwUJiDm00)

- [Visualizar Diagrama de Classes ↗](class-diagram.png)

## 7. ⚙️ Configuração

### Banco de Dados H2 (em memória)

```properties
spring.datasource.url=jdbc:h2:mem:api_livros
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

### Banco de Dados MySQL

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/api_livros
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 8. 🚀 Como Executar

1. Clone o repositório
2. Configure o banco de dados no `application.properties` (H2 já configurado por padrão)
3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse a documentação Swagger em: `http://localhost:8080/swagger-ui/index.html`

### 🎯 Scripts Prontos para Teste

**Popular banco com dados iniciais**:
```bash
chmod +x populate-data.sh
./populate-data.sh
```

**Executar suite completa de testes**:
```bash
chmod +x test-api.sh
./test-api.sh
```

## 9. 📦 Build e Distribuição

Para gerar o arquivo JAR executável:

```bash
./mvnw clean package
```

O arquivo será gerado em: `target/api-livros-0.0.1-SNAPSHOT.jar`

Para executar o JAR:

```bash
java -jar target/api-livros-0.0.1-SNAPSHOT.jar
```

## 10. 🔒 Extras

- **Spring Security**: Implementação de segurança básica (desafio extra)
- **Documentação de implantação**: Processo de deploy da aplicação

## 11. 📝 Entrega da Atividade

A entrega deve conter:

1. Projeto completo compactado
2. Script SQL do banco de dados (se usar MySQL)
3. Documento com screenshots das requisições aos endpoints e explicações dos resultados

### Screenshots para Entrega:

- 🎬 [Exemplo de execução: populando dados →](INSTRUCTIONS.md#-exemplo-de-execução)
- 🧪 [Exemplo de execução: testando endpoints →](INSTRUCTIONS.md#-exemplo-de-teste-após-inserção-de-dados)

## 12. 🔗 Links Úteis

- **[Instruções de Execução](INSTRUCTIONS.md)** - Guia passo a passo para executar o projeto
- **[Repositório GitHub](https://github.com/andreriffen/api-livros)** - Código fonte completo (opcional)

---

### 13. 📜 Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

---


### 14. ☕ Autor

- 2025 ©️ - [**Andre Riffen**](https://andreriffen.github.io/resume) | [@andreriffen (Github)](https://github.com/andreriffen)
