package com.cibertec.ticket.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
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

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private IUsuarioRepository iusuarioRepository;
    
    @GetMapping("/login")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Usuario> login(Authentication authentication) {
        Usuario user = iusuarioRepository.findByCorreo(authentication.getName()).orElse(null);
        return ResponseEntity.ok(user);
    }
}
