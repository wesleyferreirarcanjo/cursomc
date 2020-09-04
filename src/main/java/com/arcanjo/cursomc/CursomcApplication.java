package com.arcanjo.cursomc;



import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arcanjo.cursomc.domain.Categoria;
import com.arcanjo.cursomc.domain.Cidade;
import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.domain.Endereco;
import com.arcanjo.cursomc.domain.Estado;
import com.arcanjo.cursomc.domain.ItemPedido;
import com.arcanjo.cursomc.domain.Pagamento;
import com.arcanjo.cursomc.domain.PagamentoComBoleto;
import com.arcanjo.cursomc.domain.PagamentoComCartao;
import com.arcanjo.cursomc.domain.Pedido;
import com.arcanjo.cursomc.domain.Produto;
import com.arcanjo.cursomc.domain.enums.EstadoPagamento;
import com.arcanjo.cursomc.domain.enums.TipoCliente;
import com.arcanjo.cursomc.repositories.CategoriaRepository;
import com.arcanjo.cursomc.repositories.CidadeRepository;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.repositories.EnderecoRepository;
import com.arcanjo.cursomc.repositories.EstadoRepository;
import com.arcanjo.cursomc.repositories.ItemPedidoRepository;
import com.arcanjo.cursomc.repositories.PagamentoRepository;
import com.arcanjo.cursomc.repositories.PedidoRepository;
import com.arcanjo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
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
		

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado estado = new Estado(null, "Mina Gerais");
		Estado estado2 = new Estado(null, "Sao Paulo");
		
		Cidade cidade = new Cidade(null, "campinas", estado2);
		Cidade cidade2 = new Cidade(null, "sao paulo", estado2);
		Cidade cidade3 = new Cidade(null, "uberlandia", estado);
		
		estado.getCidades().addAll(Arrays.asList(cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade, cidade2));
		
		estadoRepository.saveAll(Arrays.asList(estado, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade,cidade2,cidade3));
		
		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco endereco = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cliente, cidade);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente, cidade2);
		
		clienteRepository.saveAll(Arrays.asList(cliente));
		enderecoRepository.saveAll(Arrays.asList(endereco, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2020 10:30"), cliente, endereco);
		Pedido pedido2 = new Pedido(null, sdf.parse("01/10/2019 20:30"), cliente, endereco2);
		
		Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2020 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento, pagamento2));
		
		ItemPedido itemPedido = new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, p2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido2));
		
		p1.getItens().addAll(Arrays.asList(itemPedido));
		p2.getItens().addAll(Arrays.asList(itemPedido3));
		p3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido,itemPedido2,itemPedido3));
		
	}

}
