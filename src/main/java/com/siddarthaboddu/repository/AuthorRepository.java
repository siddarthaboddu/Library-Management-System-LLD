package com.siddarthaboddu.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.Author;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>{

	public List<Author> findAll();
}
