package com.project.springboot.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.springboot.repository.UsuarioRepository;
import com.project.springboot.security.services.SSUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    private SSUserDetailService userDetailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailService(usuarioRepository);
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        // Informando pagina de login personalizada
        http.authorizeRequests().
                 antMatchers("/","h2-console/**").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();

//
//        http.authorizeRequests().
//            antMatchers("/").access("hasAnyAuthority('USERS,'ADMIN')")
//                .antMatchers("/admin").access("hasAnyAuthority('ADMIN')")
//                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").permitAll();
    }

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Usuario e senha para acesso ao spring security
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());


//        auth.inMemoryAuthentication()
//                .withUser("administrador").password(passwordEncoder().encode("123")).authorities("ADMIN")
//                .and()
//                .withUser("ricardo").password(passwordEncoder().encode("abc123")).authorities("USERS");

    }

}
