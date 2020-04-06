package br.com.github.cursomc.service;

import br.com.github.cursomc.domain.EstadoPagamento;
import br.com.github.cursomc.domain.TipoCliente;
import br.com.github.cursomc.model.*;
import br.com.github.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private final CategoriaRepository categoriaRepository;
    @Autowired
    private final ProdutoRepository produtoRepository;
    @Autowired
    private final EstadoRepository estadoRepository;
    @Autowired
    private final CidadeRepository cidadeRepository;
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final EnderecoRepository enderecoRepository;
    @Autowired
    private final PedidoRepository pedidoRepository;
    @Autowired
    private final PagamentoRepository pagamentoRepository;
    @Autowired
    private final ItemPedidoRepository itemPedidoRepository;

    public DBService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, EstadoRepository estadoRepository,
                     CidadeRepository cidadeRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
                     PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public void instantiateTestDatabase() throws ParseException {

        Categoria cat1 = new Categoria();
        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);
        Produto p4 = new Produto("Mesa de escritório", 300.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2,p3, p4));

        p1.getCategorias().add(cat1);
        p1.getCategorias().add(cat1);
        p1.getCategorias().add(cat1);
        p1.getCategorias().add(cat1);


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));

        p1.getCategorias().addAll(Arrays.asList(cat1));


        categoriaRepository.saveAll(Arrays.asList(cat1));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente( "Maria Silva", "nelio.cursos@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

//        Cliente cli2 = new Cliente(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", TipoCliente.PESSOA_JURIDICA, pe.encode("123"));
//        cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
        //cli2.addPerfil(Perfil.ADMIN);

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
        //Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
       // cli2.getEnderecos().addAll(Arrays.asList(e3));

        //clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        clienteRepository.save(cli1);
        enderecoRepository.save(e1);
//
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido( sdf.parse("30/09/2017 10:32"), cli1, e1);
       // Pedido ped2 = new Pedido( sdf.parse("10/10/2017 19:35"), cli1, e2);

//        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
//        ped1.setPagamento(pagto1);

//        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped1, sdf.parse("20/10/2017 00:00"), null);
//        ped1.setPagamento(pagto2);
//
        cli1.getPedidos().add(ped1);
//
        pedidoRepository.save(ped1);
//        pagamentoRepository.save(pagto1);
//        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
//
//        ped1.getItens().add(ip1);
//
//        itemPedidoRepository.save(ip1);
    }
}
