# INSTRUÇÕES: Atividade Complementar 4 - Continuação da API para Gestão de Livros

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

> 📖 **[Acessar o sumário "README.md" →](README.md)**

- ⭐ **[Repositório GitHub ↗](https://github.com/andreriffen/api-livros)**

- Acessar trecho do terminal [populando dados →](INSTRUCTIONS.md#-exemplo-de-execução)

- Acessar trecho do terminal [testando as endpoints →](INSTRUCTIONS.md#-exemplo-de-teste-após-inserção-de-dados)

---

> *Aluno:* **ANDRE GUILHERME BARRETO DE FARIAS**

> *Matrícula:* **202111701842**

> *Disciplina:* **SWB | Serviços Web**

> *Curso:* **CTDS [3010]/FLN**

---

## 📑 Índice (Instruções de execução)

- [1. 🎯 Início Rápido](#1--início-rápido)
  - [1.1. Pré-requisitos](#11-pré-requisitos)
  - [1.2. Executando a Aplicação](#12-executando-a-aplicação)
- [2. 📡 Acessando a API](#2--acessando-a-api)
  - [2.1. Swagger UI](#21-swagger-ui-recomendado)
  - [2.2. Console do Banco H2](#22-console-do-banco-h2)
- [3. 🧪 Testando a API](#3--testando-a-api)
  - [3.1. População Automática](#31-população-automática-recomendado)
  - [3.2. Testes Manuais](#32-testes-manuais)
  - [3.3. Testando Endpoints Individuais](#33-testando-endpoints-individuais)
- [4. 📋 Endpoints Disponíveis](#4--endpoints-disponíveis)
- [5. 🏗️ Estrutura do Projeto](#5-️-estrutura-do-projeto)
- [6. 🛠️ Tecnologias Utilizadas](#6-️-tecnologias-utilizadas)
- [7. 📦 Build da Aplicação](#7--build-da-aplicação)
- [8. 🔒 Segurança](#8--segurança)
- [9. 📊 Modelo de Dados](#9--modelo-de-dados)
- [10. 🐛 Resolução de Problemas](#10--resolução-de-problemas)
- [11. 🎓 Requisitos da Atividade](#11--requisitos-da-atividade)
- [12. 🔗 Links Úteis](#12--links-úteis)
- [13. 🎬 **Exemplo de Execução (ENTREGA)**](#13--exemplo-de-execução)
- [14. 🧪 Exemplo de Teste Após Inserção de Dados](#14--exemplo-de-teste-após-inserção-de-dados)
- [15. 📜 Licença](#15--licença)
- [16. ☕ Autor @andreriffen](#16--autor)

---

## 1. 🎯 Início Rápido

### 1.1. Pré-requisitos

- **Java 17+** ![Java 17+](https://img.shields.io/badge/Java%2017%2B-007396?style=flat-square&logo=openjdk&logoColor=white)
- **Maven 3.6+** ![Maven 3.6+](https://img.shields.io/badge/Maven%203.6%2B-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
- **Spring Boot** ![Spring Boot 3.5.9](https://img.shields.io/badge/Spring%20Boot%203.5.9-6DB33F?style=flat-square&logo=springboot&logoColor=white)
- **curl** e **jq** (opcional, para testes)

### 1.2. Executando a Aplicação

1. **Navegue até o diretório do projeto:**
   ```bash
   cd api-livros
   ```

2. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
   
   No Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

3. **Aguarde a mensagem de inicialização:**
   ```
   Started ApiLivrosApplication in X.XXX seconds
   ```

## 2. 📡 Acessando a API

### 2.1. Swagger UI (Recomendado)
Abra seu navegador e acesse:
```
http://localhost:8080/swagger-ui/index.html
```

Isso fornece uma interface interativa para testar todos os endpoints.

### 2.2. Console do Banco H2
Acesse o console do banco de dados em memória:
```
http://localhost:8080/h2-console
```

**Detalhes da Conexão:**
- JDBC URL: `jdbc:h2:mem:api_livros`
- Username: `sa`
- Password: *(deixe em branco)*

## 3. 🧪 Testando a API

### 3.1. População Automática (Recomendado)

Popule o banco de dados com dados de exemplo:

```bash
chmod +x populate-data.sh
./populate-data.sh
```

Este script cria:
- 4 autores 
- 3 editoras
- 5 livros

### 3.2. Testes Manuais

Execute a suite completa de testes:

```bash
chmod +x test-api.sh
./test-api.sh
```

### 3.3. Testando Endpoints Individuais

**Criar um Autor:**
```bash
curl -X POST http://localhost:8080/autores \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Martin Fowler",
  "email": "martin@example.com",
  "nacionalidade": "Britânico"
}'
```

**Listar Todos os Autores:**
```bash
curl http://localhost:8080/autores | jq '.'
```

**Criar uma Editora:**
```bash
curl -X POST http://localhost:8080/editoras \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "O'\''Reilly Media",
  "email": "contato@oreilly.com",
  "cidade": "Sebastopol",
  "estado": "CA"
}'
```

**Criar um Livro:**
```bash
curl -X POST http://localhost:8080/livros \
  -H 'Content-Type: application/json' \
  -d '{
  "titulo": "Clean Code",
  "isbn": "978-0132350884",
  "precoDeCusto": 85.00,
  "margemDeLucro": 0.45,
  "status": "DISPONIVEL",
  "autorId": 1,
  "editoraId": 1
}'
```

**Observação:** O preço de venda é calculado automaticamente usando a fórmula:
```
precoDeVenda = precoDeCusto + (precoDeCusto × margemDeLucro)
```

## 4. 📋 Endpoints Disponíveis

### Autores (`/autores`)
- `POST /autores` - Criar novo autor
- `GET /autores` - Listar todos os autores
- `GET /autores/{id}` - Buscar autor por ID
- `PUT /autores/{id}` - Atualizar autor
- `DELETE /autores/{id}` - Deletar autor

### Editoras (`/editoras`)
- `POST /editoras` - Criar nova editora
- `GET /editoras` - Listar todas as editoras
- `GET /editoras/{id}` - Buscar editora por ID
- `PUT /editoras/{id}` - Atualizar editora
- `DELETE /editoras/{id}` - Deletar editora

### Livros (`/livros`)
- `POST /livros` - Criar novo livro
- `GET /livros` - Listar todos os livros
- `GET /livros/{id}` - Buscar livro por ID
- `PUT /livros/{id}` - Atualizar livro
- `DELETE /livros/{id}` - Deletar livro

## 5. 🏗️ Estrutura do Projeto

```
src/main/java/com/example/api_livros/
├── config/           # Classes de configuração (CORS, Security, ModelMapper)
├── controller/       # Endpoints REST
├── dto/              # Data Transfer Objects
├── exception/        # Exceções customizadas e handler global
├── model/            # Entidades JPA
├── repository/       # Repositórios JPA
└── service/          # Lógica de negócio
```

## 6. 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.5.9** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de código boilerplate
- **Jakarta Bean Validation** - Validação de entrada
- **ModelMapper** - Conversão DTO/Entidade
- **SpringDoc OpenAPI** - Documentação da API (Swagger)
- **Spring Security** - Configuração básica de segurança
- **Maven** - Gerenciamento de dependências

## 7. 📦 Build da Aplicação

### Criar arquivo JAR:
```bash
./mvnw clean package
```

O JAR será gerado em: `target/api-livros-0.0.1-SNAPSHOT.jar`

### Executar o JAR:
```bash
java -jar target/api-livros-0.0.1-SNAPSHOT.jar
```

## 8. 🔒 Segurança

A aplicação possui configuração básica de segurança com CORS habilitado e CSRF desabilitado para fins de desenvolvimento. Todos os endpoints são publicamente acessíveis sem autenticação.

Para deploy em produção, considere implementar autenticação e autorização adequadas.

## 9. 📊 Modelo de Dados

### Enum Status do Livro
- `DISPONIVEL` - Disponível
- `ESGOTADO` - Esgotado
- `BLOQUEADO` - Bloqueado

### Lógica de Negócio
A entidade livro automaticamente:
- Calcula o preço de venda baseado no custo e margem de lucro
- Define a data de cadastro na criação
- Valida todos os campos obrigatórios
- Previne ISBNs e emails duplicados

## 10. 🐛 Resolução de Problemas

**Porta 8080 já está em uso:**
```bash
# Encontrar processo usando porta 8080
lsof -i :8080
# Matar o processo
kill -9 <PID>
```

**Maven wrapper não executável:**
```bash
chmod +x mvnw
```

**jq não encontrado:**
```bash
# Ubuntu/Debian
sudo apt-get install jq

# macOS
brew install jq

# Ou omita jq dos comandos curl
curl http://localhost:8080/livros
```

## 11. 🎓 Requisitos da Atividade

Este projeto atende aos requisitos da "Atividade Complementar 4" incluindo:

✅ CRUD completo para todas as entidades  
✅ Mapeamentos JPA/Hibernate  
✅ Padrão DTO com ModelMapper  
✅ Camada de serviço com lógica de negócio  
✅ Tratamento global de exceções  
✅ Bean Validation  
✅ Documentação Swagger  
✅ Integração com Lombok  
✅ Configuração do banco H2  
✅ Configuração básica do Spring Security  

## 12. 🔗 Links Úteis

- **[Sumário do Projeto](README.md)** - Visão geral e documentação completa
- **[Repositório GitHub](https://github.com/andreriffen/api-livros)** - Código fonte completo (opcional)

---

## 13. 🎬 Exemplo de Execução

Demonstração da execução do script `populate-data.sh` no ambiente de desenvolvimento:

```
@andreriffen ➜ /workspaces/api-livros (main) $ ./populate-data.sh
╔════════════════════════════════════════════════════════════════╗
║         📚 Populando API de Livros - Dados Iniciais           ║
╚════════════════════════════════════════════════════════════════╝

👥 Criando Autores...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 Criando autor: Gang of Four (GoF)
   ✅ Autor criado com ID: 1

📝 Criando autor: Robert C. Martin (Uncle Bob)
   ✅ Autor criado com ID: 2

📝 Criando autor: Martin Fowler
   ✅ Autor criado com ID: 3

📝 Criando autor: Eric Evans
   ✅ Autor criado com ID: 4

🏢 Criando Editoras...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 Criando editora: Addison-Wesley
   ✅ Editora criada com ID: 1

📝 Criando editora: O'Reilly Media
   ✅ Editora criada com ID: 2

📝 Criando editora: Bookman (Brasil)
   ✅ Editora criada com ID: 3

📖 Criando Livros...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 Criando livro: Design Patterns: Elements of Reusable Object-Oriented Software
   ✅ Livro criado com ID: 1
   💰 Preço de Custo: R$ 120,00 | Margem: 50% | Preço de Venda: R$ 180,00

📝 Criando livro: Clean Code: A Handbook of Agile Software Craftsmanship
   ✅ Livro criado com ID: 2
   💰 Preço de Custo: R$ 85,00 | Margem: 45% | Preço de Venda: R$ 123,25

📝 Criando livro: Refactoring: Improving the Design of Existing Code
   ✅ Livro criado com ID: 3
   💰 Preço de Custo: R$ 95,00 | Margem: 40% | Preço de Venda: R$ 133,00

📝 Criando livro: Domain-Driven Design: Tackling Complexity in the Heart of Software
   ✅ Livro criado com ID: 4
   💰 Preço de Custo: R$ 110,00 | Margem: 48% | Preço de Venda: R$ 162,80

📝 Criando livro: The Pragmatic Programmer (Edição Brasileira)
   ✅ Livro criado com ID: 5
   💰 Preço de Custo: R$ 75,00 | Margem: 35% | Preço de Venda: R$ 101,25


╔════════════════════════════════════════════════════════════════╗
║                    ✅ DADOS POPULADOS COM SUCESSO!             ║
╚════════════════════════════════════════════════════════════════╝

📊 Resumo:
   • Autores criados: 4
   • Editoras criadas: 3
   • Livros criados: 5

🔍 Para visualizar os dados:
   curl http://localhost:8080/autores | jq '.'
   curl http://localhost:8080/editoras | jq '.'
   curl http://localhost:8080/livros | jq '.'

📖 Acesse o Swagger UI em:
   http://localhost:8080/swagger-ui/index.html

╔════════════════════════════════════════════════════════════════╗
║     Execute agora o script de testes: ./test-api.sh            ║
╚════════════════════════════════════════════════════════════════╝
```

---

## 14. 🧪 Exemplo de Teste Após Inserção de Dados

Demonstração da execução do script `test-api.sh` no terminal:

```
@andreriffen ➜ /workspaces/api-livros (main) $ ./test-api.sh
🚀 Iniciando testes da API de Livros...

📚 ========== AUTORES ==========

➤ 1. POST /autores - Criando William Gibson
{
  "id": 5,
  "nome": "William Gibson",
  "email": "gibson@sprawl.net",
  "nacionalidade": "Americano-Canadense"
}

➤ 2. POST /autores - Criando Neal Stephenson
{
  "id": 6,
  "nome": "Neal Stephenson",
  "email": "neal@metaverse.com",
  "nacionalidade": "Americano"
}

➤ 3. GET /autores - Listando todos os autores
[
  {
    "id": 1,
    "nome": "Gang of Four",
    "email": "gof@designpatterns.com",
    "nacionalidade": "Internacional"
  },
  {
    "id": 2,
    "nome": "Robert C. Martin",
    "email": "unclebob@cleancoder.com",
    "nacionalidade": "Americano"
  },
  {
    "id": 3,
    "nome": "Martin Fowler",
    "email": "martin@refactoring.com",
    "nacionalidade": "Britânico"
  },
  {
    "id": 4,
    "nome": "Eric Evans",
    "email": "eric@domainlanguage.com",
    "nacionalidade": "Americano"
  },
  {
    "id": 5,
    "nome": "William Gibson",
    "email": "gibson@sprawl.net",
    "nacionalidade": "Americano-Canadense"
  },
  {
    "id": 6,
    "nome": "Neal Stephenson",
    "email": "neal@metaverse.com",
    "nacionalidade": "Americano"
  }
]

➤ 4. GET /autores/1 - Buscando autor por ID
{
  "id": 1,
  "nome": "Gang of Four",
  "email": "gof@designpatterns.com",
  "nacionalidade": "Internacional"
}

➤ 5. PUT /autores/1 - Atualizando autor
{
  "timestamp": "2025-11-30T03:02:46.121269998",
  "status": 409,
  "error": "Conflict",
  "message": "Autor já existe com email: 'gibson@sprawl.net'",
  "path": "/autores/1"
}

🏢 ========== EDITORAS ==========

➤ 6. POST /editoras - Criando editora Aleph
{
  "id": 4,
  "nome": "Aleph",
  "email": "contato@aleph.com.br",
  "cidade": "São Paulo",
  "estado": "SP"
}

➤ 7. POST /editoras - Criando editora Suma
{
  "id": 5,
  "nome": "Suma",
  "email": "contato@suma.com.br",
  "cidade": "Rio de Janeiro",
  "estado": "RJ"
}

➤ 8. GET /editoras - Listando todas as editoras
[
  {
    "id": 1,
    "nome": "Addison-Wesley",
    "email": "contato@addison-wesley.com",
    "cidade": "Boston",
    "estado": "MA"
  },
  {
    "id": 2,
    "nome": "OReilly Media",
    "email": "contato@oreilly.com",
    "cidade": "Sebastopol",
    "estado": "CA"
  },
  {
    "id": 3,
    "nome": "Bookman",
    "email": "contato@bookman.com.br",
    "cidade": "Porto Alegre",
    "estado": "RS"
  },
  {
    "id": 4,
    "nome": "Aleph",
    "email": "contato@aleph.com.br",
    "cidade": "São Paulo",
    "estado": "SP"
  },
  {
    "id": 5,
    "nome": "Suma",
    "email": "contato@suma.com.br",
    "cidade": "Rio de Janeiro",
    "estado": "RJ"
  }
]

➤ 9. GET /editoras/1 - Buscando editora por ID
{
  "id": 1,
  "nome": "Addison-Wesley",
  "email": "contato@addison-wesley.com",
  "cidade": "Boston",
  "estado": "MA"
}

➤ 10. PUT /editoras/1 - Atualizando editora
{
  "id": 1,
  "nome": "Aleph",
  "email": "sac@aleph.com.br",
  "cidade": "São Paulo",
  "estado": "SP"
}

📖 ========== LIVROS ==========

➤ 11. POST /livros - Criando Neuromancer
{
  "id": 6,
  "titulo": "Neuromancer",
  "isbn": "978-8576570080",
  "precoDeCusto": 45.00,
  "precoDeVenda": 63.0000,
  "margemDeLucro": 0.40,
  "dataDeCadastro": "2025-11-30",
  "status": "DISPONIVEL",
  "autorId": 1,
  "autorNome": "Gang of Four",
  "editoraId": 1,
  "editoraNome": "Aleph"
}

➤ 12. POST /livros - Criando Snow Crash
{
  "id": 7,
  "titulo": "Snow Crash",
  "isbn": "978-8576572459",
  "precoDeCusto": 50.00,
  "precoDeVenda": 67.5000,
  "margemDeLucro": 0.35,
  "dataDeCadastro": "2025-11-30",
  "status": "DISPONIVEL",
  "autorId": 2,
  "autorNome": "Robert C. Martin",
  "editoraId": 1,
  "editoraNome": "Aleph"
}

➤ 13. GET /livros - Listando todos os livros
[
  {
    "id": 1,
    "titulo": "Design Patterns: Elements of Reusable Object-Oriented Software",
    "isbn": "978-0201633610",
    "precoDeCusto": 120.00,
    "precoDeVenda": 180.00,
    "margemDeLucro": 0.50,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 1,
    "autorNome": "Gang of Four",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 2,
    "titulo": "Clean Code: A Handbook of Agile Software Craftsmanship",
    "isbn": "978-0132350884",
    "precoDeCusto": 85.00,
    "precoDeVenda": 123.25,
    "margemDeLucro": 0.45,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 2,
    "autorNome": "Robert C. Martin",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 3,
    "titulo": "Refactoring: Improving the Design of Existing Code",
    "isbn": "978-0201485677",
    "precoDeCusto": 95.00,
    "precoDeVenda": 133.00,
    "margemDeLucro": 0.40,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 3,
    "autorNome": "Martin Fowler",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 4,
    "titulo": "Domain-Driven Design: Tackling Complexity in the Heart of Software",
    "isbn": "978-0321125217",
    "precoDeCusto": 110.00,
    "precoDeVenda": 162.80,
    "margemDeLucro": 0.48,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 4,
    "autorNome": "Eric Evans",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 5,
    "titulo": "The Pragmatic Programmer",
    "isbn": "978-8577807017",
    "precoDeCusto": 75.00,
    "precoDeVenda": 101.25,
    "margemDeLucro": 0.35,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 2,
    "autorNome": "Robert C. Martin",
    "editoraId": 3,
    "editoraNome": "Bookman"
  },
  {
    "id": 6,
    "titulo": "Neuromancer",
    "isbn": "978-8576570080",
    "precoDeCusto": 45.00,
    "precoDeVenda": 63.00,
    "margemDeLucro": 0.40,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 1,
    "autorNome": "Gang of Four",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 7,
    "titulo": "Snow Crash",
    "isbn": "978-8576572459",
    "precoDeCusto": 50.00,
    "precoDeVenda": 67.50,
    "margemDeLucro": 0.35,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 2,
    "autorNome": "Robert C. Martin",
    "editoraId": 1,
    "editoraNome": "Aleph"
  }
]

➤ 14. GET /livros/1 - Buscando livro por ID
{
  "id": 1,
  "titulo": "Design Patterns: Elements of Reusable Object-Oriented Software",
  "isbn": "978-0201633610",
  "precoDeCusto": 120.00,
  "precoDeVenda": 180.00,
  "margemDeLucro": 0.50,
  "dataDeCadastro": "2025-11-30",
  "status": "DISPONIVEL",
  "autorId": 1,
  "autorNome": "Gang of Four",
  "editoraId": 1,
  "editoraNome": "Aleph"
}

➤ 15. PUT /livros/1 - Atualizando status do livro para ESGOTADO
{
  "timestamp": "2025-11-30T03:02:46.272617909",
  "status": 409,
  "error": "Conflict",
  "message": "Livro já existe com isbn: '978-8576570080'",
  "path": "/livros/1"
}

⚠️  ========== TESTES DE VALIDAÇÃO ==========

➤ 16. POST /autores - Tentando criar autor com email duplicado (deve falhar)
{
  "timestamp": "2025-11-30T03:02:46.28473857",
  "status": 409,
  "error": "Conflict",
  "message": "Autor já existe com email: 'gibson@sprawl.net'",
  "path": "/autores"
}

➤ 17. GET /autores/999 - Buscando autor inexistente (deve retornar 404)
{
  "timestamp": "2025-11-30T03:02:46.295144241",
  "status": 404,
  "error": "Not Found",
  "message": "Autor não encontrado(a) com id: '999'",
  "path": "/autores/999"
}

➤ 18. POST /livros - Tentando criar livro com ISBN duplicado (deve falhar)
{
  "timestamp": "2025-11-30T03:02:46.308646189",
  "status": 409,
  "error": "Conflict",
  "message": "Livro já existe com isbn: '978-8576570080'",
  "path": "/livros"
}

🗑️  ========== TESTES DE DELEÇÃO ==========

➤ 19. DELETE /livros/2 - Deletando livro Snow Crash

HTTP Status: 204

➤ 20. GET /livros - Verificando que o livro foi deletado
[
  {
    "id": 1,
    "titulo": "Design Patterns: Elements of Reusable Object-Oriented Software",
    "isbn": "978-0201633610",
    "precoDeCusto": 120.00,
    "precoDeVenda": 180.00,
    "margemDeLucro": 0.50,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 1,
    "autorNome": "Gang of Four",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 3,
    "titulo": "Refactoring: Improving the Design of Existing Code",
    "isbn": "978-0201485677",
    "precoDeCusto": 95.00,
    "precoDeVenda": 133.00,
    "margemDeLucro": 0.40,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 3,
    "autorNome": "Martin Fowler",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 4,
    "titulo": "Domain-Driven Design: Tackling Complexity in the Heart of Software",
    "isbn": "978-0321125217",
    "precoDeCusto": 110.00,
    "precoDeVenda": 162.80,
    "margemDeLucro": 0.48,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 4,
    "autorNome": "Eric Evans",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 5,
    "titulo": "The Pragmatic Programmer",
    "isbn": "978-8577807017",
    "precoDeCusto": 75.00,
    "precoDeVenda": 101.25,
    "margemDeLucro": 0.35,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 2,
    "autorNome": "Robert C. Martin",
    "editoraId": 3,
    "editoraNome": "Bookman"
  },
  {
    "id": 6,
    "titulo": "Neuromancer",
    "isbn": "978-8576570080",
    "precoDeCusto": 45.00,
    "precoDeVenda": 63.00,
    "margemDeLucro": 0.40,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 1,
    "autorNome": "Gang of Four",
    "editoraId": 1,
    "editoraNome": "Aleph"
  },
  {
    "id": 7,
    "titulo": "Snow Crash",
    "isbn": "978-8576572459",
    "precoDeCusto": 50.00,
    "precoDeVenda": 67.50,
    "margemDeLucro": 0.35,
    "dataDeCadastro": "2025-11-30",
    "status": "DISPONIVEL",
    "autorId": 2,
    "autorNome": "Robert C. Martin",
    "editoraId": 1,
    "editoraNome": "Aleph"
  }
]

✅ Testes concluídos!
```

---

### 15. 📜 Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

---


### 16. ☕ Autor

- 2025 ©️ - [**Andre Riffen**](https://andreriffen.github.io/resume) | [@andreriffen (Github)](https://github.com/andreriffen)
