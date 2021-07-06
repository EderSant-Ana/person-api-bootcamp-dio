package one.digitalinnovation.personapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;

@Api("Manages person api")
public interface PersonControllerDocs {

	@ApiOperation(value = "Person creation operation")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Success person creation"),
		@ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
	})
	MessageResponseDTO createPerson(PersonDTO personDTO);
	
	@ApiOperation(value = "Returns a list of all Person registered in the system")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "List of all persons registered in the system"),
	})
	List<PersonDTO> listAll();
	
	@ApiOperation(value = "Returns a person by a given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success person found in the system"),
		@ApiResponse(code = 404, message = "Person with given id not found.")
	})
	PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException;
	
	@ApiOperation(value = "Delete a person by a valid id")
	@ApiResponses(value = {
		@ApiResponse(code = 204, message = "Success person deleted in the system"),
		@ApiResponse(code = 404, message = "Person with given id not found.")
	})
	void deleteById(@PathVariable Long id) throws PersonNotFoundException;

	@ApiOperation(value = "Person update operation")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success person update"),
		@ApiResponse(code = 400, message = "Missing required fields or worng field range value.")
	})
	MessageResponseDTO updateById(@PathVariable Long id, PersonDTO personDTO) throws PersonNotFoundException;
}
