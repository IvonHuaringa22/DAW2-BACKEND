package com.cibertec.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.model.Compra;
import com.cibertec.ticket.repository.ICompraRepository;

@Service
public class CompraService {

	@Autowired
	private ICompraRepository repository;

	public List<Compra> findAllCompra() {
		List<Compra> list = repository.findAll();
		if (list.isEmpty()) {
			return repository.findAll();
		} else {
			return null;
		}
	}

	public Compra findByIdCompra(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}

	public Compra saveCompra(Compra compra) {
		if (compra.getIdUsuario() == null) {
			throw new IllegalArgumentException("ID de usuario es obligatorio.");
		}
		if (compra.getFechaCompra() == null) {
			throw new IllegalArgumentException("Fecha de compra es obligatoria.");
		}
		if (compra.getMetodoPago() == null || compra.getMetodoPago().isEmpty()) {
			throw new IllegalArgumentException("Método de pago es obligatorio.");
		}
		if (compra.getEstadoPago() == null) {
			throw new IllegalArgumentException("Estado de pago es obligatorio.");
		}
		return repository.save(compra);
	}

	public Compra updateCompra(Compra compra, int id) {
		Compra update = repository.findById(id).orElse(null);
		if (update == null) {
			throw new BadCredentialsException("No existe ninguna Compra con ese id");
		}

		update.setIdUsuario(compra.getIdUsuario());
		update.setFechaCompra(compra.getFechaCompra());
		update.setMetodoPago(compra.getMetodoPago());
		update.setEstadoPago(compra.getEstadoPago());

		return repository.save(update);
	}

	public void deleteByIdCompra(int id) {
		if (id <= 0) {
			throw new BadCredentialsException("El id ingresado no está permitido");
		}

		Compra delete = repository.findById(id).orElse(null);
		if (delete == null) {
			throw new BadCredentialsException("No existe ninguna Compra con ese id");
		}
		repository.delete(delete);
	}
}
