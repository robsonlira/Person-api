package br.com.dominio.personapi.controller;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.dominio.personapi.dto.MessageResponseDTO;
import br.com.dominio.personapi.dto.PersonDTO;
import br.com.dominio.personapi.model.Person;
import br.com.dominio.personapi.service.PersonService;
import br.com.dominio.personapi.util.PersonCreator;
import br.com.dominio.personapi.util.PersonPostRequestBodyCreator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
public class PersonControllerTest {
	
	@InjectMocks
	private PersonController controller;
		
	@Mock
	private PersonService serviceMock;
		
	@BeforeEach
    void setUp(){
        PageImpl<PersonDTO> personPage = new PageImpl<>(Arrays.asList(PersonCreator.createValidPerson())); 

        List<PersonDTO> personList = Arrays.asList(PersonCreator.createValidPerson());         
        
        PersonDTO personDTO = PersonCreator.createValidPerson();
        
        BDDMockito.when(serviceMock.findPage(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(personPage);
        
        BDDMockito.when(serviceMock.listAll())
               .thenReturn(personList);
        
        BDDMockito.when(serviceMock.findById(ArgumentMatchers.anyLong()))
               .thenReturn(PersonCreator.createValidPerson());

        Person expectedSavedPerson = PersonCreator.createValidPersonEntity();        
        
        BDDMockito.when(serviceMock.save(ArgumentMatchers.any(Person.class)))
               .thenReturn(PersonPostRequestBodyCreator.
            		        createPersonPostRequestBody(expectedSavedPerson.getId()));

	}
	
	@Test
    @DisplayName("List returns list of person inside page object when successful")
    void list_ReturnsPageOfPersonInsidePageObject_WhenSuccessful(){
        String expectedName = PersonCreator.createValidPerson().getFirstName();

        Page<PersonDTO> personPage = controller.findPage(null, null, null, null).getBody();

        Assertions.assertThat(personPage).isNotNull();

        Assertions.assertThat(personPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(personPage.toList().get(0).getFirstName()).isEqualTo(expectedName);
    }	
	
	@Test
    @DisplayName("ListAll returns list of person when successful")
    void listAll_ReturnsListOfPerson_WhenSuccessful(){
        String expectedName = PersonCreator.createValidPerson().getFirstName();

        List<PersonDTO> persons = controller.listAll().getBody();

        Assertions.assertThat(persons)
        .isNotNull()
        .isNotEmpty()
        .hasSize(1);

        Assertions.assertThat(persons.get(0).getFirstName()).isEqualTo(expectedName);
    }	
	
	@Test
    @DisplayName("FindById returns person when successful")
    void findById_ReturnsPerson_WhenSuccessful(){
        Long expectedId = PersonCreator.createValidPerson().getId();

        PersonDTO person = controller.findById(1L).getBody();

        Assertions.assertThat(person).isNotNull();

        Assertions.assertThat(person.getId()).isNotNull().isEqualTo(expectedId);
    }	
	
	
	@Test
    @DisplayName("Create returns person when successful")
    void create_ReturnsPerson_WhenSuccessful(){

		PersonDTO person = PersonCreator.createPersonToBeSaved();
		log.info("Creating..." + person);
		
        String message = "";
        MessageResponseDTO mrDTO = controller.create(person);

        log.info("Returning..." + mrDTO);        
        
        message = mrDTO.getMessage();
        
        Assertions.assertThat(message).isNotNull().isEqualTo("Created person with ID 1") ;

    }	
		

}
