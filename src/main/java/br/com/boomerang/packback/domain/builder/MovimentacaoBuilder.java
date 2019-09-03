package br.com.boomerang.packback.domain.builder;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Movimentacao;
import br.com.boomerang.packback.domain.Usuario;

import java.time.LocalDateTime;
import java.util.Set;

public class MovimentacaoBuilder {

    private Usuario origem;

    private Usuario destino;

    private Set<Embalagem> embalagens;

    private LocalDateTime data;

    public MovimentacaoBuilder() {
        this.data = LocalDateTime.now();
    }

    public MovimentacaoBuilder movimenta(Set<Embalagem> embalagens) {
        this.embalagens = embalagens;
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
        return new Movimentacao(null, origem, destino, embalagens, data);
    }
}
