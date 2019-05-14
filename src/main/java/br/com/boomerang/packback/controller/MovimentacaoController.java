package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Movimentacao;
import br.com.boomerang.packback.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoService servico;

    @Autowired
    public MovimentacaoController(MovimentacaoService servico) {
        this.servico = servico;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Iterable<Movimentacao>> buscaTodas() {
        return ResponseEntity.ok(servico.buscaTodas());
    }

    @PostMapping("/movimenta/{idEmbalagem}/de/{idUsuarioDe}/para/{idUsuarioPara}")
    public ResponseEntity<?> movimenta(
            @PathVariable Long idEmbalagem,
            @PathVariable Long idUsuarioDe,
            @PathVariable Long idUsuarioPara) {

        servico.movimenta(idEmbalagem, idUsuarioDe, idUsuarioPara);

        return ResponseEntity.ok().build();
    }
}
