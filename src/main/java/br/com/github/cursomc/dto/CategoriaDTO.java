package br.com.github.cursomc.dto;

import br.com.github.cursomc.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaDTO implements Serializable {

    private Integer idCategoria;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nomeCategoria;

    public CategoriaDTO (Categoria categoria) {
        idCategoria = categoria.getId();
        nomeCategoria = categoria.getNome();
    }
}
