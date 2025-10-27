# ⚡ API-REDIS - EcoFactory

API REST desenvolvida em **Spring Boot** para o gerenciamento de dados e cache do projeto **EcoFactory Calbon**. Esta API utiliza **Redis** como um *datastore* de alta velocidade.

A API está hospedada na plataforma **Render**.

[![Status](https://img.shields.io/badge/Status-Live-brightgreen)](#)

### Links de Acesso (Produção)

* **API Base URL:** `https://api-redis-ok9n.onrender.com`
* **Documentação (Swagger):** `https://api-redis-ok9n.onrender.com/swagger-ui.html`

---

## 🚀 Tecnologias Utilizadas

O *stack* principal do projeto é construído em torno do ecossistema Java e Spring, com deploy automatizado no Render.

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
<br/>
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
<br/>
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
<br/>
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
<br/>
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
<br/>
![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=black)
<br/>
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

---

## ⚙️ Configuração de Ambiente

O projeto utiliza variáveis de ambiente para configurar a conexão com o Redis.

### 1. Ambiente de Produção (Render)

No Render, as variáveis de ambiente **não** são lidas de um arquivo `.env`. Elas devem ser configuradas diretamente no dashboard do seu serviço:

1.  Vá para a seção "Environment" do seu serviço no Render.
2.  Adicione a seguinte variável (o Spring Boot irá lê-la automaticamente):
    * `SPRING_DATA_REDIS_URL`: (A sua string de conexão completa do Redis, ex: `redis://:password@host:port`)

*Nota: O Render (e outros serviços de Redis) fornece uma única `SPRING_DATA_REDIS_URL` que já contém o host, porta e senha.*

### 2. Ambiente de Desenvolvimento (Local)

Para desenvolvimento local, crie um arquivo `.env` na raiz do projeto. Este arquivo **não deve ser versionado** (`.gitignore`).

**Exemplo de `.env`:**

* `DB_URL=rediss://sua-URL`
* `DB_USERNAME=username`
* `DB_PASSWORD=SuaSenha`


## 📖 Documentação da API (Endpoints)

Este projeto utiliza Swagger (OpenAPI) para documentação interativa.

### Acesso Local
* **URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Acesso em Produção
* **URL:** [https://api-redis-ok9n.onrender.com/swagger-ui/index.html](https://api-redis-ok9n.onrender.com/swagger-ui/index.html)
