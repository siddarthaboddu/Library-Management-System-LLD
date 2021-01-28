package com.siddarthaboddu.dto;

import lombok.Data;

@Data
public class UserCreateRequestDto {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String NationalId;
	private String email;
	
	private Long roleId;
	
}
