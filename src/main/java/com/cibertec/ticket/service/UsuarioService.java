package com.cibertec.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.DTO.UsuarioDTO;
import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UsuarioDTO> findAllUsuario() {
	    List<Usuario> usuarios = repository.findAll();
	    if (usuarios.isEmpty()) {
	        return null;
	    }

	    List<UsuarioDTO> dtoList = new ArrayList<>();
	    for (Usuario usuario : usuarios) {
	        UsuarioDTO dto = new UsuarioDTO();
	        dto.setIdUsuario(usuario.getIdUsuario());
	        dto.setNombre(usuario.getNombre());
	        dto.setCorreo(usuario.getCorreo());
	        dto.setRol(usuario.getRol());
	        dtoList.add(dto);
	    }

	    return dtoList;
	}

	public UsuarioDTO findByIdUsuario(int id) {
		if (id >= 1) {
			
			Usuario u = repository.findById(id).orElse(null);
			UsuarioDTO uDTO = new UsuarioDTO();
			uDTO.setIdUsuario(u.getIdUsuario());
			uDTO.setNombre(u.getNombre());
			uDTO.setCorreo(u.getCorreo());
			uDTO.setRol(u.getRol());
			return uDTO;
			
		} else {
			return null;
		}
	}

	public UsuarioDTO saveUsuario(Usuario usuario) {
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
		
		Usuario u = repository.save(usuario);
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.setIdUsuario(u.getIdUsuario());
		uDTO.setNombre(u.getNombre());
		uDTO.setCorreo(u.getCorreo());
		uDTO.setRol(u.getRol());
		return uDTO;
	}

	public UsuarioDTO updateUsuario(Usuario usuario, int id) {
		Usuario update = repository.findById(id).orElse(null);
		if (update == null) {
			throw new BadCredentialsException("No existe ningún Usuario con ese id");
		}

		update.setNombre(usuario.getNombre());
		update.setCorreo(usuario.getCorreo());
		update.setRol(usuario.getRol());

		Usuario u = repository.save(usuario);
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.setIdUsuario(u.getIdUsuario());
		uDTO.setNombre(u.getNombre());
		uDTO.setCorreo(u.getCorreo());
		uDTO.setRol(u.getRol());
		return uDTO;
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
