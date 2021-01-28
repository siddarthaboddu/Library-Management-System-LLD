package com.siddarthaboddu.service;

import com.siddarthaboddu.dto.BookDto;
import com.siddarthaboddu.entity.Book;

public interface MapperService {

	Book mapBookFromBookDto(BookDto bookDto);

}
