
--
-- Carrega tabelas com dados iniciais
--

-- Tipos de embalagem

INSERT INTO public.tipo_embalagem(id, descricao, material) VALUES (nextval('public.tp_embalagem_id_seq'), 'Lata', 0);

INSERT INTO public.tipo_embalagem(id, descricao, material) VALUES (nextval('public.tp_embalagem_id_seq'), 'Folhas', 1);

INSERT INTO public.tipo_embalagem(id, descricao, material) VALUES (nextval('public.tp_embalagem_id_seq'), 'Pote', 2);

INSERT INTO public.tipo_embalagem(id, descricao, material) VALUES (nextval('public.tp_embalagem_id_seq'), 'Garrafa', 3);

-- Embalagens

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Lata refrigerante', 0.01, 350, 1);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Caixa papelão', 0.05, 3000, 2);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Lata óleo', 0.025, 500, 1);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Bloco de folhas', 0.09, 1000, 2);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Pote iogurte', 0.008, 120, 3);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Pote requeijão', 0.01, 200, 3);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Garrafa refrigenante', 0.1, 1000, 4);

INSERT INTO public.embalagem(id, descricao, peso, volume, tipo_id)
VALUES (nextval('public.embalagem_id_seq'), 'Garrafa suco', 0.15, 1500, 4);

-- Regiões

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Pão de Açúcar', 'Congelados');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Pão de Açúcar', 'Laticínios');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Pão de Açúcar', 'Enlatados');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Pão de Açúcar', 'Bebidas');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Carrefour', 'Congelados');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Carrefour', 'Laticínios');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Carrefour', 'Enlatados');

INSERT INTO public.regiao(id, loja, setor) VALUES (nextval('public.regiao_id_seq'), 'Carrefour', 'Bebidas');

-- Produtos

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Coca-cola Lata', 1, 4);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Fanta laranja Lata', 1, 4);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Suco Aurora Garrafa', 8, 4);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Pão de queijo Caixa', 2, 1);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Leite Caixa', 2, 2);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Milho Lata', 1, 3);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Molho de tomate Lata', 1, 3);


INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Coca-cola Lata', 1, 8);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Fanta laranja Lata', 1, 8);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Suco Aurora Garrafa', 8, 8);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Pão de queijo Caixa', 2, 5);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Leite Caixa', 2, 6);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Milho Lata', 1, 7);

INSERT INTO public.produto(id, descricao, embalagem_id, regiao_id)
VALUES (nextval('public.produto_id_seq'), 'Molho de tomate Lata', 1, 7);

-- Usuarios
-- Consumidor

INSERT INTO public.login(id, senha) VALUES (nextval('public.login_id_seq'), '123456');

INSERT INTO public.usuario(id, cnpj, cpf, email, nome, razao_social, tipo, login_id)
VALUES(nextval('public.usuario_id_seq'), null, '00100200304', 'usuario@teste.com', 'Usuario de Teste', null, 0, 1);

INSERT INTO public.endereco(id, bairro, cep, cidade, estado, numero, pais, rua, id_usuario)
VALUES(nextval('public.endereco_id_seq'), 'Nome do Bairro', '00102-003', 'Nome da Cidade', 'Nome do Estado', 570, 'BR', 'Nome da Rua', 1);

-- Produtor

INSERT INTO public.login(id, senha) VALUES (nextval('public.login_id_seq'), '123456');

INSERT INTO public.usuario(id, cnpj, cpf, email, nome, razao_social, tipo, login_id)
VALUES(nextval('public.usuario_id_seq'), '001002003000145', '00100200345', 'produtor@empresa.com.br', 'Dono da Empresa', 'Nome da Empresa', 1, 2);

INSERT INTO public.endereco(id, bairro, cep, cidade, estado, numero, pais, rua, id_usuario)
VALUES(nextval('public.endereco_id_seq'), 'Nome do Bairro', '00102-003', 'Nome da Cidade', 'Nome do Estado', 720, 'BR', 'Nome da Rua', 2);
