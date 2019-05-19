package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Material;
import br.com.boomerang.packback.domain.TipoEmbalagem;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.domain.builder.MovimentacaoBuilder;
import br.com.boomerang.packback.domain.builder.UsuarioBuilderUtils;
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
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmbalagemRepository embalagemRepository;

    @Autowired
    private TipoEmbalagemRepository tipoEmbalagemRepository;

    private Usuario consumidor;

    private Usuario produtor;

    private Embalagem embalagem;

    @Before
    public void setup() {
        inicializaBase();
    }

    @Test
    @Transactional
    public void deveCadastrarMovimentacaoDoConsumidorParaOProdutor() {
        var movimentacao = new MovimentacaoBuilder().movimenta(embalagem).de(consumidor).para(produtor).constroi();
        movimentacaoRepository.save(movimentacao);

        var movimentacoes = movimentacaoRepository.findAll();
        assertThat(movimentacoes.size()).isEqualTo(1);
        assertThat(movimentacoes.get(0).getUsuarioOrigem()).isEqualTo(consumidor);
        assertThat(movimentacoes.get(0).getUsuarioDestino()).isEqualTo(produtor);
    }

    @Test
    @Transactional
    public void deveCadastrarMovimentacaoDoProdutorParaOConsumidor() {
        var movimentacao = new MovimentacaoBuilder().movimenta(embalagem).de(produtor).para(consumidor).constroi();
        movimentacaoRepository.save(movimentacao);

        var movimentacoes = movimentacaoRepository.findAll();
        assertThat(movimentacoes.size()).isEqualTo(1);
        assertThat(movimentacoes.get(0).getUsuarioOrigem()).isEqualTo(produtor);
        assertThat(movimentacoes.get(0).getUsuarioDestino()).isEqualTo(consumidor);
    }



    @Test
    @Transactional
    public void deveCadastrarListaDeMovimentacao() {
        var movimentacao1 = new MovimentacaoBuilder().movimenta(embalagem).de(consumidor).para(produtor).constroi();
        var movimentacao2 = new MovimentacaoBuilder().movimenta(embalagem).de(produtor).para(consumidor).constroi();

        movimentacaoRepository.saveAll(List.of(movimentacao1, movimentacao2));

        var movimentacoes = movimentacaoRepository.findAll();
        assertThat(movimentacoes.size()).isEqualTo(2);
        assertThat(movimentacoes.get(0).getUsuarioOrigem()).isEqualTo(consumidor);
        assertThat(movimentacoes.get(0).getUsuarioDestino()).isEqualTo(produtor);
        assertThat(movimentacoes.get(1).getUsuarioOrigem()).isEqualTo(produtor);
        assertThat(movimentacoes.get(1).getUsuarioDestino()).isEqualTo(consumidor);
    }

    public void inicializaBase() {
        movimentacaoRepository.deleteAll();

        usuarioRepository.deleteAll();
        var consumidor = UsuarioBuilderUtils.criaUsuarioConsumidor();
        var produtor = UsuarioBuilderUtils.criaUsuarioProdutor();
        this.consumidor = usuarioRepository.save(consumidor);
        this.produtor = usuarioRepository.save(produtor);

        embalagemRepository.deleteAll();
        tipoEmbalagemRepository.deleteAll();
        var tipo = new TipoEmbalagem(null, "Lata", Material.METAL);
        var tipoSalvo = tipoEmbalagemRepository.save(tipo);
        var embalagem = new Embalagem(null, tipoSalvo, "Lata Coca-Cola", 350.0, 10.0);
        this.embalagem = embalagemRepository.save(embalagem);
    }
}
