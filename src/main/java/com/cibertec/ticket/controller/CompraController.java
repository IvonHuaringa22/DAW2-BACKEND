package com.cibertec.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.ticket.model.Compra;
import com.cibertec.ticket.service.CompraService;

@RestController
@RequestMapping("/api/compra")
public class CompraController {
	
	@Autowired
	private CompraService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Compra>> findAllCompra() {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.findAllCompra());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Compra> findByIdCompra(int id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.findByIdCompra(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Compra> saveCompra(Compra compra) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCompra(compra));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Compra> updateCompra(Compra compra) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCompra(compra));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteByIdCompra(int id) {
		service.deleteByIdCompra(id);
	}
}
