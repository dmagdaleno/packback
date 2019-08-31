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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MovimentacaoService {

    private static final Logger log = LoggerFactory.getLogger(MovimentacaoService.class);

    private final MovimentacaoRepository repositorioDeMovimentacao;
    private final EmbalagemRepository repositorioDeEmbalagem;
    private final PontuacaoRepository repositorioDePontuacao;
    private final UsuarioService usuariosService;

    @Autowired
    public MovimentacaoService(
            MovimentacaoRepository repositorioDeMovimentacao,
            EmbalagemRepository repositorioDeEmbalagem,
            PontuacaoRepository repositorioDePontuacao,
            UsuarioService usuariosService) {

        this.repositorioDeMovimentacao = repositorioDeMovimentacao;
        this.repositorioDeEmbalagem = repositorioDeEmbalagem;
        this.repositorioDePontuacao = repositorioDePontuacao;
        this.usuariosService = usuariosService;
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

    public List<Movimentacao> buscaPorUsuario(Long idUsuario) {
        return repositorioDeMovimentacao.findByUsuarioOrigemId(idUsuario);
    }

    @Transactional
    public Movimentacao movimenta(Long idEmbalagem, Long idUsuarioOrigem, Long idUsuarioDestino) {
        var embalagem = repositorioDeEmbalagem.findById(idEmbalagem).get();
        var de = usuariosService.buscaPorId(idUsuarioOrigem).get();
        var para = usuariosService.buscaPorId(idUsuarioDestino).get();
        return movimenta(Collections.singletonList(embalagem), de, para);
    }

    public Movimentacao movimenta(List<Embalagem> embalagens, Usuario origem, Usuario destino) {
        log.info("--> movimentando embalagem {} de {} para {}...", embalagens, origem, destino);

        var movimentacao = new MovimentacaoBuilder().movimenta(embalagens).de(origem).para(destino).constroi();

        var movimentacaoSalva = salva(movimentacao);

        log.info("<-- registro da movimentação da embalagem {} de {} para {} concluído", embalagens, origem, destino);

        return movimentacaoSalva;
    }

    public Movimentacao salva(Movimentacao movimentacao) {

        if(movimentacao.deveSerPontuada()) {
            Pontuacao pontuacao = movimentacao.calculaPontuacao();
            repositorioDePontuacao.save(pontuacao);
            usuariosService.salva(movimentacao.getUsuarioOrigem());
        }

        return repositorioDeMovimentacao.save(movimentacao);
    }
}
