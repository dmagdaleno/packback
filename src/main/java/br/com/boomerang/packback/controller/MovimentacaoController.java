package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoService servico;

    @Autowired
    public MovimentacaoController(MovimentacaoService servico) {
        this.servico = servico;
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
