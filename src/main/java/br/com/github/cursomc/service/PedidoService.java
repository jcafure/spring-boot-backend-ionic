package br.com.github.cursomc.service;

import br.com.github.cursomc.exception.ObjetoNaoEncontradoException;
import br.com.github.cursomc.model.Pedido;
import br.com.github.cursomc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> cliente = pedidoRepository.findById(id);
        return cliente.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Objeto não encontrado! ID: " + id + "tipo " + Pedido.class.getName()));
    }
}
