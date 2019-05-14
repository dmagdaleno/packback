package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

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
    public ResponseEntity<Collection<Usuario>> listaTodos() {
        log.info("--> lista todos os usuarios...");
        Collection<Usuario> usuarios = repositorio.findAll();
        log.info("<-- usuarios encontrados {}", usuarios);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> encontraPorId(@PathVariable Long id) {
        log.info("--> busca usuário com id {}...", id);
        Optional<Usuario> optionalUsuario = repositorio.findById(id);

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

}
