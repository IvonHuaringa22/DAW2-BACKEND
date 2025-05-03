package com.cibertec.ticket.model;

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
@Table(name = "tb_ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ticket", nullable = false)
	public Integer idTicket;

	@Column(name = "id_compra", nullable = false)
	public Integer idCompra;

	@Column(name = "id_zona", nullable = false)
	public Integer IdZona;

	@ManyToOne
	@JoinColumn(name = "id_compra", referencedColumnName = "id_compra", insertable = false, updatable = false)
	private Compra compra;

	@ManyToOne
	@JoinColumn(name = "id_zona", referencedColumnName = "id_zona", insertable = false, updatable = false)
	private Zona zona;
}
