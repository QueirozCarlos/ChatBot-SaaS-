# 🤖 Shopsync - Sistema de estoque e Vendas

Um sistema completo de estoque e vendas, desenvolvido em Spring Boot com geração de relatórios.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [API Endpoints](#api-endpoints)
- [Segurança](#segurança)
- [Deploy com Docker](#deploy-com-docker)
- [Contribuição](#contribuição)
- [Licença](#licença)

## 🎯 Sobre o Projeto

O Shopsync é uma plataforma completa de gestão de vendas que combina funcionalidades tradicionais de CRM e automatização de processos comerciais. O sistema oferece:

- **Gestão de Usuários**: Sistema de autenticação com roles (ADMIN/USER)
- **Gestão de Produtos**: Cadastro e controle de estoque
- **Gestão de Vendas**: Registro e acompanhamento de vendas
- **Relatórios**: Geração automática de relatórios
- **Notificações**: Sistema de notificações - Atualizações futuras
- **API REST**: Interface completa para integração

## ✨ Funcionalidades

### 🔐 Autenticação e Autorização
- Login/Registro de usuários
- JWT Token com refresh automático
- Controle de acesso baseado em roles
- Criptografia de senhas com BCrypt

### 📦 Gestão de Produtos
- Cadastro de produtos com nome, descrição, preço e estoque
- Controle de quantidade em estoque
- Busca e listagem de produtos
- Permissões diferenciadas por role

### 💰 Gestão de Vendas
- Registro de vendas com produtos e quantidades
- Histórico de vendas por usuário
- Controle de acesso por role (ADMIN pode ver todas, USER apenas suas)

### 📊 Relatórios
- Formatação CSV para relatórios
- Dados dinâmicos do banco de dados
- Acesso restrito a administradores

### 🔔 Sistema de Notificações - Atualizações futuras
- Envio de notificações
- Controle de notificações por administradores

## 🛠 Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.2.3** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Spring AI** - Integração com OpenAI - Atualizações futuras
- **MySQL 8.0** - Banco de dados principal
- **H2 Database** - Banco de dados para testes
- **JWT** - Tokens de autenticação
- **Swagger/OpenAPI** - Documentação da API

### DevOps & Infraestrutura
- **Docker** - Containerização
- **Docker Compose** - Orquestração de containers
- **Maven** - Gerenciamento de dependências

## 🏗 Arquitetura

```
src/main/java/com/sistema/chatbot/
├── ChatbotVendasApplication.java    # Classe principal
├── config/                          # Configurações
├── controller/                      # Controladores REST
│   ├── AuthenticationController.java
│   ├── ProductController.java
│   ├── SaleController.java
│   ├── ReportController.java
│   ├── UserController.java
│   └── NotificationController.java
├── model/                          # Entidades e DTOs
│   ├── UserEntity.java
│   ├── Product.java
│   ├── Sale.java
│   ├── Report.java
│   └── ...
├── repository/                     # Repositórios JPA
├── service/                        # Lógica de negócio
├── security/                       # Configurações de segurança
└── dto/                           # Data Transfer Objects
```

## 📋 Pré-requisitos

- **Java 21** ou superior
- **Maven 3.6+**
- **MySQL 8.0** ou **Docker**
- **OpenAI API Key** (para funcionalidades de IA)

## 🚀 Instalação

### Opção 1: Execução Local

1. **Clone o repositório**
```bash
git clone https://github.com/QueirozCarlos/Shopsync-backend.git
cd ChatBot-SaaS-
```

2. **Configure as variáveis de ambiente**
```bash
cp .env.example .env
# Edite o arquivo .env com suas configurações
```

3. **Configure o banco de dados MySQL**
```sql
CREATE DATABASE ChatSaaS_DB;
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON ChatSaaS_DB.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

4. **Execute o projeto**
```bash
mvn spring-boot:run
```

### Opção 2: Docker Compose (Recomendado)

1. **Clone e configure**
```bash
git clone https://github.com/QueirozCarlos/Shopsync-backend.git
cd ChatBot-SaaS-
cp .env.example .env
# Configure suas variáveis de ambiente
```

2. **Execute com Docker**
```bash
docker-compose up -d
```

## ⚙️ Configuração

### Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto:

```env
# OpenAI API Key (obrigatório para relatórios IA)
OPENAI-API-KEY=your-openai-api-key

# JWT Secrets (recomendado alterar em produção)
JWT_SECRET=your-jwt-secret-key
JWT_REFRESH_SECRET=your-jwt-refresh-secret-key
```

### Configurações do Banco de Dados

As configurações padrão estão em `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ChatSaaS_DB
spring.datasource.username=root
spring.datasource.password=root
```

## 📖 Uso

### Acessando a Aplicação

- **API Base URL**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/api-docs`

### Primeiro Acesso

1. **Registre um usuário administrador**:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nomeCompleto": "Admin User",
    "email": "admin@example.com",
    "password": "password123",
    "telefone": "11999999999",
    "role": "ADMIN"
  }'
```

2. **Faça login**:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "password123"
  }'
```

3. **Use o token retornado** para acessar outros endpoints

## 🔌 API Endpoints

### Autenticação
- `POST /api/auth/register` - Registrar novo usuário
- `POST /api/auth/login` - Fazer login
- `POST /api/auth/refresh` - Renovar token

### Produtos
- `GET /api/products` - Listar produtos (público)
- `POST /api/products` - Criar produto (USER+)
- `PUT /api/products` - Atualizar produto (USER+)

### Vendas
- `GET /api/sales` - Listar vendas (USER+)
- `POST /api/sales` - Criar venda (ADMIN)
- `DELETE /api/sales/{id}` - Deletar venda (ADMIN)

### Relatórios
- `GET /api/reports` - Gerar relatórios (ADMIN)

### Usuários
- `GET /api/users` - Listar usuários (ADMIN)

### Notificações
- `PUT /api/notifications` - Gerenciar notificações (ADMIN)

### ChatBot
- `GET /hello` - Gerar relatório HTML com IA

## 🔒 Segurança

### Roles e Permissões

- **ADMIN**: Acesso completo ao sistema
- **USER**: Acesso limitado a produtos e vendas próprias

### Autenticação

- JWT Tokens com expiração
- Refresh tokens para renovação automática
- Senhas criptografadas com BCrypt
- Proteção CSRF desabilitada (API REST)

### Endpoints Seguros

Todos os endpoints (exceto `/api/auth/**` e `/api/products` GET) requerem autenticação.

## 🐳 Deploy com Docker

### Build da Imagem
```bash
docker build -t chatbot-saas .
```

### Execução com Docker Compose
```bash
# Desenvolvimento
docker-compose up -d

# Produção (com volumes persistentes)
docker-compose -f docker-compose.prod.yml up -d
```

### Variáveis de Ambiente para Produção
```env
OPENAI-API-KEY=your-production-openai-key
JWT_SECRET=your-production-jwt-secret
JWT_REFRESH_SECRET=your-production-refresh-secret
SPRING_PROFILES_ACTIVE=prod
```

## 📝 Logs

Os logs são salvos em:
- **Console**: Logs em tempo real
- **Arquivo**: `logs/chatbot-vendas.log`

### Níveis de Log
- **INFO**: Informações gerais
- **DEBUG**: Detalhes de desenvolvimento
- **ERROR**: Erros e exceções

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura
mvn jacoco:report
```

### Padrões de Código

- Use Java 21 features
- Siga as convenções do Spring Boot
- Documente APIs com Swagger
- Escreva testes unitários
- Use nomes descritivos para variáveis e métodos

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Para suporte e dúvidas:
- Abra uma issue no GitHub
- Entre em contato: carlos.aqrodrigues@hotmail.com

## 🔄 Changelog

### v1.0.0
- Sistema base de autenticação
- Gestão de produtos e vendas
- Integração com OpenAI
- API REST completa
- Documentação Swagger
- Deploy com Docker

---

**Desenvolvido com ❤️ usando java e Spring Boot
