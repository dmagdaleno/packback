package br.com.boomerang.packback.repository;

import br.com.boomerang.packback.domain.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long> { }
