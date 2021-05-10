package br.com.dominio.personapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dominio.personapi.dto.MessageResponseDTO;
import br.com.dominio.personapi.dto.PersonDTO;
import br.com.dominio.personapi.model.Person;
import br.com.dominio.personapi.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
@Slf4j
@Api(value = "Endpoints to manage people")
public class PersonController {

    private final PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new people", response = PersonDTO[].class)
    public MessageResponseDTO create(@RequestBody @Valid PersonDTO objDTO) {
    	Person obj = service.fromDTO(objDTO);
        return service.save(obj);
    }

    @GetMapping
    @ApiOperation(value = "List of people", response = PersonDTO[].class)
    public ResponseEntity<List<PersonDTO>> listAll() {    	
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "People", response = PersonDTO[].class)
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id)  {
        return ResponseEntity.ok().body(service.findById(id));
    }
    
    @GetMapping("/find")
    @ApiOperation(value = "List of people for first name", response = PersonDTO[].class)
    public ResponseEntity<List<PersonDTO>> findByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(service.findByFirstName(firstName));
    }        

    @GetMapping(value="/page")
    @ApiOperation(value = "Pages of people", response = PersonDTO[].class)    
	public ResponseEntity<Page<PersonDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="firstName") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<PersonDTO> pageDto = service.findPage(page, linesPerPage, orderBy, direction);  
		return ResponseEntity.ok().body(pageDto);
	}	            
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update people", response = PersonDTO[].class)
    public MessageResponseDTO update(@PathVariable Long id, @RequestBody @Valid PersonDTO objDTO) {
    	
    	Person obj = service.fromDTO(objDTO);
    	obj.setId(id);
    	
        return service.update(id, obj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete people", response = PersonDTO[].class)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
        
}
