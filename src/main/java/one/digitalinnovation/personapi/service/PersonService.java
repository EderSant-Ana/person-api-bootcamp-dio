package one.digitalinnovation.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper;
	
	@Autowired
	public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
		this.personMapper = personMapper;
		this.personRepository = personRepository;
	}
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson =  personRepository.save(personToSave);
		System.out.println(savedPerson.toString());
		return MessageResponseDTO
					.builder()
					.message("Created Person with ID: " + savedPerson.getId())
					.build();
	}

}
