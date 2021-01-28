package com.siddarthaboddu.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>{
	public List<Role> findAll();
}
