package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.dto.BookSearchCriteria;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.service.CatalogService;

@RestController
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/search/books")
	public ResponseEntity<List<Book>> searchBooks(BookSearchCriteria bookSearchCriteria) {
		Optional<List<Book>> bookListOptional = catalogService.getBook(bookSearchCriteria);
		if(bookListOptional.isEmpty() || bookListOptional.get().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<>(bookListOptional.get(), HttpStatus.OK);
	}
}
