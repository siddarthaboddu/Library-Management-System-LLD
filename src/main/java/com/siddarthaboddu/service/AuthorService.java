package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.entity.Author;

public interface AuthorService {

	Optional<List<Author>> getAllAuthors();

	Optional<Author> addAuthor(Author author);

	Optional<Author> getAuthorById(Long authorId);

}
