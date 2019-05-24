package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Pontuacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PontuacaoRepository extends CrudRepository<Pontuacao, Long> {

    List<Pontuacao> findAllByUsuarioId(Long id);

}
