package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Material;
import br.com.boomerang.packback.domain.TipoEmbalagem;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.domain.builder.UsuarioBuilderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BancoDeDadosTest {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmbalagemRepository embalagemRepository;

    @Autowired
    private TipoEmbalagemRepository tipoEmbalagemRepository;

    @Autowired
    private PontuacaoRepository pontuacaoRepository;

    private Usuario consumidor;

    private Usuario produtor;

    private Embalagem embalagem;

    public List<Object> inicializa() {
        apagaTudo();

        var consumidor = UsuarioBuilderUtils.criaUsuarioConsumidor();
        var produtor = UsuarioBuilderUtils.criaUsuarioProdutor();
        this.consumidor = usuarioRepository.save(consumidor);
        this.produtor = usuarioRepository.save(produtor);

        var tipo = new TipoEmbalagem(null, "Lata", Material.METAL);
        var tipoSalvo = tipoEmbalagemRepository.save(tipo);
        var embalagem = new Embalagem(null, tipoSalvo, "Lata Coca-Cola", 350.0, 10.0, Collections.emptySet());
        this.embalagem = embalagemRepository.save(embalagem);

        return List.of(consumidor, produtor, embalagem);
    }

    public void apagaTudo() {
        movimentacaoRepository.deleteAll();
        usuarioRepository.deleteAll();
        embalagemRepository.deleteAll();
        tipoEmbalagemRepository.deleteAll();
        pontuacaoRepository.deleteAll();
    }
}
