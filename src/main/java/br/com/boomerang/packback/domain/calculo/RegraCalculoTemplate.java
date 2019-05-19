package br.com.boomerang.packback.domain.calculo;

import br.com.boomerang.packback.domain.Embalagem;

public abstract class RegraCalculoTemplate implements RegraCalculo {

    @Override
    public Double calculaPontos(Embalagem embalagem) {
        return embalagem.getVolume() * embalagem.getPeso() * multiploDoMaterial();
    }

    protected abstract Double multiploDoMaterial();
}
