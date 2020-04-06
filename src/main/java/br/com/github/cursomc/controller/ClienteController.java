package br.com.github.cursomc.controller;

import br.com.github.cursomc.dto.ClienteDTO;
import br.com.github.cursomc.dto.ClienteNewDTO;
import br.com.github.cursomc.model.Cliente;
import br.com.github.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        Cliente cliente = clienteService.buildCliente(clienteDTO);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clienteDTOS = clienteService.findAll();
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @GetMapping(value="/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPage);
        Page<ClienteDTO> clienteDTOS = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(clienteDTOS);
    }
}
