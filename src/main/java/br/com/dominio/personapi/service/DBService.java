package br.com.dominio.personapi.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.com.dominio.personapi.enums.PhoneType;
import br.com.dominio.personapi.model.Person;
import br.com.dominio.personapi.model.Phone;
import br.com.dominio.personapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DBService {
		
	// gerador de cpf em www.4devs.com.br/gerador_de_cpf

	private final PersonRepository repo;	
	
	public void instantiateTestDatabase() throws ParseException {
				
		Phone phone1 = Phone.builder()
				.number("(81) 999991234")
				.type(PhoneType.MOBILE)
				.build();

		Phone phone2 = Phone.builder()
				.number("(81) 33321111")
				.type(PhoneType.HOME)
				.build();
				
		Person p1 = Person.builder()
				.firstName("Jose")
				.lastName("Maria")
				.cpf("307.031.760-05")
				.birthDate(LocalDate.of(1950, 2, 3))
				.phones(Arrays.asList(phone1, phone2))
				.build();
		
		Phone phone3 = Phone.builder()
				.number("(81) 33321111")
				.type(PhoneType.HOME)
				.build();

		Person p2 = Person.builder()
				.firstName("Pedro")
				.lastName("Camaro")
				.cpf("328.318.030-04")
				.birthDate(LocalDate.of(1950, 2, 3))
				.phones(Arrays.asList(phone3))
				.build();

		repo.saveAll(Arrays.asList(p1, p2));	
		
	}

}
