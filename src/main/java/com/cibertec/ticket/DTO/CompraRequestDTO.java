package com.cibertec.ticket.DTO;

import lombok.Data;

@Data
public class CompraRequestDTO {
	private String metodoPago;
    private Integer idZonaSeleccionada;
    private Integer cantidad; // número de tickets a generar
}
