package com.siddarthaboddu.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.constant.BookStatus;
import com.siddarthaboddu.dto.BookCheckInRequestDto;
import com.siddarthaboddu.dto.BookCheckInResponseDto;
import com.siddarthaboddu.dto.BookCheckoutRequestDto;
import com.siddarthaboddu.dto.BookCheckoutResponseDto;
import com.siddarthaboddu.entity.Book;
import com.siddarthaboddu.entity.BookAction;
import com.siddarthaboddu.entity.BookItem;
import com.siddarthaboddu.entity.User;
import com.siddarthaboddu.exception.BookCheckoutLimitReachedException;
import com.siddarthaboddu.exception.BookItemNotFoundException;
import com.siddarthaboddu.exception.BookNotAvailableException;
import com.siddarthaboddu.exception.BookNotFoundException;
import com.siddarthaboddu.exception.InvalidBookCheckoutRequestException;
import com.siddarthaboddu.exception.UserNotFoundException;
import com.siddarthaboddu.repository.BookActionRepository;

@Service
public class BookActionServiceImpl implements BookActionService {

	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private BookActionRepository bookActionRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookItemService bookItemService;
	
	@Autowired
	private UserService userService;
	
	@Value("${user.checkout.limit}")
	private Integer userCheckoutLimit;
	
	@Override
	public Optional<BookCheckoutResponseDto> checkoutBook(BookCheckoutRequestDto bookCheckoutRequestDto) {

		Optional<User> userOptional = userService.findUserById(bookCheckoutRequestDto.getUserId());
		
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		
		Optional<Book> bookOptional = bookService.getBookById(bookCheckoutRequestDto.getBookId());
		
		if(bookOptional.isEmpty())
			throw new BookNotFoundException();
		
		Optional<BookItem> bookItemOptional = bookItemService.findBookItemByBookIdAndStatus(bookOptional.get(), BookStatus.AVAILABLE);
		
		if(bookItemOptional.isEmpty()) {
			throw new BookNotAvailableException();
		}
		
		BookItem bookItem = bookItemOptional.get();
		
		bookItem.setStatus(BookStatus.NOT_AVAILABLE);
		
		BookAction bookAction = new BookAction();
		bookAction.setBookItem(bookItem);
		bookAction.setCheckInDate(LocalDate.now());
		bookAction.setInUse(true);
		bookAction.setMemberCard(userOptional.get().getMemberCard());
		
		Integer userCheckoutRemainingLimit = this.findCheckoutLeftForUser(userOptional.get());
		if(userCheckoutRemainingLimit <= 0) {
			throw new BookCheckoutLimitReachedException();
		}
		
		bookAction = bookActionRepository.save(bookAction);
		
		BookCheckoutResponseDto bookCheckoutResponseDto = new BookCheckoutResponseDto();
		bookCheckoutResponseDto.setBookItem(bookItem);
		bookCheckoutResponseDto.setCheckoutsLeft(userCheckoutRemainingLimit - 1);
		
		return Optional.of(bookCheckoutResponseDto);
		
	}

	public Integer findCheckoutLeftForUser(User user) {
		Integer currentBookCheckoutCount = bookActionRepository.countByMemberCardAndInUse(user.getMemberCard(), true);
		if(currentBookCheckoutCount == null) currentBookCheckoutCount = 0;
		return userCheckoutLimit - currentBookCheckoutCount;
	}
	
	public Optional<BookCheckInResponseDto> checkInBook(BookCheckInRequestDto bookCheckInRequest) {
		
		Optional<User> userOptional = userService.findUserById(bookCheckInRequest.getUserId());
		
		if(userOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		
		Optional<BookItem> bookItemOptional = bookItemService.findById(bookCheckInRequest.getBookItemId());
		
		if(bookItemOptional.isEmpty()) {
			throw new BookItemNotFoundException();
		}
		
		Optional<BookAction> bookActionOptional = bookActionRepository.findByBookItemAndMemberCard(bookItemOptional.get(), userOptional.get().getMemberCard());
		
		if(bookActionOptional.isEmpty())
			throw new InvalidBookCheckoutRequestException();
		
		bookActionOptional.get().setCheckOutDate(LocalDate.now());

		Double overDueCharge = pricingService.getOverDueCharge(bookActionOptional.get());
		
		bookActionOptional.get().setOverDueCharge(overDueCharge);
		bookActionOptional.get().setInUse(false);
		
		bookActionRepository.save(bookActionOptional.get());
		
		return Optional.of(new BookCheckInResponseDto(overDueCharge));
		
	}

}
