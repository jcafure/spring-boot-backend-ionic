package br.com.github.cursomc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    public static Integer toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCodigo())) {
                return x.codigo;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
