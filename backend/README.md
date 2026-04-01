# Backend LivebFlow

API REST em Spring Boot para cadastro de imobiliarias usando PostgreSQL.

## Requisitos

- Java 17
- Maven 3.9+
- PostgreSQL

## Banco de dados

Crie um banco chamado `livebflow` no PostgreSQL. Ao subir a aplicacao, o Spring executa o arquivo `src/main/resources/schema.sql` para criar as tabelas iniciais.

Credenciais padrao em `src/main/resources/application.properties`:

- URL: `jdbc:postgresql://localhost:5432/livebflow`
- Usuario: `postgres`
- Senha: `postgres`

## Executar

```bash
mvn spring-boot:run
```

## Endpoints de imobiliarias

- `GET /api/imobiliarias`
- `GET /api/imobiliarias/{id}`
- `POST /api/imobiliarias`
- `PUT /api/imobiliarias/{id}`
- `DELETE /api/imobiliarias/{id}`

## Exemplo de payload

```json
{
  "razaoSocial": "Imobiliaria Exemplo LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "contato@imobiliaria.com.br",
  "telefone": "(11) 99999-0000"
}
```
