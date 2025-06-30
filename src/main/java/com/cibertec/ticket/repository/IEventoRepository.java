package com.cibertec.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ticket.model.Evento;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Integer>{
	List<Evento> findByVisible(String visible);
}
