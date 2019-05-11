FROM postgres:9.4.20

ENV LANG pt_BR.utf8

COPY scripts/init.ddl /docker-entrypoint-initdb.d/

ENV PATH $PATH:/usr/lib/postgresql/$PG_MAJOR/bin
ENV PGDATA /var/lib/postgresql/data
ENV POSTGRES_DB packback_db
ENV POSTGRES_PASSWORD pg_packback_senha

RUN mkdir -p "$PGDATA" && chown -R postgres:postgres "$PGDATA" && chmod 777 "$PGDATA"
VOLUME /var/lib/postgresql/data

EXPOSE 5432
CMD ["postgres"]
