package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
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
		return MessageResponseDTO
					.builder()
					.message("Created Person with ID: " + savedPerson.getId())
					.build();
	}

	public List<PersonDTO> listAll() {
		return personRepository
					.findAll()
					.stream()
						.map(personMapper::toDTO)
						.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		//Optional<Person> optionalPerson = personRepository.findById(id);
		//if(optionalPerson.isEmpty()) {
			//throw new PersonNotFoundException(id);}
		
		return personMapper.toDTO(person);
	}

}
