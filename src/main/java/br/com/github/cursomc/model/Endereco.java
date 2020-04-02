package br.com.github.cursomc.model;

import br.com.github.cursomc.dto.ClienteNewDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="cidade_id")
    private Cidade cidade;

    public Endereco(ClienteNewDTO clienteNewDTO, Cliente cliente, Cidade cidade) {
        this.logradouro = clienteNewDTO.getLogradouro();
        this.numero = clienteNewDTO.getNumero();
        this.complemento = clienteNewDTO.getComplemento();
        this.bairro = clienteNewDTO.getBairro();
        this.cep = clienteNewDTO.getCep();
        this.cliente = cliente;
        this.cidade = cidade;

    }
}
