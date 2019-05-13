package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Embalagem;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EmbalagemRepository extends CrudRepository<Embalagem, Long> {

    @Override
    Collection<Embalagem> findAll();
}
