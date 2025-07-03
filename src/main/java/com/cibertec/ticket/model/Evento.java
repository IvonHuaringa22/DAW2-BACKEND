package com.cibertec.ticket.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_evento")
public class Evento {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;
    
    @Column(name = "nombre_evento", nullable = false)
    private String nombreEvento;
    
    @Column(name = "tipo_evento", nullable = false)
    private String tipoEvento;
    
    @Column(name = "lugar", nullable = false)
    private String lugar;
    
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    
    @Column(name = "hora", nullable = false)
    private LocalTime hora;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @Column(name = "disponibilidad", nullable = false)
    private String disponibilidad;
    
    
    @OneToMany(mappedBy = "evento")
    @JsonManagedReference
    private List<Zona> zonas;
}
