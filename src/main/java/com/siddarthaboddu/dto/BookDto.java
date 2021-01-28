package com.siddarthaboddu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookDto {
	
	private Long id;
	private String bookCode;
	private String title;
	private Date publicationDate;
	
	private Long authorId;
	private Long categoryId;
	
}
