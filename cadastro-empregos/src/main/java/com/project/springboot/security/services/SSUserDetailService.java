package com.project.springboot.security.services;

import com.project.springboot.model.Regra;
import com.project.springboot.model.Usuario;
import com.project.springboot.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class SSUserDetailService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public SSUserDetailService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
       try{
           Usuario usuarioBanco = usuarioRepository.findByUsuario(usuario);
            if(usuarioBanco==null){
                return null;
            }else{
                return new org.springframework.security.core.userdetails.User(usuarioBanco.getNome(), usuarioBanco.getSenha(), getAuthories(usuarioBanco));
            }
       }catch (Exception e){
           e.printStackTrace();
            throw new UsernameNotFoundException("Usuário não cadastrado no sistema");
       }
    }

    private Set<GrantedAuthority> getAuthories(Usuario usuario){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Regra regra : usuario.getRegras()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(regra.getRegra());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }



}
