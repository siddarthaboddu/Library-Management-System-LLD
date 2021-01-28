package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.constant.BookStatus;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.entity.BookItem;
import com.siddarthaboddu.repository.BookItemRepository;

@Service
public class BookItemServiceImpl implements BookItemService {

	@Autowired
	private BookItemRepository bookItemRepository;

	@Autowired
	private BookService bookService;

	@Override
	public Optional<List<BookItem>> getAllBookItems() {
		List<BookItem> bookItemList = bookItemRepository.findAll();

		if (bookItemList == null || bookItemList.isEmpty())
			return Optional.empty();
		return Optional.of(bookItemList);
	}

	@Override
	public Optional<BookItem> findById(Long bookItemId) {
		return bookItemRepository.findById(bookItemId);
	}

	@Override
	public Optional<List<BookItem>> getBookItemsForBook(Long bookId) {
		Optional<Book> bookOptional = bookService.getBookById(bookId);

		if (bookOptional.isEmpty()) {
			return Optional.empty();
		}

		List<BookItem> bookItemList = bookOptional.get().getBookItems();

		if (bookItemList == null || bookItemList.isEmpty())
			return Optional.empty();

		return Optional.of(bookItemList);
	}

	@Override
	public Optional<BookItem> addBookItemForBook(Long bookId, BookItem bookItem) {
		Optional<Book> bookOptional = bookService.getBookById(bookId);

		if (bookOptional.isEmpty()) {
			return Optional.empty();
		}
		
		bookItem.setBook(bookOptional.get());
		
		return Optional.ofNullable(bookItemRepository.save(bookItem));
	}

	@Override
	public Optional<BookItem> findBookItemByBookIdAndStatus(Book book, BookStatus available) {
		List<BookItem> list = bookItemRepository.findByBookAndStatus(book, available);
		if(list == null || list.isEmpty())
			return Optional.empty();
		return Optional.of(list.get(0));
	}

}
