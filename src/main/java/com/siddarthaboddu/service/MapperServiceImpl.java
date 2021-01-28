package com.siddarthaboddu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.dto.BookDto;
import com.siddarthaboddu.entity.Author;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.entity.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MapperServiceImpl implements MapperService {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Book mapBookFromBookDto(BookDto bookDto) {
		Book book = new Book();
		book.setBookCode(bookDto.getBookCode());
		book.setPublicationDate(bookDto.getPublicationDate());
		book.setTitle(bookDto.getTitle());
		
		Optional<Author> author = authorService.getAuthorById(bookDto.getAuthorId());
		Optional<Category> category  = categoryService.getCategoryById(bookDto.getCategoryId());
		if(author.isPresent())
			book.setAuthor(author.get());
		if(category.isPresent())
			book.setCategory(category.get());
		
		log.info("book mapper: {}", book);
		return book;
	}
	

}
