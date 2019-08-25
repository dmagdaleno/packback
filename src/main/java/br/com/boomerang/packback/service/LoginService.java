package br.com.boomerang.packback.service;

import br.com.boomerang.packback.domain.Autenticacao;
import br.com.boomerang.packback.domain.Login;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UsuarioService usuarioService;
    private final LoginRepository loginRepository;

    public LoginService(UsuarioService usuarioService, LoginRepository loginRepository) {
        this.usuarioService = usuarioService;
        this.loginRepository = loginRepository;
    }

    public Optional<Usuario> autentica(Autenticacao autenticacao) {
        var usuario = usuarioService.buscaPorEmail(autenticacao.getEmail());

        if(usuario.isEmpty())
            throw new IllegalArgumentException("E-mail não encontrado");

        Optional<Login> login = loginRepository.findById(usuario.get().getLogin().getId());

        if(login.isEmpty())
            throw new IllegalArgumentException("Autenticação inválida");

        if(autenticacao.getSenha().equals(login.get().getSenha()))
            return usuario;
        else
            return Optional.empty();
    }
}
