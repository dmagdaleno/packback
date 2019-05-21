package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.domain.builder.MovimentacaoBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovimentacaoRepositoryIntegrationTest {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private InicializadorDeBanco init;

    private Usuario consumidor;

    private Usuario produtor;

    private Embalagem embalagem;

    @Before
    public void setup() {
        var objects = init.inicializaBase();
        consumidor = (Usuario) objects.get(0);
        produtor = (Usuario) objects.get(1);
        embalagem = (Embalagem) objects.get(2);
    }

    @Test
    @Transactional
    public void deveCadastrarMovimentacaoDoConsumidorParaOProdutor() {
        var movimentacao = new MovimentacaoBuilder().movimenta(embalagem).de(consumidor).para(produtor).constroi();
        repository.save(movimentacao);

        var movimentacoes = repository.findAll();
        assertThat(movimentacoes.size()).isEqualTo(1);
        assertThat(movimentacoes.get(0).getUsuarioOrigem()).isEqualTo(consumidor);
        assertThat(movimentacoes.get(0).getUsuarioDestino()).isEqualTo(produtor);
    }

    @Test
    @Transactional
    public void deveCadastrarMovimentacaoDoProdutorParaOConsumidor() {
        var movimentacao = new MovimentacaoBuilder().movimenta(embalagem).de(produtor).para(consumidor).constroi();
        repository.save(movimentacao);

        var movimentacoes = repository.findAll();
        assertThat(movimentacoes.size()).isEqualTo(1);
        assertThat(movimentacoes.get(0).getUsuarioOrigem()).isEqualTo(produtor);
        assertThat(movimentacoes.get(0).getUsuarioDestino()).isEqualTo(consumidor);
    }

    @Test
    @Transactional
    public void deveCadastrarListaDeMovimentacao() {
        var movimentacao1 = new MovimentacaoBuilder().movimenta(embalagem).de(consumidor).para(produtor).constroi();
        var movimentacao2 = new MovimentacaoBuilder().movimenta(embalagem).de(produtor).para(consumidor).constroi();

        repository.saveAll(List.of(movimentacao1, movimentacao2));

        var movimentacoes = repository.findAll();
        assertThat(movimentacoes.size()).isEqualTo(2);
        assertThat(movimentacoes.get(0).getUsuarioOrigem()).isEqualTo(consumidor);
        assertThat(movimentacoes.get(0).getUsuarioDestino()).isEqualTo(produtor);
        assertThat(movimentacoes.get(1).getUsuarioOrigem()).isEqualTo(produtor);
        assertThat(movimentacoes.get(1).getUsuarioDestino()).isEqualTo(consumidor);
    }
}
