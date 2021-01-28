package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.entity.Role;

public interface RoleService {

	Optional<List<Role>> getAll();

	Optional<Role> create(Role role);

	Optional<Role> getById(Long id);

}
