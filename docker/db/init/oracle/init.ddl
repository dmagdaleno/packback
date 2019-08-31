
CREATE TABLE embalagem (
    id integer NOT NULL,
    descricao character varying(255),
    peso double precision,
    volume double precision,
    tipo_id integer
);

CREATE SEQUENCE embalagem_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE;

CREATE TABLE endereco (
    id integer NOT NULL,
    bairro character varying(255),
    cep character varying(255),
    cidade character varying(255),
    estado character varying(255),
    numero integer,
    pais character varying(255),
    rua character varying(255),
    id_usuario integer
);

CREATE SEQUENCE endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE;

CREATE TABLE movimentacao (
    id integer NOT NULL,
    data timestamp,
    id_embalagem integer,
    id_usuario_destino integer,
    id_usuario_origem integer
);

CREATE TABLE pontuacao (
    id integer NOT NULL,
    pontos double precision,
    id_usuario integer
);

CREATE SEQUENCE pontuacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE;

CREATE SEQUENCE pontuacao_usuario_seq
    START WITH 1
    INCREMENT BY 50
    NOMINVALUE
    NOMAXVALUE
    NOCACHE;

CREATE TABLE tipo_embalagem (
    id integer NOT NULL,
    descricao character varying(255),
    material integer
);

CREATE SEQUENCE tp_embalagem_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE;

CREATE TABLE usuario (
    id integer NOT NULL,
    cnpj character varying(255),
    cpf character varying(255),
    email character varying(255),
    nome character varying(255),
    razao_social character varying(255),
    tipo integer
);

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE;


ALTER TABLE embalagem
    ADD CONSTRAINT embalagem_pkey PRIMARY KEY (id);

ALTER TABLE endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);

ALTER TABLE movimentacao
    ADD CONSTRAINT movimentacao_pkey PRIMARY KEY (id);

ALTER TABLE pontuacao
    ADD CONSTRAINT pontuacao_pkey PRIMARY KEY (id);

ALTER TABLE tipo_embalagem
    ADD CONSTRAINT tipo_embalagem_pkey PRIMARY KEY (id);

ALTER TABLE usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);

ALTER TABLE endereco
    ADD CONSTRAINT fk233cf5mftynvlqs8ydwjlgf0i FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE embalagem
    ADD CONSTRAINT fk8acqv2gedbo7gs7a9ft3b1h7m FOREIGN KEY (tipo_id) REFERENCES tipo_embalagem(id);

ALTER TABLE movimentacao
    ADD CONSTRAINT fkb6n22m4bx25sl4sla23lap72s FOREIGN KEY (id_usuario_origem) REFERENCES usuario(id);

ALTER TABLE pontuacao
    ADD CONSTRAINT fkcbnlcuyiy6v9nfvtsr2ivakim FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE movimentacao
    ADD CONSTRAINT fkjwaorj6kgwiqrwp1tpkbabc83 FOREIGN KEY (id_embalagem) REFERENCES embalagem(id);

ALTER TABLE movimentacao
    ADD CONSTRAINT fkslvco5tngq8w6xxms7y6gcnav FOREIGN KEY (id_usuario_destino) REFERENCES usuario(id);
