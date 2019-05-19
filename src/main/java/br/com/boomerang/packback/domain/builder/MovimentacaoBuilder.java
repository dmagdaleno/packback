package br.com.boomerang.packback.domain.builder;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Movimentacao;
import br.com.boomerang.packback.domain.Usuario;

import java.time.LocalDateTime;

public class MovimentacaoBuilder {

    private Usuario origem;

    private Usuario destino;

    private Embalagem embalagem;

    private LocalDateTime data;

    public MovimentacaoBuilder() {
        this.data = LocalDateTime.now();
    }

    public MovimentacaoBuilder movimenta(Embalagem embalagem) {
        this.embalagem = embalagem;
        return this;
    }

    public MovimentacaoBuilder de(Usuario usuario) {
        this.origem = usuario;
        return this;
    }

    public MovimentacaoBuilder para(Usuario usuario) {
        this.destino = usuario;
        return this;
    }

    public Movimentacao constroi() {
        return new Movimentacao(null, origem, destino, embalagem, data);
    }
}
