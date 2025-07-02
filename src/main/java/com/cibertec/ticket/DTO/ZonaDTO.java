package com.cibertec.ticket.DTO;

import lombok.Data;

@Data
public class ZonaDTO {
	private Integer idZona;
    private String nombreZona;
    private Double precio;
    private Integer capacidad;
    private Integer idEvento;
    private String nombreEvento;

}
