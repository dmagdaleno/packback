package br.com.boomerang.packback.service;

import br.com.boomerang.packback.domain.Login;
import br.com.boomerang.packback.domain.Usuario;
import br.com.boomerang.packback.repository.LoginRepository;
import br.com.boomerang.packback.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repositorioDeUsuario;
    private final LoginRepository repositorioDeLogin;

    @Autowired
    public UsuarioService(UsuarioRepository repositorioDeUsuario, LoginRepository repositorioDeLogin) {
        this.repositorioDeUsuario = repositorioDeUsuario;
        this.repositorioDeLogin = repositorioDeLogin;
    }

    public Usuario salva(Usuario usuario) {
        var login = repositorioDeLogin.save(new Login(usuario.getSenha()));
        usuario.setLogin(login);
        return repositorioDeUsuario.save(usuario);
    }

    public Optional<Usuario> buscaPorEmail(String email) {
        return repositorioDeUsuario.findByEmail(email);
    }

    public Optional<Usuario> buscaPorId(Long id) {
        return repositorioDeUsuario.findById(id);
    }
}
