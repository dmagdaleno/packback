package br.com.boomerang.packback.domain;

import br.com.boomerang.packback.domain.calculo.RegraCalculo;
import br.com.boomerang.packback.domain.calculo.impl.CalculadoraMetal;
import br.com.boomerang.packback.domain.calculo.impl.CalculadoraPapel;
import br.com.boomerang.packback.domain.calculo.impl.CalculadoraPlastico;
import br.com.boomerang.packback.domain.calculo.impl.CalculadoraVidro;

public enum Material {

    METAL(new CalculadoraMetal()),
    PAPEL(new CalculadoraPapel()),
    PLASTICO(new CalculadoraPlastico()),
    VIDRO(new CalculadoraVidro());

    Material(RegraCalculo regra) {
        this.regra = regra;
    }

    private RegraCalculo regra;

    public RegraCalculo getRegra() {
        return regra;
    }
}
