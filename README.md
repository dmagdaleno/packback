# PackBack - API

API para cadastro e consultas de materiais recicláveis disponíveis para coleta do sistema PackBack.

## Build e Execução

Informações necessárias para construir e rodar o projeto.

### Pré-requisitos

- Docker 18.x
- Java 12

### Passos

Construir imagem do banco:
```
docker build -t postgres:v0.0.1 db/
```

Executar o banco:
```
docker run --rm --name pg-packback-db -d -p 5432:5432 -v $pwd/db/volume/postgres:/var/lib/postgresql/data postgres:v0.0.1
```

Rodar a aplicação:
```
./gradlew bootRun
```

## Uso

Documentação da API:
```
http://localhost:8080/packback/api/swagger-ui.html
```


## Acesso em Cloud

Documentação da API:

```
http://35.247.211.12:8080/packback/api/swagger-ui.html
```

### Endpoint principal

Recupera todas as movimentações realizadas:
```
http://35.247.211.12:8080/packback/api/movimentacoes
```

Recupera uma movimentação específica por id:
```
http://35.247.211.12:8080/packback/api/movimentacoes/{id}
```

Reegistra uma nova movimentação:
```
http://35.247.211.12:8080/packback/api/movimentacoes/movimenta/{idEmbalagem}/de/{idUsuarioOrigem}/para/{idUsuarioDestino}
```