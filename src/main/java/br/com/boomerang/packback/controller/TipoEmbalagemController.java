package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.TipoEmbalagem;
import br.com.boomerang.packback.repository.TipoEmbalagemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/embalagens/tipos")
public class TipoEmbalagemController {

    private static final Logger log = LoggerFactory.getLogger(TipoEmbalagemController.class);

    private TipoEmbalagemRepository repositorio;

    @Autowired
    public TipoEmbalagemController(TipoEmbalagemRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Iterable<TipoEmbalagem>> buscaTodos() {
        log.info("--> lista todos os tipos de embalagem...");
        var tipos = repositorio.findAll();
        log.info("<-- tipos de embalagem encontrados {}", tipos);
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<TipoEmbalagem> buscaPorId(@PathVariable Long id) {
        log.info("--> busca tipoEmbalagem com id {}...", id);
        var optionalTipoEmbalagem = repositorio.findById(id);

        if(optionalTipoEmbalagem.isPresent()) {
            var tipoEmbalagem = optionalTipoEmbalagem.get();
            log.info("<-- tipoEmbalagem encontrado {}", tipoEmbalagem);
            return ResponseEntity.ok(tipoEmbalagem);
        }
        else {
            log.info("<-- tipoEmbalagem com id {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoEmbalagem> adiciona(@RequestBody TipoEmbalagem tipoEmbalagem) {
        log.info("--> salvando tipoEmbalagem {}...", tipoEmbalagem);
        var tipoEmbalagemSalvo = repositorio.save(tipoEmbalagem);
        log.info("<-- tipoEmbalagem salvo {}", tipoEmbalagemSalvo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tipoEmbalagemSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEmbalagem> altera(@PathVariable Long id, @RequestBody TipoEmbalagem tipoEmbalagem) {
        log.info("--> alterando tipoEmbalagem com id {}...", id);
        tipoEmbalagem.setId(id);
        var tipoEmbalagemAlterado = repositorio.save(tipoEmbalagem);
        log.info("<-- tipoEmbalagem alterado {}", tipoEmbalagemAlterado);

        return ResponseEntity.ok(tipoEmbalagemAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoEmbalagem> remove(@PathVariable Long id) {
        log.info("--> removendo tipoEmbalagem com id {}...", id);
        repositorio.deleteById(id);
        log.info("<-- tipoEmbalagem com id {} removido", id);

        return ResponseEntity.noContent().build();
    }
}
