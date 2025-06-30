package com.cibertec.ticket.service;

import java.time.LocalDateTime;
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
	    // Validación mínima: método de pago no puede ser nulo o vacío
		if (compra.getMetodoPago() == null || compra.getMetodoPago().isEmpty()) {
		    throw new IllegalArgumentException("Método de pago es obligatorio.");
		}

		String metodo = compra.getMetodoPago().toUpperCase();

		if (!metodo.equals("TARJETA") && !metodo.equals("EFECTIVO")) {
		    throw new IllegalArgumentException("Método de pago no válido. Solo se permite TARJETA o EFECTIVO.");
		}

		if (metodo.equals("TARJETA")) {
		    compra.setEstadoPago("Pendiente");
		} else {
		    compra.setEstadoPago("Pagado");
		}

	    // Asignar fecha actual si no viene definida
	    if (compra.getFechaCompra() == null) {
	        compra.setFechaCompra(LocalDateTime.now());
	    }
	    // Validar que el ID de usuario esté presente
	    if (compra.getIdUsuario() == null) {
	        throw new IllegalArgumentException("ID de usuario es obligatorio.");
	    }

	    return repository.save(compra);
	}

	public Compra updateCompra(Compra compra, int id) {
		Compra update = repository.findById(id).orElse(null);
		if (update == null) {
			throw new BadCredentialsException("No existe ninguna Compra con ese id");
		}

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
