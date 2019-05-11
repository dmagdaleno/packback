CREATE DATABASE packback_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.utf8' 
    LC_CTYPE = 'pt_BR.utf8';

\c packback_db

CREATE SEQUENCE usuario_id_seq START 1;

CREATE SEQUENCE endereco_id_seq START 1;

CREATE TABLE "Usuario" (
   "id" bigint NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
   "email" character varying(256) NOT NULL UNIQUE,
   "nome" character varying(256),
   "cpf" character varying(16),
   "razao_social" character varying(256),
   "cnpj" character varying(24),
   CONSTRAINT usuario_pkey PRIMARY KEY ("id")
)
WITH (OIDS = FALSE);

CREATE TABLE "Endereco" (
    "id" bigint NOT NULL DEFAULT nextval('endereco_id_seq'::regclass),
    "id_usuario" bigint NOT NULL,
    "cep" character varying(8),
    "rua" character varying(256),
    "numero" bigint,
    "bairro" character varying(128),
    "cidade" character varying(128),
    "estado" character varying(128),
    "pais" character varying(64),
    CONSTRAINT endereco_pkey PRIMARY KEY ("id"),
    CONSTRAINT usuario_fkey FOREIGN KEY ("id_usuario")
        REFERENCES "Usuario"("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE);
