package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    Collection<Produto> findAllByRegiaoId(Long regiaoId);

    @Query("SELECT p FROM Produto p INNER JOIN p.tags t WHERE t.descricao in (:tags)")
    Collection<Produto> findAllByTags(Set<String> tags);
}
