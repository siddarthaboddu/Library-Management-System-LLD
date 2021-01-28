package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.entity.MemberCard;
import com.siddarthaboddu.entity.User;
import com.siddarthaboddu.repository.MemberCardRepository;

@Service
public class MemberCardServiceImpl implements MemberCardService {

	@Autowired
	private MemberCardRepository memberCardRepository;
	
	@Override
	public Optional<List<MemberCard>> getAll() {
		return Optional.of(memberCardRepository.findAll());
	}

	@Override
	public Optional<MemberCard> getById(Long id) {
		return memberCardRepository.findById(id);
	}

	@Override
	public MemberCard createMemberCard(User user) {
		MemberCard memberCard = new MemberCard();
		memberCard.setUser(user);
		memberCard = memberCardRepository.save(memberCard);
		return memberCard;
	}
	
	

}
