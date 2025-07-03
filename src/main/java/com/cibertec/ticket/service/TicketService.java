package com.cibertec.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.DTO.TicketDetalleDTO;
import com.cibertec.ticket.model.Compra;
import com.cibertec.ticket.model.Evento;
import com.cibertec.ticket.model.Ticket;
import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.model.Zona;
import com.cibertec.ticket.repository.ICompraRepository;
import com.cibertec.ticket.repository.ITicketRepository;
import com.cibertec.ticket.repository.IUsuarioRepository;
import com.cibertec.ticket.repository.IZonaRepository;

@Service
public class TicketService {
	
	@Autowired
	private ITicketRepository repository;
	
	@Autowired
	private IZonaRepository zonaRepository;
	
	@Autowired
	private ICompraRepository compraRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	public List<Ticket> findAllTicket() {
		return repository.findAll();
	}

	public Ticket findByIdTicket(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}
	
	public List<TicketDetalleDTO> listarTicketsConDetalle() {
	    List<Ticket> tickets = repository.findAll();
	    List<TicketDetalleDTO> resultado = new ArrayList<>();

	    for (Ticket t : tickets) {
	        TicketDetalleDTO dto = new TicketDetalleDTO();
	        dto.setIdTicket(t.getIdTicket());
	        dto.setIdCompra(t.getIdCompra());

	        // Obtener la zona
	        Zona zona = zonaRepository.findById(t.getIdZona()).orElse(null);
	        if (zona != null) {
	            dto.setNombreZona(zona.getNombreZona());
	            Evento evento = zona.getEvento();
	            if (evento != null) {
	                dto.setIdEvento(evento.getIdEvento());
	                dto.setNombreEvento(evento.getNombreEvento());
	            }

	            // Obtener usuario desde la compra
	            Compra compra = compraRepository.findById(t.getIdCompra()).orElse(null);
	            if (compra != null) {
	                Usuario usuario = usuarioRepository.findById(compra.getIdUsuario()).orElse(null);
	                if (usuario != null) {
	                    dto.setCorreoUsuario(usuario.getCorreo());
	                }
	            }
	        }

	        resultado.add(dto);
	    }

	    return resultado;
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
