package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.entity.MemberCard;
import com.siddarthaboddu.entity.User;

public interface MemberCardService {

	Optional<List<MemberCard>> getAll();

	Optional<MemberCard> getById(Long id);

	MemberCard createMemberCard(User user);

}
