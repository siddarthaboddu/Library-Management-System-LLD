package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.entity.Role;
import com.siddarthaboddu.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<List<Role>> getAll() {
		return Optional.of(roleRepository.findAll());
	}

	@Override
	public Optional<Role> create(Role role) {
		return Optional.of(roleRepository.save(role));
	}

	@Override
	public Optional<Role> getById(Long id) {
		return roleRepository.findById(id);
	}

}
