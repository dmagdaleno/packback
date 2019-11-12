package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Identificador;
import br.com.boomerang.packback.domain.Produto;
import br.com.boomerang.packback.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);

    private ProdutoRepository repository;

    @Autowired
    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/regiao/{idRegiao}")
    public ResponseEntity<Collection<Produto>> buscaPorRegiao(@PathVariable Long idRegiao) {
        log.info("--> Busca produtos da região {}", idRegiao);
        return ResponseEntity.ok(repository.findAllByRegiaoId(idRegiao));
    }

    @PostMapping("/identifica")
    public ResponseEntity<Collection<Produto>> buscaPorTags(@RequestBody Identificador identificador) {
        log.info("--> Busca produtos com as tags {}", Arrays.toString(identificador.getTags().toArray()));
        return ResponseEntity.ok(repository.findAllByTags(identificador.getTags()));
    }
}
