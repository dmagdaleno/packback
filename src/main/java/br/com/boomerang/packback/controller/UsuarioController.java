package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private UsuarioRepository repositorio;

    @Autowired
    public UsuarioController(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Collection<Usuario>> buscaTodos() {
        log.info("--> lista todos os usuarios...");
        var usuarios = repositorio.findAll();
        log.info("<-- usuarios encontrados {}", usuarios);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> buscaPorId(@PathVariable Long id) {
        log.info("--> busca usuário com id {}...", id);
        var optionalUsuario = repositorio.findById(id);

        if(optionalUsuario.isPresent()) {
            var usuario = optionalUsuario.get();
            log.info("<-- usuario encontrado {}", usuario);
            return ResponseEntity.ok(usuario);
        }
        else {
            log.info("<-- usuario com id {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> adiciona(@RequestBody Usuario usuario) {
        log.info("--> salvando usuario {}...", usuario);
        var usuarioSalvo = repositorio.save(usuario);
        log.info("<-- usuario salvo {}", usuarioSalvo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> altera(@PathVariable Long id, @RequestBody Usuario usuario) {
        log.info("--> alterando usuario com id {}...", id);
        usuario.setId(id);
        var usuarioAlterado = repositorio.save(usuario);
        log.info("<-- usuario alterado {}", usuarioAlterado);

        return ResponseEntity.ok(usuarioAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> remove(@PathVariable Long id) {
        log.info("--> removendo usuario com id {}...", id);
        repositorio.deleteById(id);
        log.info("<-- usuario com id {} removido", id);

        return ResponseEntity.noContent().build();
    }

}
