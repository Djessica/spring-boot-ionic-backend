package com.djessicaeickstaedt.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djessicaeickstaedt.cursomc.domain.Pedido;
import com.djessicaeickstaedt.cursomc.repositories.PedidoRepository;
import com.djessicaeickstaedt.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServico {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
