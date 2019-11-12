docker build -t pg-packback-db:v0.0.4 docker/db/

docker run --rm -d --name pg-packback-db -p 5432:5432 pg-packback-db:v0.0.4

docker run --rm -d \
 --name pg-packback-db \
 -p 5432:5432 \
 -v $pwd/docker/db/volume/postgres:/var/lib/postgresql/data \
 pg-packback-db:v0.0.4

docker exec -it pg-packback-db bash

psql -U postgres

pg_dump -U postgres -s packback_db > packback_db_dump_schema.ddl