package com.project.springboot.security.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.springboot.model.Regra;
import com.project.springboot.model.Usuario;
import com.project.springboot.repository.RegraRepository;
import com.project.springboot.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RegraRepository regraRepository; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {	
		// Criando usuarios iniciais para primeiro acesso ao sistema
		regraRepository.save(new Regra("USER"));
		regraRepository.save(new Regra("ADMIN"));
		Regra regraAdmin = regraRepository.findByRegra("ADMIN");
		Regra regraUsuario = regraRepository.findByRegra("USER");
		
		Usuario usuarioAdmin = new Usuario("admin@email.com", passwordEncoder.encode("admin"), "ADMINISTRADOR", "SUPER_USER", Usuario.HABILITADO , "admin");
		usuarioAdmin.setRegras(Arrays.asList(regraAdmin));
		usuarioRepository.save(usuarioAdmin);
		
		Usuario usuario = new Usuario("user@email.com", passwordEncoder.encode("user"), "USUARIO", "USER", Usuario.HABILITADO , "user");
		usuario.setRegras(Arrays.asList(regraUsuario));
		usuarioRepository.save(usuario);
	}

}
