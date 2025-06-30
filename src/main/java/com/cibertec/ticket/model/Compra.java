package com.cibertec.ticket.model;

import java.time.LocalDateTime;

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
@Table(name = "tb_compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra", nullable = false)
	public Integer idCompra;
	
	@Column(name = "id_usuario", nullable = false)
	public Integer idUsuario;
	
	@Column(name = "fecha_compra", nullable = false)
	public LocalDateTime fechaCompra;
	
	@Column(name = "metodo_pago", nullable = false)
	public String metodoPago;
	
	@Column(name = "estado_pago", nullable = false)
	public String estadoPago;
	

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
	private Usuario usuario;
}
