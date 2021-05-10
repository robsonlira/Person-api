package br.com.dominio.personapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.dominio.personapi.dto.MessageResponseDTO;
import br.com.dominio.personapi.dto.PersonDTO;
import br.com.dominio.personapi.model.Person;
import br.com.dominio.personapi.repository.PersonRepository;
import br.com.dominio.personapi.util.PersonCreator;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService service;
		
	@Mock
	private PersonRepository repositoryMock;
		
	@BeforeEach
    void setUp(){
				
        PageImpl<Person> personPage = new PageImpl<>(Arrays.asList(PersonCreator.createValidPersonEntity())); 
        PageImpl<PersonDTO> personPageDTO = new PageImpl<>(Arrays.asList(PersonCreator.createValidPerson())); 
        
        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(personPage);

        List<Person> personList = Arrays.asList(PersonCreator.createValidPersonEntity());         

        BDDMockito.when(repositoryMock.findAll())
               .thenReturn(personList);
        
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong()))
               .thenReturn(Optional.of(PersonCreator.createValidPersonEntity()));
        
        Person expectedSavedPerson = PersonCreator.createValidPersonEntity();
        
        BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Person.class)))
               .thenReturn(expectedSavedPerson);        
	}
	
	@Test
    @DisplayName("List returns list of person inside page object when successful")
    void findPage_ReturnsPageOfPersonInsidePageObject_WhenSuccessful(){
        String expectedName = PersonCreator.createValidPerson().getFirstName();

        Page<PersonDTO> personPage = service.findPage(1, 1, "firstName", "ASC" );

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

        List<PersonDTO> persons = service.listAll();

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

        PersonDTO person = service.findById(1L);

        Assertions.assertThat(person).isNotNull();

        Assertions.assertThat(person.getId()).isNotNull().isEqualTo(expectedId);
    }	
	
	
	@Test
    @DisplayName("Create returns person when successful")
    void create_ReturnsPerson_WhenSuccessful(){

		Person person = PersonCreator.createValidPersonEntity();
		
        Person expectedSavedPerson = PersonCreator.createValidPersonEntity();

		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		
        MessageResponseDTO succesMessage = service.save(person);
        
        Assertions.assertThat(succesMessage).isNotNull().isEqualTo(expectedSuccessMessage) ;

    }		
	

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }	
	
}
