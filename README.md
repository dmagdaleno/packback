# PackBack - API

API para cadastro e consultas de materiais recicláveis disponíveis para coleta do sistema PackBack.

## Build e Execução

Informações necessárias para construir e rodar o projeto.

### Pré-requisitos

- Docker 18.x
- Java 12

### Passos com Docker

Construir imagem do banco:
```
docker build -t postgres:v0.0.1 docker/db/
```

Construir imagem da aplicação
```
docker build -t packback:v0.0.2 -t packback:latest docker/
```

docker network create pbnet

Executar o banco:
```
docker run --rm --name pg-packback-db --net pbnet -d -p 5432:5432 -v docker/db/volume/postgres:/var/lib/postgresql/data postgres:v0.0.1
```

Executar a aplicação:
```
docker run --rm --name packback --net pbnet -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=docker packback:v0.0.2
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

Registra uma nova movimentação:
```
http://35.247.211.12:8080/packback/api/movimentacoes/movimenta/{idEmbalagem}/de/{idUsuarioOrigem}/para/{idUsuarioDestino}
```