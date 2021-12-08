package com.project.springboot.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Regra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String regra;

    @ManyToMany(mappedBy = "regras", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarios;

    public Regra(){

    }

    public Regra (String regra){
        this.regra = regra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegra() {
        return regra;
    }

    public void setRegra(String regra) {
        this.regra = regra;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
