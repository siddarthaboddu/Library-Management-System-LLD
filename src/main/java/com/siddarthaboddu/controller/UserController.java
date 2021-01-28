package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.dto.UserCreateRequestDto;
import com.siddarthaboddu.entity.User;
import com.siddarthaboddu.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestHeader("userId") Long requestUserId){
		
		if(!userService.isLibrarian(requestUserId)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<List<User>> userListOptional = userService.getAllUsers();
		
		if(userListOptional.isEmpty()) 
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(userListOptional.get());
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestHeader("userId") Long requestUserId, @RequestBody UserCreateRequestDto userCreateRequest){
		
//		if(!userService.isLibrarian(requestUserId)) {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
		
		Optional<User> userResponseOptional = userService.createUserFromUserCreateRequestDto(userCreateRequest);
		
		if(userResponseOptional.isEmpty()) 
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(userResponseOptional.get());
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@RequestParam("id") Long id){
		Optional<User> userOptional = userService.findUserById(id);
		
		if(userOptional.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(userOptional.get());
	}

//	@GetMapping("/lol")
//	public ResponseEntity<String> lol(){
//		throw new RuntimeException("sdfkjsldfkjsd");
////		return ResponseEntity.ok("siddu");
//	}
}
