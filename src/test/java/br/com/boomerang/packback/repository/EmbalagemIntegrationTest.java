package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.TipoEmbalagem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmbalagemIntegrationTest {

    @Autowired
    private EmbalagemRepository repositorio;

    @Autowired
    private TipoEmbalagemRepository tipoEmbalagemRepositorio;

    @Before
    public void setup() {
        repositorio.deleteAll();
        tipoEmbalagemRepositorio.deleteAll();
    }

    @Test
    public void deveCadastrarUmaEmbalagem() {
        var tipo = new TipoEmbalagem(null, "Lata", "Metal");
        var tipoSalvo = tipoEmbalagemRepositorio.save(tipo);

        var embalagem = new Embalagem(null, tipoSalvo, "Lata Coca-Cola", 350.0, 10.0);

        repositorio.save(embalagem);

        List<Embalagem> lista = new ArrayList<>(repositorio.findAll());

        assertThat(lista.size()).isEqualTo(1);
    }
}
