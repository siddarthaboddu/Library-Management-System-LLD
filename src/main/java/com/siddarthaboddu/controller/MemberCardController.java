package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.entity.MemberCard;
import com.siddarthaboddu.service.MemberCardService;

@RestController
public class MemberCardController {

	@Autowired
	private MemberCardService memberCardService;
	
	@GetMapping("/memberCards")
	public ResponseEntity<List<MemberCard>> getAll(){
		Optional<List<MemberCard>> memberCardOptional = memberCardService.getAll();
		
		if(memberCardOptional.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(memberCardOptional.get());
	}
	
	@GetMapping("/memberCards/{id}")
	public ResponseEntity<MemberCard> getById(@RequestParam("id") Long id){
		Optional<MemberCard> memberCardOptional = memberCardService.getById(id);
		
		if(memberCardOptional.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(memberCardOptional.get());
	}
}
