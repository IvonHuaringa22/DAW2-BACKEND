package com.cibertec.ticket.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_usuario")
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;
	
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "correo", nullable = false)
    private String correo;
    
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;
    
    @Column(name = "rol", nullable = false)
    private String rol;
    
    @Column(name = "fecha_login")
    private LocalDateTime fechaLogin;
}
