package br.com.github.cursomc.dto;

import br.com.github.cursomc.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO(Produto produto) {
        id = produto.getId();
        nome = produto.getNome();
        preco = produto.getPreco();
    }
}
