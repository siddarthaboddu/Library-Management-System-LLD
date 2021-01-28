package com.siddarthaboddu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.dto.BookSearchCriteria;
import com.siddarthaboddu.entity.Author;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.entity.Category;
import com.siddarthaboddu.repository.BookRepository;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CategoryService categoryService;

//	@Override
//	public Optional<List<Book>> getBook(BookSearchCriteria bookSearchCriteria) {
//		Optional<List<Book>> bookList = bookRepository.searchByCriteria(bookSearchCriteria.getTitle(),
//				bookSearchCriteria.getAuthor(), bookSearchCriteria.getCategory(),
//				bookSearchCriteria.getPublicationStartDate(), bookSearchCriteria.getPublicationEndDate());
//
//		return bookList;
//	}

	@Override
	public Optional<List<Book>> getBook(BookSearchCriteria bookSearchCriteria) {
		Optional<List<Book>> bookList = bookRepository.findAllBySearchCriteria(bookSearchCriteria.getTitle(),
				bookSearchCriteria.getPublicationStartDate(), bookSearchCriteria.getPublicationEndDate());

		Set<Book> bookSet = new HashSet<>();

		if (bookList.isPresent()) {
			bookSet.addAll(bookList.get());
		}

		if (bookSearchCriteria.getAuthorId() != null) {
			Optional<Author> authorOptional = authorService.getAuthorById(bookSearchCriteria.getAuthorId());
			if (authorOptional.isPresent()) {
				bookSet.addAll(authorOptional.get().getBooks());
			}
		}

		if (bookSearchCriteria.getCategoryId() != null) {
			Optional<Category> categoryOptional = categoryService.getCategoryById(bookSearchCriteria.getCategoryId());
			if (categoryOptional.isPresent()) {
				bookSet.addAll(categoryOptional.get().getBooks());
			}
		}

		return bookList;
	}

}
