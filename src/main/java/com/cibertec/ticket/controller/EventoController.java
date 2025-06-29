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

import com.cibertec.ticket.model.Evento;
import com.cibertec.ticket.service.EventoService;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

	@Autowired
	private EventoService service;
	

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Evento>> findAllEvento() {
        return ResponseEntity.ok(service.findAllEvento());
    }

    @GetMapping("/{id}")
     @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Evento> findByIdEvento(@PathVariable int id) {
        Evento evento = service.findByIdEvento(id);
        if (evento != null) {
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
      @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Evento> saveEvento(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveEvento(evento));
    }

    @PutMapping("/{id}")
      @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Evento> updateEvento(@RequestBody Evento evento, @PathVariable int id) {
        return ResponseEntity.ok(service.updateEvento(evento, id));
    }

    @DeleteMapping("/{id}")
      @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteByIdEvento(@PathVariable int id) {
        service.deleteByIdEvento(id);
        return ResponseEntity.noContent().build();
    }
}
