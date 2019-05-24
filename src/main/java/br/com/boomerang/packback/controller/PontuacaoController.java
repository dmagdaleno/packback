package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Pontuacao;
import br.com.boomerang.packback.repository.PontuacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pontuacoes")
public class PontuacaoController {

    private static final Logger log = LoggerFactory.getLogger(PontuacaoController.class);

    private PontuacaoRepository repository;

    @Autowired
    public PontuacaoController(PontuacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Pontuacao>> buscaPontuacaoPorIdDoUsuario(@PathVariable Long idUsuario) {
        log.info("--> Busca pontuações do usuário {}", idUsuario);
        return ResponseEntity.ok(repository.findAllByUsuarioId(idUsuario));
    }
}
