package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.entity.BookItem;
import com.siddarthaboddu.service.BookItemService;

@RestController
public class BookItemController {

	@Autowired
	private BookItemService bookItemService;
	
	@GetMapping("/bookItems")
	public ResponseEntity<List<BookItem>> getAllBookItems(){
		Optional<List<BookItem>> bookItemList = bookItemService.getAllBookItems();
		
		if(bookItemList.isEmpty() || bookItemList.get().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok(bookItemList.get());
		}
	}
	
	@GetMapping("/bookItems/{id}")
	public ResponseEntity<BookItem> getBookItemById(@PathVariable("id") Long bookItemId){
		Optional<BookItem> bookItemOptional = bookItemService.findById(bookItemId);
		
		if(bookItemOptional.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok(bookItemOptional.get());
		}
	}
	
	
	@GetMapping("/books/{id}/bookItems")
	public ResponseEntity<List<BookItem>> updateBook(@PathVariable("id") Long bookId){
		Optional<List<BookItem>> bookItemListOptional = bookItemService.getBookItemsForBook(bookId);
		
		if(bookItemListOptional.isEmpty() || bookItemListOptional.get().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(bookItemListOptional.get());
	}
	
	@PostMapping("/books/{id}/bookItems")
	public ResponseEntity<BookItem> addBookItemForBook(@PathVariable("id") Long bookId, @RequestBody BookItem bookItem){
		Optional<BookItem> bookItemOptional = bookItemService.addBookItemForBook(bookId, bookItem);
		
		if(bookItemOptional.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok(bookItemOptional.get());
		}
	}
}
