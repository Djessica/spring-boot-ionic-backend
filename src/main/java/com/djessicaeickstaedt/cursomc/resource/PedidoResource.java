package com.djessicaeickstaedt.cursomc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djessicaeickstaedt.cursomc.domain.Pedido;
import com.djessicaeickstaedt.cursomc.services.PedidoServico;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoServico service;

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok(obj);
	}
}
