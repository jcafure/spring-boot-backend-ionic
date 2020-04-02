package br.com.github.cursomc;

import br.com.github.cursomc.model.Categoria;
import br.com.github.cursomc.model.Cidade;
import br.com.github.cursomc.model.Estado;
import br.com.github.cursomc.model.Produto;
import br.com.github.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

}
