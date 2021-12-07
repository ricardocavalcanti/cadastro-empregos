package com.project.springboot.repository;

import com.project.springboot.model.Emprego;
import org.springframework.data.repository.CrudRepository;

public interface EmpregoRepository extends CrudRepository<Emprego, Long> {

}
