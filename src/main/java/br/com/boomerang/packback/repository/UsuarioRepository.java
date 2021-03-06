package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Override
    Collection<Usuario> findAll();

    Optional<Usuario> findByEmail(String email);
}
