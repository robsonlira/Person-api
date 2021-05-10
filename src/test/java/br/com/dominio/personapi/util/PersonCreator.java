package br.com.dominio.personapi.util;

import java.time.LocalDate;
import java.util.Collections;

import br.com.dominio.personapi.dto.PersonDTO;
import br.com.dominio.personapi.model.Person;

public class PersonCreator {
	
	// gerador de cpf em www.4devs.com.br/gerador_de_cpf

	public static final LocalDate BIRTH_DATE = LocalDate.of(1950, 2, 3);
    private static final String CPF_NUMBER = "307.031.760-05";	
	
	public static PersonDTO createPersonToBeSaved(){
								
		return PersonDTO.builder()
				.firstName("Jose")
				.lastName("Maria")
				.cpf(CPF_NUMBER)
				.birthDate("03-02-1950")
                .phones(Collections.singletonList(PhoneCreator.createPhoneDTO()))
				.build();
	}

	public static PersonDTO createValidPerson(){
		
		return PersonDTO.builder()
				.id(1L)
				.firstName("Jose")
				.lastName("Maria")
				.cpf(CPF_NUMBER)
				.birthDate("03-02-1950")
                .phones(Collections.singletonList(PhoneCreator.createPhoneDTO()))
				.build();
	}

	public static PersonDTO createValidUpdatePerson(){
		
		return PersonDTO.builder()
				.id(1L)
				.firstName("Jose")
				.lastName("Maria da Silva")
				.cpf(CPF_NUMBER)
				.birthDate("03-02-1950")
                .phones(Collections.singletonList(PhoneCreator.createPhoneDTO()))
				.build();
	}	

	
   public static Person createPersonToBeSavedEntity(){
		
		return Person.builder()
				.firstName("Jose")
				.lastName("Maria")
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneCreator.createPhoneEntityToBeSaved()))
				.build();
	}	
	
	public static Person createValidPersonEntity(){
		
		return Person.builder()
				.id(1L)
				.firstName("Jose")
				.lastName("Maria")
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneCreator.createPhoneEntity()))
				.build();
	}
	
	
}
