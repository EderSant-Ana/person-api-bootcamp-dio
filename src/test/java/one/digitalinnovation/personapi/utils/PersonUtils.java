package one.digitalinnovation.personapi.utils;

import java.time.LocalDate;
import java.util.Collections;

import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;

public class PersonUtils {
	
	private static final String FIST_NAME = "Eder";
	private static final String LAST_NAME = "Sant'ana";
	private static final String CPF_NUMBER = "111.129.444-22";
	private static final Long PERSON_ID = 1L;
	private static final LocalDate BIRTH_DATE= LocalDate.of(1983, 05, 22);
	
	public static PersonDTO createFakeDTO() {
		return PersonDTO.builder()
				.firstName(FIST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("22-05-1983")
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();				
	}

	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();				
	}


}
