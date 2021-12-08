package com.project.springboot.repository;

import com.project.springboot.model.Regra;
import org.springframework.data.repository.CrudRepository;

public interface RegraRepository extends CrudRepository<Regra, Long> {
    Regra findByRegra(String regra);
}
