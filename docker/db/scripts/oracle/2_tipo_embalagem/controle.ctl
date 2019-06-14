options (errors=9999999, rows=2)
load data
 characterset UTF8
 infile 'dados.txt'
 badfile 'erros.bad'
 discardfile 'descartados.dsc'
 apptruncateend
 into table tipo_embalagem
 fields terminated by ","
(id "tp_embalagem_id_seq.nextval", material, descricao)