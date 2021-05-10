package br.com.dominio.personapi.util;

import br.com.dominio.personapi.dto.MessageResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonPostRequestBodyCreator {
	
	public static MessageResponseDTO createPersonPostRequestBody(Long expectedId) {

		log.info("Recebendo..." + expectedId);
				
		return MessageResponseDTO.builder()
				.message("Created person with ID " + expectedId)
				.build();
	}
	
	

}
