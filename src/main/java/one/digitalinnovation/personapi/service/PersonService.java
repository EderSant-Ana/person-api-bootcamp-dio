package one.digitalinnovation.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
	
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper;
	
	/*
	 * @Autowired public PersonService(PersonRepository personRepository,
	 * PersonMapper personMapper) { this.personMapper = personMapper;
	 * this.personRepository = personRepository; }
	 */
	
	//POST
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson =  personRepository.save(personToSave);
		return createMessageResponse(savedPerson.getId(), "Created Person with ID: ");
	}

	//GET ALL
	public List<PersonDTO> listAll() {
		return personRepository
					.findAll()
					.stream()
						.map(personMapper::toDTO)
						.collect(Collectors.toList());
	}

	//GET BY ID
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		//Optional<Person> optionalPerson = personRepository.findById(id);
		//if(optionalPerson.isEmpty()) {
			//throw new PersonNotFoundException(id);}
		
		return personMapper.toDTO(person);
	}

	//DELETE
	public void deleteById(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		
		personRepository.deleteById(id);
	}

	//PUT
	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		Person personToUpdate = personMapper.toModel(personDTO);
		
		Person updatedPerson =  personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated Person with ID: ");
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
					.builder()
					.message(message + id)
					.build();
	}

}
