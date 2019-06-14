options (errors=9999999, rows=5)
load data
 characterset WE8ISO8859P1
 infile 'dados.txt'
 badfile 'erros.bad'
 discardfile 'descartados.dsc'
 append
 into table tipo_embalagem
 fields terminated by ","
( id tp_embalagem_id_seq(max), material, descricao)