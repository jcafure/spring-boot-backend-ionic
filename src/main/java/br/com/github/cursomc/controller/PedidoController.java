package br.com.github.cursomc.controller;

import br.com.github.cursomc.model.Pedido;
import br.com.github.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById (@PathVariable Integer id) {
        Pedido categoria = pedidoService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody Pedido pedido) {
        Pedido pedidoInsert = pedidoService.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedidoInsert.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getModel() {
        Pedido clienteNewDTO = new Pedido();
        return ResponseEntity.ok(clienteNewDTO);
    }
}
