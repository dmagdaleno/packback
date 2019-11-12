CREATE TABLE public.tag (
    id bigint NOT NULL,
    produto_id bigint,
    descricao character varying(55)
);


ALTER TABLE public.tag OWNER TO postgres;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tag_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.tag_id_seq OWNER TO postgres;


--
-- Name: tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.tag
ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: fk_produto_tag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
ADD CONSTRAINT fk_produto_tag FOREIGN KEY (produto_id) REFERENCES public.produto(id);