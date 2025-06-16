package com.college.yi.ecsite.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "user_id") 
	private Integer id;
	private String email;
	private String password;
	
	@Transient
	private String confirmPassword;
	private String lastName;
	private String firstName;
	private String lastNameKana;
	private String firstNameKana;
	private LocalDateTime createdAt;
}