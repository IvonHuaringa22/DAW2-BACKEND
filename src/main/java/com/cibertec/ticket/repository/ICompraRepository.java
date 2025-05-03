package com.cibertec.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ticket.model.Compra;

@Repository
public interface ICompraRepository extends JpaRepository<Compra, Integer>{

}
