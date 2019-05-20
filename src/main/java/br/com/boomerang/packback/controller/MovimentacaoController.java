package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Movimentacao;
import br.com.boomerang.packback.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoService servico;

    @Autowired
    public MovimentacaoController(MovimentacaoService servico) {
        this.servico = servico;
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> buscaTodas() {
        return ResponseEntity.ok(servico.buscaTodas());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Movimentacao> buscaPorId(@PathVariable Long id) {

        Movimentacao movimentacao = servico.buscaPorId(id);

        if(movimentacao != null) {
            return ResponseEntity.ok(movimentacao);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movimenta/{idEmbalagem}/de/{idUsuarioOrigem}/para/{idUsuarioDestino}")
    @Transactional
    public ResponseEntity<Movimentacao> movimenta(
            @PathVariable Long idEmbalagem,
            @PathVariable Long idUsuarioOrigem,
            @PathVariable Long idUsuarioDestino) {

        var movimentacao = servico.movimenta(idEmbalagem, idUsuarioOrigem, idUsuarioDestino);

        var uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/movimentacoes/{id}")
                .buildAndExpand(movimentacao.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
