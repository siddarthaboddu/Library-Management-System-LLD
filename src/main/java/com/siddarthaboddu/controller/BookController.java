package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.dto.BookDto;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService; 
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
		Optional<Book> bookOptional = bookService.getBookById(id);
		
		if(bookOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		Optional<List<Book>> bookListOptional = bookService.getAllBooks();
		
		if(bookListOptional.isEmpty() || bookListOptional.get().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(bookListOptional.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody BookDto book){
		Optional<Book> bookOptional = bookService.addBook(book);
		
		if(bookOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(bookOptional.get(), HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto book){
		Optional<Book> bookOptional  = bookService.updateBook(id, book);
		
		if(bookOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
		}
	}

}
