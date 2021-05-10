package br.com.dominio.personapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dominio.personapi.dto.PersonDTO;
import br.com.dominio.personapi.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
