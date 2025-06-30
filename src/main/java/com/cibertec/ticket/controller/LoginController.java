package com.cibertec.ticket.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cibertec.ticket.DTO.LoginRequest;
import com.cibertec.ticket.DTO.LoginResponse;
import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.repository.IUsuarioRepository;
import com.cibertec.ticket.util.Token;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioRepository iusuarioRepository;
        
    LoginController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    // login protegido con roles
    
    @GetMapping("/login")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Usuario> login(Authentication authentication) {
        Usuario user = iusuarioRepository.findByCorreo(authentication.getName()).orElse(null);
        return ResponseEntity.ok(user);
    }

    // login que valida usuario y devuelve token JWT
    
	@PostMapping("/login")
	public ResponseEntity<?> loginManual(@RequestBody LoginRequest request) {
	    Optional<Usuario> optionalUsuario = iusuarioRepository.findByCorreo(request.getCorreo());
	
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();
	        
	     // Validar contraseña cifrada con BCrypt
	        if (passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
	        	
	        	// Actualiza la fecha de login
	            usuario.setFechaLogin(LocalDateTime.now());
	            iusuarioRepository.save(usuario);
	            
	            // Crea token JWT
	            String tokenJwt = Token.crearToken(usuario.getNombre(), usuario.getCorreo(), usuario.getRol());
	            
	         // Crear respuesta incluyendo fecha
	            LoginResponse response = new LoginResponse(
	            	    "Inicio de sesión exitoso",
	            	    usuario.getRol(),
	            	    tokenJwt,
	            	    usuario.getFechaLogin().toString()
	            	);
	            
	            return ResponseEntity.ok(response);

	        }
	    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
	}
}
