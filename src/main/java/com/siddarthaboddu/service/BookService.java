package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.dto.BookDto;
import com.siddarthaboddu.entity.Book;

public interface BookService {
	Optional<Book> getBookById(Long id);

	Optional<Book> addBook(BookDto bookDto);

	Optional<List<Book>> getAllBooks();

	Optional<Book> updateBook(Long id, BookDto book);
}
