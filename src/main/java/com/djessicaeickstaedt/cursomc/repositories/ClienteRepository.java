package com.djessicaeickstaedt.cursomc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.djessicaeickstaedt.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/*
	 * no spring se você cria uma operação que utiliza as palavras findBy ele vai
	 * detectar que se quer fazer uma busca por email
	 * agora o ClienteRepository tem uma operação que vai na base de dados e busca um cliente por email
	 */
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
