package br.com.boomerang.packback.service;

import br.com.boomerang.packback.domain.Autenticacao;
import br.com.boomerang.packback.domain.Login;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    private final UsuarioService usuarioService;
    private final LoginRepository loginRepository;

    public LoginService(UsuarioService usuarioService, LoginRepository loginRepository) {
        this.usuarioService = usuarioService;
        this.loginRepository = loginRepository;
    }

    public Optional<Usuario> autentica(Autenticacao autenticacao) {
        var usuario = usuarioService.buscaPorEmail(autenticacao.getEmail());

        if(usuario.isEmpty()) {
            log.info("E-mail {} não encontrado", autenticacao.getEmail());
            return Optional.empty();
        }

        Optional<Login> login = loginRepository.findById(usuario.get().getLogin().getId());

        if(login.isEmpty()){
            log.info("Autenticação inválida para o email {}", autenticacao.getEmail());
            return Optional.empty();
        }

        if(autenticacao.getSenha().equals(login.get().getSenha())) {
            log.info("Usuário {} autenticado com sucesso", autenticacao.getEmail());
            return usuario;
        }
        else {
            log.info("Senha incorreta para o email {}", autenticacao.getEmail());
            return Optional.empty();
        }
    }
}
