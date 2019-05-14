package br.com.boomerang.packback.service;

import br.com.boomerang.packback.domain.*;
import br.com.boomerang.packback.repository.EmbalagemRepository;
import br.com.boomerang.packback.repository.MovimentacaoRepository;
import br.com.boomerang.packback.repository.PontuacaoRepository;
import br.com.boomerang.packback.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public void movimenta(Long idEmbalagem, Long idUsuarioDe, Long idUsuarioPara) {
        var embalagem = repositorioDeEmbalagem.findById(idEmbalagem).get();
        var de = repositorioDeUsuario.findById(idUsuarioDe).get();
        var para = repositorioDeUsuario.findById(idUsuarioPara).get();
        movimenta(embalagem, de, para);
    }

    public void movimenta(Embalagem embalagem, Usuario de, Usuario para) {
        log.info("--> movimentando embalagem {} de {} para {}...", embalagem, de, para);

        var movimentaDe = new Movimentacao(null, TipoMovimentacao.DE, de, embalagem, LocalDateTime.now());
        var movimentaPara = new Movimentacao(null, TipoMovimentacao.PARA, para, embalagem, LocalDateTime.now());

        if(deveCalcularPontuacao(de, para)) {
            var pontuacao = new Pontuacao(null, de, embalagem.calculaPontos());
            de.adicionaPontuacao(pontuacao);
            repositorioDePontuacao.save(pontuacao);
            repositorioDeUsuario.save(de);
        }

        repositorioDeMovimentacao.save(movimentaDe);
        repositorioDeMovimentacao.save(movimentaPara);

        log.info("<-- registro da movimentação da embalagem {} de {} para {} concluído", embalagem, de, para);
    }

    private boolean deveCalcularPontuacao(Usuario de, Usuario para) {
        return de.getTipo().equals(TipoUsuario.CONSUMIDOR) && para.getTipo().equals(TipoUsuario.PRODUTOR);
    }
}
