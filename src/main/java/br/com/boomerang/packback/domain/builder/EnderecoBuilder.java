package br.com.boomerang.packback.domain.builder;

import br.com.boomerang.packback.domain.Endereco;

public class EnderecoBuilder {

    private String cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public EnderecoBuilder naRua(String rua) {
        this.rua = rua;
        return this;
    }

    public EnderecoBuilder numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public Endereco constroi() {
        var endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setNumero(numero);

        return endereco;
    }
}
