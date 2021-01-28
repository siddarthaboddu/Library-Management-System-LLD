package com.siddarthaboddu.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{

	public List<Category> findAll();
}
