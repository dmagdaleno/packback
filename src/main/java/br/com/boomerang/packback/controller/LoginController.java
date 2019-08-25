package br.com.boomerang.packback.controller;

import br.com.boomerang.packback.domain.Autenticacao;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/autentica")
    public ResponseEntity<Usuario> autentica(@RequestBody Autenticacao autenticacao) {
        return loginService.autentica(autenticacao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
