package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.entity.Author;
import com.siddarthaboddu.service.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAllAuthors(){
		Optional<List<Author>> authorList = authorService.getAllAuthors();
		
		if(authorList.isEmpty() || authorList.get().isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(authorList.get(), HttpStatus.OK);
	}
	
	@PostMapping("/authors")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author){
		Optional<Author> authorResponseOptional = authorService.addAuthor(author);
		
		if(authorResponseOptional.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(authorResponseOptional.get(), HttpStatus.CREATED);
	}
}
