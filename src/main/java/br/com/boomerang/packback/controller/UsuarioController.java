package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private UsuarioRepository repositorio;

    @Autowired
    public UsuarioController(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public ResponseEntity<Collection<Usuario>> listaTodos() {
        log.info("--> lista todos os usuarios...");
        Collection<Usuario> usuarios = repositorio.findAll();
        log.info("<-- usuarios encontrados {}", usuarios);
        return ResponseEntity.ok(usuarios);
    }


}
