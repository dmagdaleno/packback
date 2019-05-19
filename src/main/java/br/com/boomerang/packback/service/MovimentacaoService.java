package br.com.boomerang.packback.service;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.domain.Movimentacao;
import br.com.boomerang.packback.domain.Pontuacao;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.domain.builder.MovimentacaoBuilder;
import br.com.boomerang.packback.repository.EmbalagemRepository;
import br.com.boomerang.packback.repository.MovimentacaoRepository;
import br.com.boomerang.packback.repository.PontuacaoRepository;
import br.com.boomerang.packback.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovimentacaoService {

    private static final Logger log = LoggerFactory.getLogger(MovimentacaoService.class);

    private final MovimentacaoRepository repositorioDeMovimentacao;
    private final EmbalagemRepository repositorioDeEmbalagem;
    private final PontuacaoRepository repositorioDePontuacao;
    private final UsuarioRepository repositorioDeUsuario;

    @Autowired
    public MovimentacaoService(
            MovimentacaoRepository repositorioDeMovimentacao,
            EmbalagemRepository repositorioDeEmbalagem,
            PontuacaoRepository repositorioDePontuacao,
            UsuarioRepository repositorioDeUsuario) {

        this.repositorioDeMovimentacao = repositorioDeMovimentacao;
        this.repositorioDeEmbalagem = repositorioDeEmbalagem;
        this.repositorioDePontuacao = repositorioDePontuacao;
        this.repositorioDeUsuario = repositorioDeUsuario;
    }

    public List<Movimentacao> buscaTodas(){
        return repositorioDeMovimentacao.findAll();
    }

    public Movimentacao buscaPorId(Long id){
        log.info("--> busca movimentacao com o id {}...", id);

        var movimentacao = repositorioDeMovimentacao.findById(id);

        if(movimentacao.isPresent()) {
            log.info("<-- movimentacao com o id {} encontrada", id);
            return movimentacao.get();
        }
        else {
            log.info("<-- movimentacao com o id {} não encontrada", id);
            return null;
        }
    }

    public Movimentacao movimenta(Long idEmbalagem, Long idUsuarioOrigem, Long idUsuarioDestino) {
        var embalagem = repositorioDeEmbalagem.findById(idEmbalagem).get();
        var de = repositorioDeUsuario.findById(idUsuarioOrigem).get();
        var para = repositorioDeUsuario.findById(idUsuarioDestino).get();
        return movimenta(embalagem, de, para);
    }

    public Movimentacao movimenta(Embalagem embalagem, Usuario origem, Usuario destino) {
        log.info("--> movimentando embalagem {} de {} para {}...", embalagem, origem, destino);

        var movimentacao = new MovimentacaoBuilder().movimenta(embalagem).de(origem).para(destino).constroi();

        if(movimentacao.deveSerPontuada()) {
            var pontuacao = new Pontuacao(null, origem, embalagem.calculaPontos());
            origem.adicionaPontuacao(pontuacao);
            repositorioDePontuacao.save(pontuacao);
            repositorioDeUsuario.save(origem);
        }

        var movimentacaoSalva = repositorioDeMovimentacao.save(movimentacao);

        log.info("<-- registro da movimentação da embalagem {} de {} para {} concluído", embalagem, origem, destino);

        return movimentacaoSalva;
    }
}
