options (errors=9999999, rows=5)
  load data
    characterset UTF8
    infile 'dados.txt'
    badfile 'erros.bad'
    discardfile 'descartados.dsc'

  into table tipo_embalagem
    truncate
    when (1:1) = 'A'
    fields terminated by ","
      (dummy0 FILLER, id "tp_embalagem_id_seq.nextval", material, descricao)

  into table embalagem
    truncate
    when (1:1) = 'B'
    fields terminated by ","
      (dummy0 FILLER POSITION(1), id "embalagem_id_seq.nextval", descricao, volume, peso, tipo_id)

  into table endereco
    truncate
    when (1:1) = 'C'
    fields terminated by ","
      (dummy0 FILLER POSITION(1), id "endereco_id_seq.nextval", cep, rua, numero, bairro, cidade, estado, pais)