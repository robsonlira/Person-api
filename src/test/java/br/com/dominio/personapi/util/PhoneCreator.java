package br.com.dominio.personapi.util;

import br.com.dominio.personapi.dto.PhoneDTO;
import br.com.dominio.personapi.enums.PhoneType;
import br.com.dominio.personapi.model.Phone;

public class PhoneCreator {
		
	public static PhoneDTO createPhoneDTO(){
		
		return PhoneDTO.builder()
				.number("(81) 999991234")
				.type(PhoneType.MOBILE)
				.build();						
	}

	public static Phone createPhoneEntity(){

		return Phone.builder()
				.id(1L)
				.number("(81) 999991234")
				.type(PhoneType.MOBILE)
				.build();
		
	}

	public static Phone createPhoneEntityToBeSaved(){

		return Phone.builder()
				.number("(81) 999991234")
				.type(PhoneType.MOBILE)
				.build();
		
	}	
	

}
