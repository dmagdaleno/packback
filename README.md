# PackBack - API

API para cadastro e consultas de materiais recicláveis disponíveis para coleta do sistema PackBack.

## Build e Execução

Informações necessárias para a construir e rodar o projeto.

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

Para rodar o banco na VM:
```
docker run --rm --name pg-packback-db -d -p 5432:5432 -v /home/diegommagdaleno/work/packback/db/volume/postgres:/var/lib/postgresql/data postgres:v0.0.1
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

```
http://35.247.211.12:8080/packback/api/swagger-ui.html
```