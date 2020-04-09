package br.com.github.cursomc.model;

import br.com.github.cursomc.domain.TipoCliente;
import br.com.github.cursomc.dto.ClienteNewDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    @JsonIgnore
    private String senha;
    private String cpfOuCnpj;
    private Integer tipo;

    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="Telefone")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();
//
//    public Cliente(ClienteNewDTO clienteNewDTO) {
//        this.nome = clienteNewDTO.getNome();
//        this.email = clienteNewDTO.getEmail();
//        this.cpfOuCnpj = clienteNewDTO.getCpfOuCnpj();
//        this.tipo = clienteNewDTO.getTipo();
//        this.senha = clienteNewDTO.getSenha();
//
//    }

    public Cliente(String nome, String email, String s1, TipoCliente pessoaFisica, String encode) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = s1;
        this.tipo = pessoaFisica.getCodigo();
        this.senha = encode;
    }

    public Cliente(Integer idCLiente, String nomeCliente, String email, Object o, Object o1, Object o2) {

    }
}
