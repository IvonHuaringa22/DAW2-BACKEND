package com.cibertec.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ticket.model.Evento;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Integer>{

}
