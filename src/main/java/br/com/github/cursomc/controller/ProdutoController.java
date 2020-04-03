package br.com.github.cursomc.controller;

import br.com.github.cursomc.dto.ProdutoDTO;
import br.com.github.cursomc.model.Produto;
import br.com.github.cursomc.service.ProdutoService;
import br.com.github.cursomc.service.validation.utils.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById (@PathVariable Integer id) {
        Produto categoria = produtoService.findbyId(id);
        return ResponseEntity.ok(categoria);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="categorias", defaultValue="") String categorias,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }



}
