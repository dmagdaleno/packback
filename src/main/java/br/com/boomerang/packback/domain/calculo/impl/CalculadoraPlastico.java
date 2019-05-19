package br.com.boomerang.packback.domain.calculo.impl;

import br.com.boomerang.packback.domain.calculo.RegraCalculoTemplate;

public class CalculadoraPlastico extends RegraCalculoTemplate {
    @Override
    protected Double multiploDoMaterial() {
        return 0.6;
    }
}
