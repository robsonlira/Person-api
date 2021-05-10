package br.com.dominio.personapi.repository;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.dominio.personapi.model.Person;
import br.com.dominio.personapi.util.PersonCreator;


@DataJpaTest
@DisplayName("Tests for Person Repository")
@Log4j2
public class PersonRepositoryTest {
		
	@Autowired
    private PersonRepository repository;
			
	@Test
    @DisplayName("Save persists person when Successful")
    void save_PersistPerson_WhenSuccessful(){

        Person personToBeSaved = PersonCreator.createPersonToBeSavedEntity();
        
        Person personSaved = this.repository.save(personToBeSaved);

        Assertions.assertThat(personSaved).isNotNull();

        Assertions.assertThat(personSaved.getId()).isNotNull();

        Assertions.assertThat(personSaved.getFirstName()).isEqualTo(personToBeSaved.getFirstName());
    }
	
	 @Test
	 @DisplayName("Save updates person when Successful")
	 void save_UpdatesPerson_WhenSuccessful(){
	        Person personToBeSaved = PersonCreator.createPersonToBeSavedEntity();
	        
	        Person personSaved = this.repository.save(personToBeSaved);
	        log.info("Saved: " + personSaved);
	        
	        personSaved.setFirstName("Joao");
	        log.info("Updated " + personSaved);
	        log.info("Updated " + personSaved);
	        log.info("Updated " + personSaved);
	        	        
	        Person personUpdated = this.repository.save(personSaved);
	        log.info("Updated " + personUpdated);
	        
	        
	        Assertions.assertThat(personUpdated).isNotNull();

	        Assertions.assertThat(personUpdated.getId()).isNotNull();

	        Assertions.assertThat(personUpdated.getFirstName()).isEqualTo(personSaved.getFirstName());
	    }
	
	 @Test
	 @DisplayName("Find By Name returns list of person when Successful")
	 void findByFirstName_ReturnsListOfPerson_WhenSuccessful(){
	        Person personToBeSaved = PersonCreator.createPersonToBeSavedEntity();

	        Person personSaved = this.repository.save(personToBeSaved);

	        String name = personSaved.getFirstName();

	        List<Person> persons = this.repository.findByFirstName(name);

	        Assertions.assertThat(persons)
	                .isNotEmpty()
	                .contains(personSaved);

	    }
	 
	 @Test
	 @DisplayName("Find By Name returns empty list when no person is found")
	 void findByFirstName_ReturnsEmptyList_WhenPersonIsNotFound(){
	      List<Person> animes = this.repository.findByFirstName("abc");

	      Assertions.assertThat(animes).isEmpty();
	 }
	 
	 
	 @Test
	 @DisplayName("Delete removes person when Successful")
	 void delete_RemovesPerson_WhenSuccessful(){
	        Person personToBeSaved = PersonCreator.createPersonToBeSavedEntity();

	        Person personSaved = this.repository.save(personToBeSaved);

	        this.repository.delete(personSaved);

	        Optional<Person> optional = this.repository.findById(personSaved.getId());

	        Assertions.assertThat(optional).isEmpty();

	    }	 
	 
	 
}
