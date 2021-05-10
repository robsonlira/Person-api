package br.com.dominio.personapi.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.dominio.personapi.service.DBService;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@Profile("test")
public class TestConfig {

	private final DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	
}
