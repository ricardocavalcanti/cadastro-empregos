package com.project.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        // Informando pagina de login personalizada
        http.authorizeRequests().
                antMatchers("/").hasAnyAuthority("ADMIN","USERS")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();

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
        auth.inMemoryAuthentication()
                .withUser("administrador").password(passwordEncoder().encode("123")).authorities("ADMIN")
                .and()
                .withUser("ricardo").password(passwordEncoder().encode("abc123")).authorities("USERS");
    }

}
