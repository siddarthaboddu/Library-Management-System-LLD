package com.siddarthaboddu.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.BookAction;
import com.siddarthaboddu.entity.BookItem;
import com.siddarthaboddu.entity.MemberCard;
import com.siddarthaboddu.entity.User;

@Repository
public interface BookActionRepository extends PagingAndSortingRepository<BookAction, Long>{

	Integer countByMemberCardAndInUse(MemberCard memberCard, boolean inUse);

	Optional<BookAction> findByBookItemAndMemberCard(BookItem bookItem, MemberCard memberCard);

}
