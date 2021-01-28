package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.entity.Role;
import com.siddarthaboddu.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getAllRoles(){
		Optional<List<Role>> roleListOptional = roleService.getAll();
		
		if(roleListOptional.isEmpty() || roleListOptional.get().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(roleListOptional.get());
	}
	
	@PostMapping("/roles")
	public ResponseEntity<Role> addRole(@RequestBody Role role){
		Optional<Role> roleOptional = roleService.create(role);
		
		if(roleOptional.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(roleOptional.get());
	}
	
	@GetMapping("/roles/{id}")
	public ResponseEntity<Role> getRoleById(@RequestParam("id") Long id){
		Optional<Role> roleOptional = roleService.getById(id);
		
		if(roleOptional.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(roleOptional.get());
	} 
}
