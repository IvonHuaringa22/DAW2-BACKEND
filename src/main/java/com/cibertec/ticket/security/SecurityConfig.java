package com.cibertec.ticket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.repository.IUsuarioRepository;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioRepository repository;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Deshabilita CSRF para la API de prueba
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/**").permitAll()
						.requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()
						.anyRequest().authenticated())
				.httpBasic();
		return http.build();
	}


	@Bean
	public UserDetailsService userDetailsService() {

		 return username -> {
		        Usuario usuario = repository.findByCorreo(username)
		            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

		        return User.builder()
		            .username(usuario.getCorreo())
		            .password(usuario.getContrasenia()) 
		            .roles(usuario.getRol()) 
		            .build();
		    };
	}
}
