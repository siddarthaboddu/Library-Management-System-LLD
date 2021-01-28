package com.siddarthaboddu.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	List<User> findAll();
	
}
