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

import com.cibertec.ticket.DTO.CompraRequestDTO;
import com.cibertec.ticket.model.Compra;
import com.cibertec.ticket.service.CompraService;

@RestController
@RequestMapping("/api/compra")
public class CompraController {
	
	@Autowired
	private CompraService service;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Compra>> findAllCompra() {
	    List<Compra> compras = service.findAllCompra();
	    return ResponseEntity.ok(compras);
	}

	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Compra> findByIdCompra(int id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.findByIdCompra(id));
	}
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> saveCompra(@RequestBody CompraRequestDTO compraDTO) {
	    service.saveCompra(compraDTO);
	    return ResponseEntity.status(HttpStatus.CREATED).body("Compra realizada correctamente.");
	}

	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Compra> updateCompra(@RequestBody Compra compra, @PathVariable int id) {
	    return ResponseEntity.ok(service.updateCompra(compra, id));
	}

	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteByIdCompra(int id) {
		service.deleteByIdCompra(id);
	}
}
