# PackBack - API

API para cadastro e consultas de materiais recicláveis disponíveis para coleta do sistema PackBack.

## Acesso em Cloud

Documentação completa da API:

```
http://packback.brazilsouth.cloudapp.azure.com:8080/packback/api/swagger-ui.html
```

### Principais endpoints

#### Movimentações

Recupera todas as movimentações realizadas (`GET`):
```
http://packback.brazilsouth.cloudapp.azure.com:8080/packback/api/movimentacoes
```

Recupera uma movimentação específica por `id` (`GET`):
```
http://packback.brazilsouth.cloudapp.azure.com:8080/packback/api/movimentacoes/{id}
```

Recupera todas as movimentações realizadas por um usuário (`GET`):
```
http://packback.brazilsouth.cloudapp.azure.com:8080/packback/api/movimentacoes/usuario/{idUsuario}
```

Registra uma nova movimentação (`POST`):
```
http://packback.brazilsouth.cloudapp.azure.com:8080/packback/api/movimentacoes/movimenta/{idEmbalagem}/de/{idUsuarioOrigem}/para/{idUsuarioDestino}
```

#### Pontuação

Recupera todas as pontuações de um usuário pelo seu `id` (`GET`):
```
http://packback.brazilsouth.cloudapp.azure.com:8080/packback/api/pontuacoes/{idUsuario}
```

## Build e Execução Local

Informações necessárias para construir e rodar o projeto.

### Pré-requisitos

- Docker 18.x
- JDK 12

### Build (Gradle e Docker)

O Gradle Wrapper, por padrão, busca o JDK que será utilizado para o build pela variável de ambiente JAVA_HOME.
Portanto, é importante colocar essa variável no PATH do seu Sistema Operacional antes de rodar o comando do Gradle.

No `Ubuntu` isso é feito com o comando: `export JAVA_HOME=/caminho/do/java`

Fazer o build da aplicação com Gradle:
```
./gradlew clean build
```

Copiar JAR da aplicação para o diretório do docker (exemplo usando `Ubuntu`):
```
cp build/libs/packback*.jar docker/packback.jar
```

Construir imagem docker do banco:
```
docker build -t pg-packback-db:v0.0.4 docker/db/
```

Construir imagem docker da aplicação
```
docker build -t packback:v0.0.6 -t packback:latest docker/
```

### Execução (Docker Compose)

Utilizando o docker compose, basta executar o comando abaixo para subir as imagens do banco e da aplicação:

```
docker-compose -f docker/docker-compose.yaml up -d
```

### Uso

Verifique se a aplicação subiu corretamente acessando a documentação:
```
http://localhost:8080/packback/api/swagger-ui.html
```

**Observação:** Assim que o docker compose completa a execução, significa que as imagens docker já estão prontas, 
porém, pode ser que o Spring Boot ainda não tenha concluído. Caso a URL acima não funcione imediatamente, 
aguarde alguns segundos e tente novamente.
