package com.royaltechnosoft.inquiry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@Entity
@Table(name = "users")
public class User implements Model {

	@Id
	@GeneratedValue
	private Integer userId;
	@Column(nullable = false, length = 32)
	private String name;
	@Column(nullable = false, length = 45)
	private String email;
	@Column(nullable = false, length = 32)
	private String password;
	@Column(nullable = false, length = 8)
	private String role;

	// Getters and setters
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "32")
	@RegexFieldValidator(trim=true,regex="^[a-zA-Z ]*$",key="fieldErrors.lettersAndSpaces")
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
	@EmailValidator(key="fieldErrors.email")
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "45")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "45")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
