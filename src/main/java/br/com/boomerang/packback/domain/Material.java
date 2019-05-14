package br.com.boomerang.packback.domain;

public enum Material {

    METAL(new CalculadoraMetal()),
    PAPEL(new CalculadoraPapel()),
    PLASTICO(new CalculadoraPlastico()),
    VIDRO(new CalculadoraVidro());

    Material(RegraCalculo regra) {
        this.regra = regra;
    }

    private RegraCalculo regra;
}
