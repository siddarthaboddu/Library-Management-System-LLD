package com.siddarthaboddu.service;

import java.util.Optional;

import com.siddarthaboddu.dto.BookCheckoutRequestDto;
import com.siddarthaboddu.dto.BookCheckoutResponseDto;

public interface BookActionService {

	Optional<BookCheckoutResponseDto> checkoutBook(BookCheckoutRequestDto bookCheckoutRequestDto);

}
