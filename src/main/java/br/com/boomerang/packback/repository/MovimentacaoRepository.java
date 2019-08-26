package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Movimentacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {

    @Override
    List<Movimentacao> findAll();

    List<Movimentacao> findByUsuarioOrigemId(Long usuarioOrigemId);

}
