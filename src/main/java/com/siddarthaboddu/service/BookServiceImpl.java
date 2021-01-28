package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.dto.BookDto;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MapperService mapperService;
	
	public Optional<Book> getBookById(Long id){
		return bookRepository.findById(id);
	}

	@Override
	public Optional<Book> addBook(BookDto bookDto) {
		
		Book book = mapperService.mapBookFromBookDto(bookDto);
		
		return Optional.of(bookRepository.save(book));
	}

	@Override
	public Optional<List<Book>> getAllBooks() {
		return Optional.of(bookRepository.findAll());
	}

	@Override
	public Optional<Book> updateBook(Long id, BookDto bookUpdate) {
		if(bookRepository.findById(id) != null) {
			Book book = mapperService.mapBookFromBookDto(bookUpdate);
			return Optional.of(bookRepository.save(book));		
		}
		else {
			return Optional.empty();
		}
		
	}

}
	