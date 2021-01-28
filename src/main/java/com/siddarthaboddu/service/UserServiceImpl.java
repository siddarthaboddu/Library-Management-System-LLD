package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.dto.UserCreateRequestDto;
import com.siddarthaboddu.entity.MemberCard;
import com.siddarthaboddu.entity.Role;
import com.siddarthaboddu.entity.User;
import com.siddarthaboddu.exception.RoleNotFoundException;
import com.siddarthaboddu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MemberCardService memberCardService;
	
	@Override
	public boolean isLibrarian(Long requestUserId) {
		Optional<User> requestUserOptional = this.findUserById(requestUserId);
		
		return requestUserOptional.isPresent() && requestUserOptional.get().isLibrarian();
	}
	
	@Override
	public Optional<User> findUserById(Long requestUserId) {
		return userRepository.findById(requestUserId);
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public Optional<User> createUserFromUserCreateRequestDto(UserCreateRequestDto userCreateRequest) {
		
		User user = new User();
		user.setFirstName(userCreateRequest.getFirstName());
		user.setLastName(userCreateRequest.getLastName());
		user.setMobileNumber(userCreateRequest.getMobileNumber());
		user.setEmail(userCreateRequest.getEmail());
		user.setNationalId(userCreateRequest.getNationalId());
		
		Optional<Role> roleOptional = roleService.getById(userCreateRequest.getRoleId());
		
		if(roleOptional.isEmpty()) {
			throw new RoleNotFoundException("roleId: "+userCreateRequest.getRoleId());
		}
		
		user.setRole(roleOptional.get());
		
		user = userRepository.save(user);
		MemberCard memberCard = memberCardService.createMemberCard(user);
		user.setMemberCard(memberCard);
		
		user = userRepository.save(user);
		
		return Optional.ofNullable(user);
	}



}
