# INCAAS - Gestão de Processos Judiciais com Agendamento de Audiências

Este projeto é uma API **Spring Boot** para gerenciamento de processos judiciais e suas audiências, desenvolvida como desafio técnico para desenvolvedor backend.

---

## 🔹 Tecnologias utilizadas

- Java 21
- Spring Boot 
- Spring Data JPA
- H2 Database (em memória)
- Lombok
- Swagger (SpringDoc OpenAPI)
- Maven

---

## 🔹 Estrutura do Projeto

```
src/main/java
└── br.com.incaas
    ├── model
    │   ├── Processo.java
    │   ├── Audiencia.java
    │   └── enums
    │        ├── StatusProcesso.java
    │        └── TipoAudiencia.java
    ├── repository
    │   ├── ProcessoRepository.java
    │   └── AudienciaRepository.java
    ├── service
    │   ├── ProcessoService.java
    │   └── AudienciaService.java
    └── controller
        ├── ProcessoController.java
        └── AudienciaController.java
```

---

## 🔹 Funcionalidades

### Processo
- Cadastro de processo judicial com campos:
  - número do processo (único e validado por regex)
  - vara
  - comarca
  - assunto
  - status (ATIVO, ARQUIVADO, SUSPENSO)
- Listagem e filtragem por `status` e `comarca`

### Audiência
- Cada processo pode ter uma ou mais audiências
- Campos da audiência:
  - data/hora de início e fim
  - tipo (CONCILIAÇÃO, INSTRUÇÃO, JULGAMENTO)
  - local
- Regras:
  - Não permite sobreposição de audiências na mesma vara e local
  - Só pode marcar dias úteis (segunda a sexta)
  - Processos arquivados ou suspensos não podem receber novas audiências

### Consulta de Agenda
- Endpoint que retorna a agenda de audiências de uma comarca em um determinado dia

---

## 🔹 Regras de Negócio
- Número do processo segue o padrão: `0000000-00.0000.0.00.0000`
- Validações usando `@Pattern` e Bean Validation
- Enumerações salvas no banco como texto (`@Enumerated(EnumType.STRING)`)

---

## 🔹 Rodando o Projeto

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/incaas.git
```

2. Acesse o diretório do projeto:
```bash
cd incaas
```

3. Rode o projeto:
```bash
mvn spring-boot:run
```

4. Acesse a documentação Swagger:
```
http://localhost:8080/swagger-ui/index.html
```

5. O banco H2 pode ser acessado em:
```
http://localhost:8080/h2-console
```
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (em branco)

---

## 🔹 Testes
- Testes unitários e de integração estão localizados no pacote `src/test/java`.  
- Rodar testes com Maven:
```bash
mvn test
```

---

## 🔹 Diferenciais Implementáveis
- Versionamento da API (`/api/v1`)
- Validação personalizada com anotações (`@Valid`, `@Constraint`)
- Autenticação simples via token JWT

---

## 🔹 Autor
**Robson Alves de Alencastro**

---

## 🔹 Observações
Este projeto é voltado para demonstração de **Spring Boot, JPA, validação e boas práticas de arquitetura**, servindo como base para desafios técnicos e estudo de backend Java.
