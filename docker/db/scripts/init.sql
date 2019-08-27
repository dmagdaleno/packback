SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: embalagem; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.embalagem (
    id bigint NOT NULL,
    descricao character varying(255),
    peso double precision,
    volume double precision,
    tipo_id bigint
);


ALTER TABLE public.embalagem OWNER TO postgres;

--
-- Name: embalagem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.embalagem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.embalagem_id_seq OWNER TO postgres;

--
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    bairro character varying(255),
    cep character varying(255),
    cidade character varying(255),
    estado character varying(255),
    numero integer,
    pais character varying(255),
    rua character varying(255),
    id_usuario bigint
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_seq OWNER TO postgres;

--
-- Name: login; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.login (
    id bigint NOT NULL,
    senha character varying(255)
);


ALTER TABLE public.login OWNER TO postgres;

--
-- Name: login_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.login_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_id_seq OWNER TO postgres;

--
-- Name: movimentacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.movimentacao (
    id bigint NOT NULL,
    data timestamp without time zone,
    id_embalagem bigint,
    id_usuario_destino bigint,
    id_usuario_origem bigint
);


ALTER TABLE public.movimentacao OWNER TO postgres;

--
-- Name: pontuacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.pontuacao (
    id bigint NOT NULL,
    pontos double precision,
    id_usuario bigint
);


ALTER TABLE public.pontuacao OWNER TO postgres;

--
-- Name: pontuacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pontuacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pontuacao_id_seq OWNER TO postgres;

--
-- Name: pontuacao_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pontuacao_usuario_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pontuacao_usuario_seq OWNER TO postgres;

--
-- Name: tipo_embalagem; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.tipo_embalagem (
    id bigint NOT NULL,
    descricao character varying(255),
    material integer
);


ALTER TABLE public.tipo_embalagem OWNER TO postgres;

--
-- Name: tp_embalagem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tp_embalagem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tp_embalagem_id_seq OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    cnpj character varying(255),
    cpf character varying(255),
    email character varying(255),
    nome character varying(255),
    razao_social character varying(255),
    tipo integer,
    login_id bigint
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- Name: embalagem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.embalagem
    ADD CONSTRAINT embalagem_pkey PRIMARY KEY (id);


--
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: login_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.login
    ADD CONSTRAINT login_pkey PRIMARY KEY (id);


--
-- Name: movimentacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.movimentacao
    ADD CONSTRAINT movimentacao_pkey PRIMARY KEY (id);


--
-- Name: pontuacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.pontuacao
    ADD CONSTRAINT pontuacao_pkey PRIMARY KEY (id);


--
-- Name: tipo_embalagem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.tipo_embalagem
    ADD CONSTRAINT tipo_embalagem_pkey PRIMARY KEY (id);


--
-- Name: uk_5171l57faosmj8myawaucatdw; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_5171l57faosmj8myawaucatdw UNIQUE (email);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: fk233cf5mftynvlqs8ydwjlgf0i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT fk233cf5mftynvlqs8ydwjlgf0i FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- Name: fk56vq2a7ekea3mql3q34bm2ixf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk56vq2a7ekea3mql3q34bm2ixf FOREIGN KEY (login_id) REFERENCES public.login(id);


--
-- Name: fk8acqv2gedbo7gs7a9ft3b1h7m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.embalagem
    ADD CONSTRAINT fk8acqv2gedbo7gs7a9ft3b1h7m FOREIGN KEY (tipo_id) REFERENCES public.tipo_embalagem(id);


--
-- Name: fkb6n22m4bx25sl4sla23lap72s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimentacao
    ADD CONSTRAINT fkb6n22m4bx25sl4sla23lap72s FOREIGN KEY (id_usuario_origem) REFERENCES public.usuario(id);


--
-- Name: fkcbnlcuyiy6v9nfvtsr2ivakim; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pontuacao
    ADD CONSTRAINT fkcbnlcuyiy6v9nfvtsr2ivakim FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- Name: fkjwaorj6kgwiqrwp1tpkbabc83; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimentacao
    ADD CONSTRAINT fkjwaorj6kgwiqrwp1tpkbabc83 FOREIGN KEY (id_embalagem) REFERENCES public.embalagem(id);


--
-- Name: fkslvco5tngq8w6xxms7y6gcnav; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimentacao
    ADD CONSTRAINT fkslvco5tngq8w6xxms7y6gcnav FOREIGN KEY (id_usuario_destino) REFERENCES public.usuario(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
