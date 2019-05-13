# PackBack - API

API para cadastro e consultas de materiais recicláveis disponíveis para coleta do sistema PackBack.

### Build e Execução

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