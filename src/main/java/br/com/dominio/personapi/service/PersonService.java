package br.com.dominio.personapi.service;

import br.com.dominio.personapi.service.exceptions.DataIntegrityException;
import br.com.dominio.personapi.service.exceptions.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;
import br.com.dominio.personapi.dto.PersonDTO;
import br.com.dominio.personapi.mapper.PersonMapper;
import br.com.dominio.personapi.dto.MessageResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dominio.personapi.model.Person;
import br.com.dominio.personapi.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    private PersonRepository repository; 
    private final PersonMapper personMapper;   
    
    @Autowired
    public PersonService(PersonRepository repository, PersonMapper personMapper) {
       this.repository = repository;
       this.personMapper  = personMapper;
    }                
    
    @Transactional
    public MessageResponseDTO save(Person obj) {
        log.info("Saving...");
        obj = repository.save(obj);
        return createMessageResponse(obj.getId(), "Created person with ID ");
    }
    
    public List<PersonDTO> listAll() {
        List<Person> allObj = repository.findAll();
         
        return allObj.stream()
                .map(obj -> personMapper.toDTO(obj))
                .collect(Collectors.toList());        
    }
    	
    public Page<PersonDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<Person> allObj = repository.findAll(pageRequest);  
				
		return allObj.map(obj -> personMapper.toDTO(obj));
	}    
    
    public PersonDTO findById(Long id) throws ObjectNotFoundException {
        Person obj = verifyIfExists(id);
        return personMapper.toDTO(obj);
    }
    
    public List<PersonDTO> findByFirstName(String name) {
    	List<Person> allObj = repository.findByFirstName(name);
    	
    	return allObj.stream()
                .map(obj -> personMapper.toDTO(obj))
                .collect(Collectors.toList());      	
    }

    @Transactional        
    public MessageResponseDTO update(Long id, Person obj) throws ObjectNotFoundException {
        verifyIfExists(id);

        log.info("Updating...");
        Person updatedObj = repository.save(obj);
        return createMessageResponse(updatedObj.getId(), "Updated person with ID ");
    }

    public void delete(Long id) throws ObjectNotFoundException {
        verifyIfExists(id);
        log.info("Deleting...");
        try {
           repository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um registro que possui movimentação");
		}
    }
       
    public Person fromDTO(PersonDTO objDTO) {
        Person obj = personMapper.toModel(objDTO);
        return obj;    	
    }
        
    private Person verifyIfExists(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                		"Objeto não encontrado! Id: " + id + ", Tipo: " + Person.class.getName()));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
