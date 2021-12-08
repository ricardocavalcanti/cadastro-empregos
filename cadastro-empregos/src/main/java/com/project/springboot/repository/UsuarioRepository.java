package com.project.springboot.repository;

import com.project.springboot.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository <Usuario, Long>{
    Usuario findByUsuario(String usuario);
}
