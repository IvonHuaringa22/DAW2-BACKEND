package com.cibertec.ticket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.DTO.CompraRequestDTO;
import com.cibertec.ticket.model.Compra;
import com.cibertec.ticket.model.Ticket;
import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.model.Zona;
import com.cibertec.ticket.repository.ICompraRepository;
import com.cibertec.ticket.repository.IUsuarioRepository;
import com.cibertec.ticket.repository.IZonaRepository;

@Service
public class CompraService {

	@Autowired
	private ICompraRepository repository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IZonaRepository zonaRepository;
	
	@Autowired
	private TicketService ticketService;


	public List<Compra> findAllCompra() {
		return repository.findAll();

	}

	public Compra findByIdCompra(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}
	
	public Compra saveCompra(CompraRequestDTO dto) {
	    // 1. Validar método de pago
	    String metodo = Optional.ofNullable(dto.getMetodoPago())
	        .map(String::toUpperCase)
	        .orElseThrow(() -> new IllegalArgumentException("Método de pago es obligatorio."));

	    if (!metodo.equals("TARJETA") && !metodo.equals("EFECTIVO")) {
	        throw new IllegalArgumentException("Método de pago inválido. Solo TARJETA o EFECTIVO.");
	    }

	    Zona zona = zonaRepository.findById(dto.getIdZonaSeleccionada())
	        .orElseThrow(() -> new IllegalArgumentException("Zona no encontrada."));

	    String correo = SecurityContextHolder.getContext().getAuthentication().getName();
	    Usuario usuario = usuarioRepository.findByCorreo(correo)
	        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));

	    Compra compra = new Compra();
	    compra.setMetodoPago(metodo);
	    compra.setEstadoPago(metodo.equals("TARJETA") ? "Pendiente" : "Pagado");
	    compra.setFechaCompra(LocalDateTime.now());
	    compra.setIdUsuario(usuario.getIdUsuario());

	    Compra saved = repository.save(compra);
	    
	    int cantidad = Optional.ofNullable(dto.getCantidad()).orElse(1);
	    for (int i = 0; i < cantidad; i++) {
	        Ticket ticket = new Ticket();
	        ticket.setIdCompra(saved.getIdCompra());
	        ticket.setIdZona(dto.getIdZonaSeleccionada());
	        ticketService.saveTicket(ticket);
	    }
	    return saved;
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
