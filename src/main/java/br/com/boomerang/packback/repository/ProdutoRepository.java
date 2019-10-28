package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    Collection<Produto> findAllByRegiaoId(Long regiaoId);
}
