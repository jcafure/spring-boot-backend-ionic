package br.com.github.cursomc.service;

import br.com.github.cursomc.domain.EstadoPagamento;
import br.com.github.cursomc.exception.ObjetoNaoEncontradoException;
import br.com.github.cursomc.model.ItemPedido;
import br.com.github.cursomc.model.PagamentoComBoleto;
import br.com.github.cursomc.model.Pedido;
import br.com.github.cursomc.repository.PagamentoRepository;
import br.com.github.cursomc.repository.PedidoRepository;
import br.com.github.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteService clienteService;
    private final BoletoService boletoService;
    private final ProdutoService produtoService;


    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository,
                         ProdutoRepository produtoRepository, ClienteService clienteService, BoletoService boletoService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteService = clienteService;
        this.boletoService = boletoService;
        this.produtoService = produtoService;
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> cliente = pedidoRepository.findById(id);
        return cliente.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Objeto não encontrado! ID: " + id + "tipo " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.findbyId(pedido.getCliente().getId()));
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()) {
                ip.setDesconto(0.0);
                ip.setPreco(ip.getId().getProduto().getPreco());
                ip.getId().setPedido(pedido);

        }
        //itemPedidoRepository.saveAll(pedido.getItens());
       // emailService.sendOrderConfirmationEmail(pedido);
        return pedido;
    }
}
