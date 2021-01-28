package com.siddarthaboddu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siddarthaboddu.constant.RoleType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@ToString(exclude = {"role", "memberCard"})
public class User extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String mobileNumber;
	
	@Column(unique = true)
	private String NationalId;
	
	@Column(unique = true)
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Role role;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "member_card_id", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private MemberCard memberCard;
	
	@JsonIgnore
	public boolean isLibrarian() {
		if(role == null) return false;
		if(role.getRoleType().equals(RoleType.LIBRARIAN)) return true;
		return false;
	}
}
