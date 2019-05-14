package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Movimentacao;
import org.springframework.data.repository.CrudRepository;

public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {
}
