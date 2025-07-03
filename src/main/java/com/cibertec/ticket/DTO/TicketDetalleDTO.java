package com.cibertec.ticket.DTO;

import lombok.Data;

@Data
public class TicketDetalleDTO {
    private Integer idTicket;
    private Integer idCompra;
    private String nombreZona;
    private String correoUsuario;
    private Integer idEvento;
    private String nombreEvento;

}
