package br.com.github.cursomc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO implements Serializable {

    private String email;
    private String senha;
}
