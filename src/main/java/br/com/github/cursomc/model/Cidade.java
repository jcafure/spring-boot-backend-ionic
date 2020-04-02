package br.com.github.cursomc.model;

import br.com.github.cursomc.dto.ClienteNewDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name="estado_id")
    private Estado estado;

    public Cidade (ClienteNewDTO clienteNewDTO) {
        this.id = clienteNewDTO.getCidadeId();
        this.nome = null;
        this.estado = null;
    }
}
