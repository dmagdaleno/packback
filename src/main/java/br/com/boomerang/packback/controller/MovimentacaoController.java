package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Movimentacao;
import br.com.boomerang.packback.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Movimentacao>> buscaPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(servico.buscaPorUsuario(id));
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<Movimentacao> movimenta(
            @PathVariable Long idEmbalagem,
            @PathVariable Long idUsuarioOrigem,
            @PathVariable Long idUsuarioDestino) {

        var movimentacao = servico.movimenta(idEmbalagem, idUsuarioOrigem, idUsuarioDestino);

        URI uri = getUri(movimentacao);

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/movimenta")
    public ResponseEntity<Movimentacao> movimenta(@RequestBody Movimentacao movimentacao) {

        var movimentacaoSalva = servico.salva(movimentacao);

        URI uri = getUri(movimentacaoSalva);

        return ResponseEntity.created(uri).build();
    }

    private URI getUri(Movimentacao movimentacaoSalva) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/movimentacoes/{id}")
                .buildAndExpand(movimentacaoSalva.getId())
                .toUri();
    }
}
