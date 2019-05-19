package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Embalagem;
import br.com.boomerang.packback.repository.EmbalagemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/embalagens")
public class EmbalagemController {

    private static final Logger log = LoggerFactory.getLogger(TipoEmbalagemController.class);

    private EmbalagemRepository repositorio;

    @Autowired
    public EmbalagemController(EmbalagemRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Iterable<Embalagem>> buscaTodos() {
        log.info("--> lista todas as embalagens...");
        var embalagems = repositorio.findAll();
        log.info("<-- embalagens encontradas {}", embalagems);
        return ResponseEntity.ok(embalagems);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Embalagem> buscaPorId(@PathVariable Long id) {
        log.info("--> busca embalagem com id {}...", id);
        var optionalEmbalagem = repositorio.findById(id);

        if(optionalEmbalagem.isPresent()) {
            var embalagem = optionalEmbalagem.get();
            log.info("<-- embalagem encontrada {}", embalagem);
            return ResponseEntity.ok(embalagem);
        }
        else {
            log.info("<-- embalagem com id {} não encontrada", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Embalagem> adiciona(@RequestBody Embalagem embalagem) {
        log.info("--> salvando embalagem {}...", embalagem);
        var embalagemSalva = repositorio.save(embalagem);
        log.info("<-- embalagem salva {}", embalagemSalva);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(embalagemSalva.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Embalagem> altera(@PathVariable Long id, @RequestBody Embalagem embalagem) {
        log.info("--> alterando embalagem com id {}...", id);
        embalagem.setId(id);
        var embalagemAlterada = repositorio.save(embalagem);
        log.info("<-- tipoEmbalagem alterado {}", embalagemAlterada);

        return ResponseEntity.ok(embalagemAlterada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Embalagem> remove(@PathVariable Long id) {
        log.info("--> removendo embalagem com id {}...", id);
        repositorio.deleteById(id);
        log.info("<-- embalagem com id {} removida", id);

        return ResponseEntity.noContent().build();
    }
}
