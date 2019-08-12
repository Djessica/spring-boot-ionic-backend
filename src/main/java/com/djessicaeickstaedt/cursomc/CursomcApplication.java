package com.djessicaeickstaedt.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.djessicaeickstaedt.cursomc.domain.Categoria;
import com.djessicaeickstaedt.cursomc.domain.Cidade;
import com.djessicaeickstaedt.cursomc.domain.Estado;
import com.djessicaeickstaedt.cursomc.domain.Produto;
import com.djessicaeickstaedt.cursomc.repositories.CategoriaRepository;
import com.djessicaeickstaedt.cursomc.repositories.CidadeRepository;
import com.djessicaeickstaedt.cursomc.repositories.EstadoRepository;
import com.djessicaeickstaedt.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;

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
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		
		Cidade c1 = new Cidade(null, "Uberlandia",e1);
		Cidade c2 = new Cidade(null, "São Paulo",e2);
		Cidade c3 = new Cidade(null, "Campinas",e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(pd1,pd2,pd3));
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

}
