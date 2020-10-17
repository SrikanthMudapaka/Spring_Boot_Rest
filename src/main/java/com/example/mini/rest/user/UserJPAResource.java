package com.example.mini.rest.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	@Autowired
	private UserDaoService userService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(value = "/jpa/users/{id}")
	public EntityModel<Optional<User>> retirveUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id --" + id);

		// "all-Users" , SERVER-PATH + "/users"
		// retrieveAllUsers
		
		// ******** HATEOAS *************//
		EntityModel<Optional<User>> resource = EntityModel.of(user);

		// LINKTO() and METHODON() ARE STATIC IMPORTS
		WebMvcLinkBuilder linkTo2 = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo2.withRel("all-users"));
		// ******* HATEOAS *********** //

		return resource;
	}

	@PostMapping(value = "/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		// CREATE --STATUS CODE 201
		// /user/{id} ---- savedUser.getId()

		// URI IS USED TO GET THE DETAILS IN HEADER SECTION -->
		// http://localhost:8081/users/4
		URI location = ServletUriComponentsBuilder // ^USED TO GET CURRENT REQUEST URI
				.fromCurrentRequest() // http://localhost:8081/users
				.path("/{id}") // APPENDIND {ID} TO ABOVE URI
				.buildAndExpand(savedUser.getId()).toUri(); // REPLACING /user/{id} WITH savedUser.getId()
															// to get the location in header section .
		return ResponseEntity.created(location).build();// TO GET RESPONSE 201 created

	}

	@DeleteMapping(value = "/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);

		
	}
}
