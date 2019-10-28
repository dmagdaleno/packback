package br.com.boomerang.packback.service;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.BancoDeDadosTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovimentacaoServiceIntegrationTest {

    @Autowired
    private MovimentacaoService service;

    @Autowired
    private BancoDeDadosTest init;

    private Usuario consumidor;

    private Usuario produtor;

    private Embalagem embalagem;

    @Before
    public void setup() {
        var objects = init.inicializa();
        consumidor = (Usuario) objects.get(0);
        produtor = (Usuario) objects.get(1);
        embalagem = (Embalagem) objects.get(2);
    }

    @Test
    @Transactional
    public void deveCadastrarMovimentacaoEAtribuirPontosAoUsuarioConsumidor() {
        assertThat(consumidor.getPontos()).isEqualTo(.0);

        var movimentacaoSalva = service.movimenta(Collections.singleton(embalagem), consumidor, produtor);

        var movimentacao = service.buscaPorId(movimentacaoSalva.getId());

        assertThat(movimentacao.getUsuarioOrigem().getPontos()).isEqualTo(3150.0);
    }

}
