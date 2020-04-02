package br.com.github.cursomc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;

}
