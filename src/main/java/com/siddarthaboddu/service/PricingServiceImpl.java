package com.siddarthaboddu.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.entity.BookAction;

@Service
public class PricingServiceImpl implements PricingService {

	@Value("${book.checkout.max.days}")
	private Long bookCheckoutMaxDays;
	
	@Value("${book.checkout.overdue.charge}")
	private Double bookCheckoutOverDueCharge;
	
	@Override
	public Double getOverDueCharge(BookAction bookAction) {		
		long daysDiff = ChronoUnit.DAYS.between(bookAction.getCheckInDate(), bookAction.getCheckOutDate());
		
		return this.getTotalPricing(Math.max(0, daysDiff - bookCheckoutMaxDays), bookCheckoutOverDueCharge);
	}

	private Double getTotalPricing(long days, double perDayCharge) {
		return days * perDayCharge;
	}

}
