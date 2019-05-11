package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
