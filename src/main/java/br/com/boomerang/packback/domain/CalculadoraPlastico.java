package br.com.boomerang.packback.domain;

public class CalculadoraPlastico implements RegraCalculo {
    @Override
    public Double calculaPontos(Embalagem embalagem) {
        return embalagem.getVolume() * embalagem.getPeso() * 10;
    }
}
