package com.cibertec.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ticket.model.Zona;

@Repository
public interface IZonaRepository extends JpaRepository<Zona, Integer>{
	List<Zona> findByIdEvento(Integer idEvento);
}
