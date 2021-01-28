package com.siddarthaboddu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookSearchCriteria {
	private String title;
	private Long authorId;
	private Long categoryId;
	private Date publicationStartDate;
	private Date publicationEndDate;
}
