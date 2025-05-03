package com.cibertec.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ticket.model.Zona;

@Repository
public interface IZonaRepository extends JpaRepository<Zona, Integer>{

}
