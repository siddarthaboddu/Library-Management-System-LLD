package com.siddarthaboddu.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	public List<Book> findAll();

	public Optional<Book> findByBookCode(String bookCode);

//	@Query("select b from Book b where (:title is null OR b.title = :title) AND (:author is null OR b.author = :author)"+
//	" AND (:category is null OR b.category = :category) AND (:publicationStartDate is null OR b.publicationDate >= :publicationStartDate) " + 
//			"AND (:publicationEndDate is null OR b.publicationDate <= :publicationEndDate)")
//	public Optional<List<Book>> searchByCriteria(@Param("title") String title, @Param("author") String author,
//			@Param("category") String category, @Param("publicationStartDate") Date publicationStartDate,
//			@Param("publicationEndDate") Date publicationEndDate);

	@Query("select b from Book b where (:title is null OR b.title = :title) "
			+ " AND  (:publicationStartDate is null OR b.publicationDate >= :publicationStartDate) "
			+ " AND (:publicationEndDate is null OR b.publicationDate <= :publicationEndDate)")
	public Optional<List<Book>> findAllBySearchCriteria(@Param("title") String title,
			@Param("publicationStartDate") Date publicationStartDate,
			@Param("publicationEndDate") Date publicationEndDate);
}
