package br.com.github.cursomc.dto;

import br.com.github.cursomc.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO implements Serializable {

    private Integer idCLiente;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String nomeCliente;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

    public ClienteDTO(Cliente cliente) {
        this.idCLiente = cliente.getId();
        this.nomeCliente = cliente.getNome();
        this.email = cliente.getEmail();
    }
}
