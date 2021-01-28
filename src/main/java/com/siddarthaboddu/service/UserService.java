package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.dto.UserCreateRequestDto;
import com.siddarthaboddu.entity.User;

public interface UserService {

	boolean isLibrarian(Long requestUserId);
	
	Optional<User> findUserById(Long requestUserId);

	Optional<List<User>> getAllUsers();

	Optional<User> createUserFromUserCreateRequestDto(UserCreateRequestDto userCreateRequest);

}
