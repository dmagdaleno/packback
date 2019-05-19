package br.com.boomerang.packback.domain.calculo.impl;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.calculo.RegraCalculo;

public class CalculadoraMetal implements RegraCalculo {
    @Override
    public Double calculaPontos(Embalagem embalagem) {
        return embalagem.getVolume() * embalagem.getPeso() * 10;
    }
}
