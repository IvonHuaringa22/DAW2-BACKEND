package com.cibertec.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> findAllUsuario() {
		List<Usuario> list = repository.findAll();
		if (list.isEmpty()) {
			return repository.findAll();
		} else {
			return null;
		}
	}

	public Usuario findByIdUsuario(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}

	public Usuario saveUsuario(Usuario usuario) {
		if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
			throw new IllegalArgumentException("Nombre es obligatorio.");
		}
		if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
			throw new IllegalArgumentException("Correo es obligatorio.");
		}
		if (usuario.getContrasenia() == null || usuario.getContrasenia().isEmpty()) {
			throw new IllegalArgumentException("Contraseña es obligatoria.");
		}
		if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
			throw new IllegalArgumentException("Rol es obligatorio.");
		}
		 String claveCodificada = passwordEncoder.encode(usuario.getContrasenia());
		 usuario.setContrasenia(claveCodificada);
		
		return repository.save(usuario);
	}

	public Usuario updateUsuario(Usuario usuario, int id) {
		Usuario update = repository.findById(id).orElse(null);
		if (update == null) {
			throw new BadCredentialsException("No existe ningún Usuario con ese id");
		}

		update.setNombre(usuario.getNombre());
		update.setCorreo(usuario.getCorreo());
		update.setRol(usuario.getRol());

		return repository.save(update);
	}

	public void deleteByIdUsuario(int id) {
		if (id <= 0) {
			throw new BadCredentialsException("El id ingresado no está permitido");
		}

		Usuario delete = repository.findById(id).orElse(null);
		if (delete == null) {
			throw new BadCredentialsException("No existe ningún Usuario con ese id");
		}
		repository.delete(delete);
	}
}
