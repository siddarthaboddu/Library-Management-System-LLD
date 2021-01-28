package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.constant.BookStatus;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.entity.BookItem;

public interface BookItemService {

	Optional<List<BookItem>> getAllBookItems();

	Optional<BookItem> findById(Long bookItemId);

	Optional<List<BookItem>> getBookItemsForBook(Long bookId);

	Optional<BookItem> addBookItemForBook(Long bookId, BookItem bookItem);

	Optional<BookItem> findBookItemByBookIdAndStatus(Book book, BookStatus available);

}
