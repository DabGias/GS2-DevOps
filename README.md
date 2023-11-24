<h1 align="center">🌐 Realizando deploy da API Spring 🌐</h1>
<h2 align="center">📝 Requisitos 📝</h2>

> [!IMPORTANT]
>Primeiramente precisamos criar o banco de dados (SQL da Azure) e o app Spring (Aplicativos do Azure Spring). Após ambos os serviços estarem disponíveis vá até o recurso de banco de dados na Azure e procure pela URL de conexão do seu banco de dados; depois da criação destes dois serviços devemos clonar o repositório (`git clone https://github.com/DabGias/GS2-DevOps`), devemos garantir que o banco de dados e o app Spring estão devidamente implantados na nuvem; tendo a sua URL de conexão do banco de dados salva, substitua o valor da propriedade `spring.datasource.url` pela URL de conexão ( **ATENÇÃO: NÃO SE ESQUEÇA DE MODIFICAR A SENHA DO SEU USUÁRIO NA URL DE CONEXÃO**).

<h2 align="center">🔗 API 🔗</h2>

Agora basta testar a API através da URL fornecida como "Teste de ponto de extremidade" dentro do app criado no serviço de Aplicativos do Azure Spring.

No nosso código possuímos 3 entidades, sendo elas, Consulta, Médico e Paciente, cada uma com seus *endpoints* e métodos `GET`, `POST`, `PUT` e `DELETE`.

<h2 align="center">Consulta <code>("/consultas")</code></h2>

`GET`

```json
{
    "id": 1,
    "motivo": "Tosse persistente",
    "diagnostico": "Bronquite",
    "data": "2023-11-24",
    "paciente": {
        "id": 1,
        "nome": "Julia e Silva",
        "cpf": "721.731.650-84",
        "email": "julia@teste.com"
    },
    "medico": {
        "id": 1,
        "nome": "Dr. Lucas Aquino",
        "email": "lucas@fiap.com.br",
        "cpf": "111.111.111-11",
        "crm": "CRM111"
    }
}
```

`GET ("/{id}")`

```json
{
    "id": 1,
    "motivo": "Tosse persistente",
    "diagnostico": "Bronquite",
    "data": "2023-11-24",
    "paciente": {
        "id": 1,
        "nome": "Julia e Silva",
        "cpf": "721.731.650-84",
        "email": "julia@teste.com"
    },
    "medico": {
        "id": 1,
        "nome": "Dr. Lucas Aquino",
        "email": "lucas@fiap.com.br",
        "cpf": "111.111.111-11",
        "crm": "CRM111"
    }
}
```

`POST`

```json
{
    "motivo": "Dor no útero",
    "diagnostico": "Ovário Policístico",
    "data": "2023-01-25",
    "paciente": {
        "nome": "Bianca Carvalho",
        "cpf": "636.781.390-00",
        "email": "carvalho@email.com.br"
    },
    "medico": {
        "nome": "Dr. Matheus Silva Lima",
        "email": "lima@email.com.br",
        "cpf": "215.396.810-09",
        "crm": "CRM675"
    }
}
```

`PUT ("/{id}")`

```json
{
    "id": 6,
    "motivo": "Dor no útero",
    "diagnostico": "Endometriose",
    "data": "2023-01-25",
    "paciente": {
        "id":6,
        "nome": "Bianca Carvalho",
        "cpf": "636.781.390-00",
        "email": "bianca.carvalho@email.com"
    },
    "medico": {
        "id":6,
        "nome": "Dr. Matheus Silva Lima",
        "email": "lima@email.com.br",
        "cpf": "215.396.810-09",
        "crm": "CRM675"
    }
}
```

`DELETE ("/{id}")`

```json
204 No Content
```

<h2 align="center">Médico <code>("/medico")</code></h2>

`GET`

```json
{
    "id": 1,
    "nome": "Dr. Lucas Aquino",
    "email": "lucas@fiap.com.br",
    "cpf": "111.111.111-11",
    "crm": "CRM111"
}
```

`GET ("/{id}")`

```json
{
    "id": 1,
    "nome": "Dr. Lucas Aquino",
    "email": "lucas@fiap.com.br",
    "cpf": "111.111.111-11",
    "crm": "CRM111"
}
```

`POST`

```json
{
    "nome": "Dr. Matheus Silva Lima",
    "email": "lima@email.com.br",
    "cpf": "215.396.810-09",
    "crm": "CRM675"
}
```

`PUT ("/{id}")`

```json
{
    "id":6,
    "nome": "Dr. Matheus Silva Lima",
    "email": "lima@email.com.br",
    "cpf": "215.396.810-09",
    "crm": "CRM675"
}
```

`DELETE ("/{id}")`

```json
204 No Content
```

<h2 align="center">Paciente <code>("/pacientes")</code></h2>

`GET`

```json
{
    "id": 1,
    "nome": "Julia e Silva",
    "cpf": "721.731.650-84",
    "email": "julia@teste.com"
}
```

`GET ("/{id}")`

```json
{
    "id": 1,
    "nome": "Julia e Silva",
    "cpf": "721.731.650-84",
    "email": "julia@teste.com"
}
```

`POST`

```json
{
    "nome": "Bianca Carvalho",
    "cpf": "636.781.390-00",
    "email": "carvalho@email.com.br"
}
```

`PUT ("/{id}")`

```json
{
    "id":6,
    "nome": "Bianca Carvalho",
    "cpf": "636.781.390-00",
    "email": "bianca.carvalho@email.com"
}
```

`DELETE ("/{id}")`

```json
204 No Content
```
