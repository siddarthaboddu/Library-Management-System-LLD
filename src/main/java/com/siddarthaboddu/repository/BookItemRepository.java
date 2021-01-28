package com.siddarthaboddu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.constant.BookStatus;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.entity.BookItem;

@Repository
public interface BookItemRepository extends PagingAndSortingRepository<BookItem, Long>{

	public List<BookItem> findAll();

	public List<BookItem> findByBookAndStatus(Book book, BookStatus available);
}
