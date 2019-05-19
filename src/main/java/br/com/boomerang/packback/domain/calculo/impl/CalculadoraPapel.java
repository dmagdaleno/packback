package br.com.boomerang.packback.domain.calculo.impl;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.calculo.RegraCalculo;
import br.com.boomerang.packback.domain.calculo.RegraCalculoTemplate;

public class CalculadoraPapel extends RegraCalculoTemplate {
    @Override
    protected Double multiploDoMaterial() {
        return 0.5;
    }
}
