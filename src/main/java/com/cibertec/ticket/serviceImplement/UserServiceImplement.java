package com.cibertec.ticket.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.repository.IUsuarioRepository;

@Service
public class UserServiceImplement implements UserDetailsService{

	@Autowired
	private IUsuarioRepository dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = dao.findByCorreo(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: "+ email+ "no existe."));
		
		return new UserDetailImplement(usuario);
	}
		
}
