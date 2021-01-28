package com.siddarthaboddu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.dto.BookCheckoutRequestDto;
import com.siddarthaboddu.dto.BookCheckoutResponseDto;
import com.siddarthaboddu.service.BookActionService;

@RestController
public class BookActionController {
	
	@Autowired
	private BookActionService bookActionService;
	
	@PostMapping("/checkout")
	public ResponseEntity<BookCheckoutResponseDto> checkoutBookItem(@RequestBody BookCheckoutRequestDto bookCheckoutRequestDto){
		
		Optional<BookCheckoutResponseDto> bookCheckoutResponseOptional = bookActionService.checkoutBook(bookCheckoutRequestDto);
		
		if(bookCheckoutResponseOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(bookCheckoutResponseOptional.get());
	}

}
