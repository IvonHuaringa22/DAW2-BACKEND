package com.cibertec.ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.model.Evento;
import com.cibertec.ticket.repository.IEventoRepository;

@Service
public class EventoService {

	@Autowired
	private IEventoRepository repository;

	public List<Evento> findAllEvento() {
		List<Evento> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		} else {
			return repository.findAll();
		}
	}

	public Evento findByIdEvento(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}
	
	public ResponseEntity<List<Evento>> ListarPorDisponibilidad() {
		List<Evento> eventosVisibles = repository.findByVisible("Disponible");
	    if (eventosVisibles.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(eventosVisibles);
	    }
	}

	public Evento saveEvento(Evento evento) {
		if (evento.getNombreEvento() == null) {
			throw new IllegalArgumentException("Nombre de evento es obligatorio.");
		}
		if (evento.getTipoEvento() == null) {
			throw new IllegalArgumentException("Tipo de evento es obligatorio.");
		}
		if (evento.getLugar() == null) {
			throw new IllegalArgumentException("Lugar es obligatorio.");
		}
		if (evento.getFecha() == null) {
			throw new IllegalArgumentException("Fecha es obligatorio.");
		}
		if (evento.getHora() == null) {
			throw new IllegalArgumentException("Hora es obligatorio.");
		}
		if (evento.getDescripcion() == null) {
			throw new IllegalArgumentException("Descripcion es obligatorio.");
		}
		if(evento.getDisponibilidad() == null) {
			throw new IllegalArgumentException("Disponibilidad es obligatorio.");
		}
		return repository.save(evento);
	}

	public Evento updateEvento(Evento evento, int id) {
		Evento update = repository.findById(id).orElse(null);
		update.setNombreEvento(evento.getNombreEvento());
		update.setTipoEvento(evento.getTipoEvento());
		update.setLugar(evento.getLugar());
		update.setFecha(evento.getFecha());
		update.setHora(evento.getHora());
		update.setDescripcion(evento.getDescripcion());
		update.setDisponibilidad(evento.getDisponibilidad());

		return repository.save(update);
	}

	public void deleteByIdEvento(int id) {
		if (id <= 0) {
			throw new BadCredentialsException("El id ingresado no esta permitido");
		}
		
		Evento delete = repository.findById(id).orElse(null);
		if (delete == null) {
			throw new BadCredentialsException("No existe ningun Evento con ese id");
		}
		repository.delete(delete);

	}
	
	public ResponseEntity<?> EliminarLogicoEvento(int id) {
		Optional<Evento> eventoEliminarLogico = repository.findById(id);
		
		if(eventoEliminarLogico.isPresent()) {
			Evento eventoEli = eventoEliminarLogico.get();
			eventoEli.setDisponibilidad("No Disponible");
			try {
				repository.save(eventoEli);
				return ResponseEntity.ok(eventoEli);
			} catch (Exception e) {
				System.out.println("Ocurrio algo inesperado, error: " + e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}
	
	
}