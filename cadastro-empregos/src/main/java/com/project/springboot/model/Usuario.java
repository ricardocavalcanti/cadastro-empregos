package com.project.springboot.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="senha")
    private String senha;

    @Column(name="nome")
    private String nome;

    @Column(name="sobre_nome")
    private String sobreNome;

    @Column(name="habilitado")
    private boolean habilitado;

    @Column(name="usuario")
    private String usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name= "usuario_id"),
        inverseJoinColumns = @JoinColumn(name= "regra_id"))
    private Collection<Regra> regras;

    public Usuario(){

    }

    public Usuario(String email, String senha, String nome, String sobreNome, boolean habilitado, String usuario) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.habilitado = habilitado;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Collection<Regra> getRegras() {
        return regras;
    }

    public void setRegras(Collection<Regra> regras) {
        this.regras = regras;
    }
}
