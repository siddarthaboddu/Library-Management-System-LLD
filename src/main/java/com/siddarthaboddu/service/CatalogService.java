package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.dto.BookSearchCriteria;
import com.siddarthaboddu.entity.Book;

public interface CatalogService {
	Optional<List<Book>> getBook(BookSearchCriteria bookSearchCriteria);
}
