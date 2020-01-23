package com.djessicaeickstaedt.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.djessicaeickstaedt.cursomc.domain.Categoria;
import com.djessicaeickstaedt.cursomc.repositories.CategoriaRepository;
import com.djessicaeickstaedt.cursomc.services.exceptions.DataIntegrityException;
import com.djessicaeickstaedt.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categoria que tem produtos");
		}

	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	/* a classe page é pertencente ao Spring */
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		/* o pagerequest é usado para que o spring saiba como vai ser a paginação */
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
}
