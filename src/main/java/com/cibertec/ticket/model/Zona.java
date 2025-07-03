package com.cibertec.ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_zona")
public class Zona {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona", nullable = false)
    private Integer idZona;
	
    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;
    
    @Column(name = "nombre_zona", nullable = false)
    private String nombreZona;
    
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "id_evento", referencedColumnName = "id_evento", insertable = false, updatable = false)
	private Evento evento;
}
