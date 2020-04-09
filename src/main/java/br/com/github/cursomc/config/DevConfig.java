package br.com.github.cursomc.config;

import br.com.github.cursomc.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {


    private final DBService dbService;

    @Autowired
    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instatiateDataBase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }
}
