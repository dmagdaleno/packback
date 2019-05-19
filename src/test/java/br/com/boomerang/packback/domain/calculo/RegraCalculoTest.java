package br.com.boomerang.packback.domain.calculo;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Material;
import br.com.boomerang.packback.domain.TipoEmbalagem;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegraCalculoTest {

    private Double volume = 350.0;
    private Double peso = 10.0;

    @Test
    public void deveCalcularPontosParaEmbalagemDeMetal() {
        var tipo = new TipoEmbalagem(null, "Lata", Material.METAL);
        var embalagem = new Embalagem(null, tipo, "Lata Coca-Cola", volume, peso);

        assertThat(embalagem.calculaPontos()).isEqualTo(volume*peso*0.9);
    }

    @Test
    public void deveCalcularPontosParaEmbalagemDeVidro() {
        var tipo = new TipoEmbalagem(null, "Garrafa", Material.VIDRO);
        var embalagem = new Embalagem(null, tipo, "Garrafa Coca-Cola", volume, peso);

        assertThat(embalagem.calculaPontos()).isEqualTo(volume*peso*0.7);
    }

    @Test
    public void deveCalcularPontosParaEmbalagemDePlastico() {
        var tipo = new TipoEmbalagem(null, "Pote", Material.PLASTICO);
        var embalagem = new Embalagem(null, tipo, "Pote doce de leite", volume, peso);

        assertThat(embalagem.calculaPontos()).isEqualTo(volume*peso*0.6);
    }

    @Test
    public void deveCalcularPontosParaEmbalagemDePapel() {
        var tipo = new TipoEmbalagem(null, "Bloco de folhas", Material.PAPEL);
        var embalagem = new Embalagem(null, tipo, "Bloco de folhas de papel", volume, peso);

        assertThat(embalagem.calculaPontos()).isEqualTo(volume*peso*0.5);
    }
}
