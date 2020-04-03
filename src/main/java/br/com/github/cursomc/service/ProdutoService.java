package br.com.github.cursomc.service;

import br.com.github.cursomc.exception.ObjetoNaoEncontradoException;
import br.com.github.cursomc.model.Categoria;
import br.com.github.cursomc.model.Produto;
import br.com.github.cursomc.repository.CategoriaRepository;
import br.com.github.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto findbyId(Integer id) {
        Optional<Produto> cliente = produtoRepository.findById(id);
        return cliente.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Objeto não encontrado! ID: " + id + "tipo " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
