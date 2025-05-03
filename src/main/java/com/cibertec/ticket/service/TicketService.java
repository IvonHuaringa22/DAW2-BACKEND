package com.cibertec.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.model.Ticket;
import com.cibertec.ticket.repository.ITicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private ITicketRepository repository;

	public List<Ticket> findAllTicket() {
		List<Ticket> list = repository.findAll();
		if (list.isEmpty()) {
			return repository.findAll();
		} else {
			return null;
		}
	}

	public Ticket findByIdTicket(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}

	public Ticket saveTicket(Ticket ticket) {
		if (ticket.getIdCompra() == null) {
			throw new IllegalArgumentException("ID de compra es obligatorio.");
		}
		if (ticket.getIdZona() == null) {
			throw new IllegalArgumentException("ID de zona es obligatorio.");
		}
		return repository.save(ticket);
	}

	public Ticket updateTicket(Ticket ticket, int id) {
		Ticket update = repository.findById(id).orElse(null);
		if (update == null) {
			throw new BadCredentialsException("No existe ningún Ticket con ese id");
		}

		update.setIdCompra(ticket.getIdCompra());
		update.setIdZona(ticket.getIdZona());

		return repository.save(update);
	}

	public void deleteByIdTicket(int id) {
		if (id <= 0) {
			throw new BadCredentialsException("El id ingresado no está permitido");
		}

		Ticket delete = repository.findById(id).orElse(null);
		if (delete == null) {
			throw new BadCredentialsException("No existe ningún Ticket con ese id");
		}
		repository.delete(delete);
	}
}
