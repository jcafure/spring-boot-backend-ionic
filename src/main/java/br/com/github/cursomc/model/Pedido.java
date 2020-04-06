package br.com.github.cursomc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    @OneToMany(mappedBy="id.pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Date parse, Cliente cli1, Endereco e1) {
        this.instante = parse;
        this.cliente = cli1;
        this.enderecoDeEntrega = e1;
    }
}
