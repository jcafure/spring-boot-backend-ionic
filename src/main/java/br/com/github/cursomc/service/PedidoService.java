package br.com.github.cursomc.service;

import br.com.github.cursomc.domain.EstadoPagamento;
import br.com.github.cursomc.exception.ObjetoNaoEncontradoException;
import br.com.github.cursomc.model.*;
import br.com.github.cursomc.repository.ItemPedidoRepository;
import br.com.github.cursomc.repository.PagamentoRepository;
import br.com.github.cursomc.repository.PedidoRepository;
import br.com.github.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteService clienteService;
    private final BoletoService boletoService;
    private final ItemPedidoRepository itemPedidoRepository;


    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository,
                         ProdutoRepository produtoRepository, ClienteService clienteService, BoletoService boletoService, ProdutoService produtoService, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteService = clienteService;
        this.boletoService = boletoService;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> cliente = pedidoRepository.findById(id);
        return cliente.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Objeto não encontrado! ID: " + id + "tipo " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        Pedido pedidoInsert = new Pedido();
        pedidoInsert.setInstante(new Date());
        pedidoInsert.setCliente(clienteService.findbyId(pedido.getCliente().getId()));
        pedidoInsert.setPagamento(pedido.getPagamento());
        pedidoInsert.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE.getCod());
        pedidoInsert.getPagamento().setPedido(pedido);
        if (pedidoInsert.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedidoInsert.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedidoInsert.getInstante());
        }
        pedidoRepository.save(pedidoInsert);

        pagamentoRepository.save(pedidoInsert.getPagamento());

        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setProduto(produtoRepository.getOne(itemPedido.getProduto().getId()));
            itemPedido.setPreco(itemPedido.getProduto().getPreco());
            itemPedido.setPedido(pedidoInsert);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        // emailService.sendOrderConfirmationEmail(pedido);
        return pedido;
    }
}
