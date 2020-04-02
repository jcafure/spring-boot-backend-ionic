package br.com.github.cursomc.controller;

import br.com.github.cursomc.dto.ClienteNewDTO;
import br.com.github.cursomc.model.Cliente;
import br.com.github.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById (@PathVariable Integer id) {
        Cliente categoria = clienteService.findbyId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
      Cliente cliente = clienteService.buildClienteFromDTO(clienteNewDTO);
      clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getModel() {
        ClienteNewDTO clienteNewDTO = new ClienteNewDTO();
        return ResponseEntity.ok(clienteNewDTO);
    }

}
