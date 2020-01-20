package com.djessicaeickstaedt.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.djessicaeickstaedt.cursomc.domain.Categoria;
import com.djessicaeickstaedt.cursomc.domain.Cidade;
import com.djessicaeickstaedt.cursomc.domain.Cliente;
import com.djessicaeickstaedt.cursomc.domain.Endereco;
import com.djessicaeickstaedt.cursomc.domain.Estado;
import com.djessicaeickstaedt.cursomc.domain.ItemPedido;
import com.djessicaeickstaedt.cursomc.domain.Pagamento;
import com.djessicaeickstaedt.cursomc.domain.PagamentoComBoleto;
import com.djessicaeickstaedt.cursomc.domain.PagamentoComCartao;
import com.djessicaeickstaedt.cursomc.domain.Pedido;
import com.djessicaeickstaedt.cursomc.domain.Produto;
import com.djessicaeickstaedt.cursomc.domain.enums.EstadoPagamento;
import com.djessicaeickstaedt.cursomc.domain.enums.TipoCliente;
import com.djessicaeickstaedt.cursomc.repositories.CategoriaRepository;
import com.djessicaeickstaedt.cursomc.repositories.CidadeRepository;
import com.djessicaeickstaedt.cursomc.repositories.ClienteRepository;
import com.djessicaeickstaedt.cursomc.repositories.EnderecoRepository;
import com.djessicaeickstaedt.cursomc.repositories.EstadoRepository;
import com.djessicaeickstaedt.cursomc.repositories.ItemPedidoRepository;
import com.djessicaeickstaedt.cursomc.repositories.PagamentoRepository;
import com.djessicaeickstaedt.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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
		
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("123456789","987654321"));
		
		Endereco en1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "99010056", cli1, c1);
		Endereco en2 = new Endereco(null, "Avenida Matoss", "105", "Sala 800", "Centro", "99010056", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(en1,en2));
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, en1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, en2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(pd1,pd2,pd3));
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(en1,en2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, pd1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, pd3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, pd2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		pd1.getItens().addAll(Arrays.asList(ip1));
		pd2.getItens().addAll(Arrays.asList(ip3));
		pd3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
