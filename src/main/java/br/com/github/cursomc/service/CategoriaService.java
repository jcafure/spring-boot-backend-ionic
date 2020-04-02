package br.com.github.cursomc.service;

import br.com.github.cursomc.dto.CategoriaDTO;
import br.com.github.cursomc.exception.DataIntegrityException;
import br.com.github.cursomc.exception.ObjetoNaoEncontradoException;
import br.com.github.cursomc.model.Categoria;
import br.com.github.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjetoNaoEncontradoException(
                "Objeto não encontrado! ID: " + id + ", tipo " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria novaCategoria = findById(categoria.getId());
        updateData(novaCategoria, categoria);
        return categoriaRepository.save(novaCategoria);
    }


    public Categoria buildCategoria(CategoriaDTO categoriaDTO) {
        return Categoria.builder().id(categoriaDTO.getIdCategoria()).nome(categoriaDTO.getNomeCategoria()).build();
    }

    private void updateData(Categoria novaCategoria, Categoria categoria) {
        novaCategoria.setNome(categoria.getNome());
    }

    public void delete(Integer id) {
        findById(id);
        try{
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
    }

    public Page<Categoria>findPage(Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC, "nome");
        return categoriaRepository.findAll(pageRequest);
    }
}
