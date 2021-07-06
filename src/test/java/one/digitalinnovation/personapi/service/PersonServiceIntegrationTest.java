package one.digitalinnovation.personapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import javax.transaction.Transactional;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class PersonServiceIntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;
		
	@Test
	void testGivenIdThenReturnPersonDTOById() throws PersonNotFoundException {
		//PersonDTO personDTO = PersonUtils.createFakeDTO();
		Person expectedSavedPerson = PersonUtils.createFakeEntity();

		Person savedPerson = personRepository.save(expectedSavedPerson);
		
		PersonDTO personDTO = personService.findById(1L);
		
		assertThat(personDTO.getId()).isNotNull();

	}
	
	@Test
	void testGivenMissingIdReturnPersonNotFoundException() {
		
		Long id = 123L;
		
		Throwable throwable = catchThrowable(() -> personService.findById(id));
		
		BDDAssertions.then(throwable).isInstanceOf(PersonNotFoundException.class);
		
	}

}
