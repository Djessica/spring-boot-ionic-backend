package com.djessicaeickstaedt.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.djessicaeickstaedt.cursomc.domain.Categoria;
import com.djessicaeickstaedt.cursomc.domain.Produto;
import com.djessicaeickstaedt.cursomc.repositories.CategoriaRepository;
import com.djessicaeickstaedt.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto pd1 = new Produto(null,"Computador", 2000.00);
		Produto pd2 = new Produto(null,"Impressora", 800.00);
		Produto pd3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(pd1,pd2,pd3));
		cat2.getProdutos().addAll(Arrays.asList(pd2));
		
		
		pd1.getCategorias().addAll(Arrays.asList(cat1));
		pd2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		pd3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(pd1,pd2,pd3));
	}

}
