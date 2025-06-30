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

import com.cibertec.ticket.model.Zona;
import com.cibertec.ticket.service.ZonaService;

@RestController
@RequestMapping("/api/zona")
public class ZonaController {

	@Autowired
	private ZonaService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Zona>> findAllZona() {
        return ResponseEntity.ok(service.findAllZona());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Zona> findByIdZona(@PathVariable int id) {
        Zona zona = service.findByIdZona(id);
        if (zona != null) {
            return ResponseEntity.ok(zona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Zona> saveZona(@RequestBody Zona zona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveZona(zona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Zona> updateZona(@RequestBody Zona zona, @PathVariable int id) {
        return ResponseEntity.ok(service.updateZona(zona,id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteByIdZona(@PathVariable int id) {
        service.deleteByIdZona(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<Zona>> obtenerZonasPorEvento(@PathVariable Integer idEvento) {
    	List<Zona> zonas = service.obtenerZonasPorEvento(idEvento);
        return ResponseEntity.ok(zonas);
    }

}
