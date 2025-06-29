package com.cibertec.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.cibertec.ticket.model.Zona;
import com.cibertec.ticket.repository.IZonaRepository;

@Service
public class ZonaService {

	@Autowired
	private IZonaRepository repository;

	public List<Zona> findAllZona() {
		List<Zona> list = repository.findAll();
		if (list.isEmpty()) {
			return repository.findAll();
		} else {
			return null;
		}
	}

	public Zona findByIdZona(int id) {
		if (id >= 1) {
			return repository.findById(id).orElse(null);
		} else {
			return null;
		}
	}

	public Zona saveZona(Zona zona) {
		if (zona.getIdEvento() == null) {
			throw new IllegalArgumentException("Id del evento es obligatorio.");
		}
		if (zona.getNombreZona() == null || zona.getNombreZona().isEmpty()) {
			throw new IllegalArgumentException("Nombre de zona es obligatorio.");
		}
		if (zona.getPrecio() == null || zona.getPrecio() < 0) {
			throw new IllegalArgumentException("Precio debe ser mayor o igual a 0.");
		}
		if (zona.getCapacidad() == null || zona.getCapacidad() <= 0) {
			throw new IllegalArgumentException("Capacidad debe ser mayor que 0.");
		}
		return repository.save(zona);
	}

	public Zona updateZona(Zona zona, int id) {
		Zona update = repository.findById(id).orElse(null);
		if (update == null) {
			throw new BadCredentialsException("No existe ninguna Zona con ese id");
		}

		update.setIdEvento(zona.getIdEvento());
		update.setNombreZona(zona.getNombreZona());
		update.setPrecio(zona.getPrecio());
		update.setCapacidad(zona.getCapacidad());

		return repository.save(update);
	}

	public void deleteByIdZona(int id) {
		if (id <= 0) {
			throw new BadCredentialsException("El id ingresado no está permitido");
		}

		Zona delete = repository.findById(id).orElse(null);
		if (delete == null) {
			throw new BadCredentialsException("No existe ninguna Zona con ese id");
		}
		repository.delete(delete);
	}
}
