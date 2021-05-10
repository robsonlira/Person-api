package br.com.dominio.personapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dominio.personapi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findByFirstName(String name);
}
