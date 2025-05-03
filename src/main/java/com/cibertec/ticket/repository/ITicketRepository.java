package com.cibertec.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ticket.model.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer>{

}
