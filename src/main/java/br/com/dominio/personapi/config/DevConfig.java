package br.com.dominio.personapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.dominio.personapi.service.DBService;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class DevConfig {

	private final DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto=}")
	private String strategy;
		
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}
	
	
}
