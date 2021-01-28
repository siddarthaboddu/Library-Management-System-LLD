package com.siddarthaboddu.dto;

import com.siddarthaboddu.entity.BookItem;

import lombok.Data;

@Data
public class BookCheckoutResponseDto {

	private BookItem bookItem;
	private Integer checkoutsLeft;
	
}
