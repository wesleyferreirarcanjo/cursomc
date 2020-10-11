package com.arcanjo.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.arcanjo.cursomc.domain.enums.Perfil;
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

@Service
public class DBService {

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
	
	@Autowired
	private BCryptPasswordEncoder cryptPasswordEncoder;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);	
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		Estado estado = new Estado(null, "Mina Gerais");
		Estado estado2 = new Estado(null, "Sao Paulo");

		Cidade cidade = new Cidade(null, "campinas", estado2);
		Cidade cidade2 = new Cidade(null, "sao paulo", estado2);
		Cidade cidade3 = new Cidade(null, "uberlandia", estado);

		estado.getCidades().addAll(Arrays.asList(cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade, cidade2));

		estadoRepository.saveAll(Arrays.asList(estado, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade, cidade2, cidade3));

		Cliente cliente = new Cliente(null, "ANA", "wesleyferreirarcanjo@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, cryptPasswordEncoder.encode("123"));
		
		Cliente cliente2 = new Cliente(null, "Wesley", "wesleycursomc@gmail.com", "43312642892", TipoCliente.PESSOAFISICA, cryptPasswordEncoder.encode("123"));
		cliente2.addPerfil(Perfil.ADMIN);
		
		cliente.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cliente2.getTelefones().addAll(Arrays.asList("992930872"));

		Endereco endereco = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cliente, cidade);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente,cidade2);
		
		Endereco endereco3 = new Endereco(null, "Avenida BATA", "103", "", "Centro", "45777232", cliente2,cidade2);
		
		cliente.getEndereco().addAll(Arrays.asList(endereco, endereco2));
		cliente2.getEndereco().addAll(Arrays.asList(endereco3));

		clienteRepository.saveAll(Arrays.asList(cliente, cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco, endereco2, endereco3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2020 10:30"), cliente, endereco);
		Pedido pedido2 = new Pedido(null, sdf.parse("01/10/2019 20:30"), cliente, endereco2);

		Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				sdf.parse("20/10/2020 00:00"), null);
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

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido, itemPedido2, itemPedido3));
	}
}
