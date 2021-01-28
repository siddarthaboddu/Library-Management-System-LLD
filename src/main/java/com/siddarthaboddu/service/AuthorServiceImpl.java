package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.entity.Author;
import com.siddarthaboddu.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Optional<List<Author>> getAllAuthors() {
		return Optional.ofNullable(authorRepository.findAll());
	}

	@Override
	public Optional<Author> addAuthor(Author author) {
		Author createdAuthor = authorRepository.save(author);
		return Optional.ofNullable(createdAuthor);
	}

	@Override
	public Optional<Author> getAuthorById(Long authorId) {
		return authorRepository.findById(authorId);
	}

}
