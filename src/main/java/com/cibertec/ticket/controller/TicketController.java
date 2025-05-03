package com.cibertec.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.ticket.model.Ticket;
import com.cibertec.ticket.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	@Autowired
	private TicketService service;

    @GetMapping
    
    public ResponseEntity<List<Ticket>> findAllTicket() {
        return ResponseEntity.ok(service.findAllTicket());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> findByIdTicket(@PathVariable int id) {
        Ticket ticket = service.findByIdTicket(id);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTicket(ticket));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable int id) {
        return ResponseEntity.ok(service.updateTicket(ticket, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteByIdTicket(@PathVariable int id) {
        service.deleteByIdTicket(id);
        return ResponseEntity.noContent().build();
    }
}
