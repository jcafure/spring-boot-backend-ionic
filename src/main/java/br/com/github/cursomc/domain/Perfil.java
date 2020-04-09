package br.com.github.cursomc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Perfil {
    ADMIN (1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private final Integer cod;
    private final String descricao;

    public static Perfil toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
