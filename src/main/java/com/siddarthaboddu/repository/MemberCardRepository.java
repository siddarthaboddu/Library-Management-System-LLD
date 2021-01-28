package com.siddarthaboddu.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.MemberCard;

@Repository
public interface MemberCardRepository extends PagingAndSortingRepository<MemberCard, Long>{

	public List<MemberCard> findAll();
}
