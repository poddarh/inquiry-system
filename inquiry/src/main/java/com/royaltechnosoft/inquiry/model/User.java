package com.royaltechnosoft.inquiry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;

@Entity
@Table(name = "users")
public class User implements Model {

	@Id
	@GeneratedValue
	private Integer userId;
	@Column(nullable = false, length = 45)
	private String name;
	@Column(nullable = false, length = 60)
	private String email;
	@Column(nullable = false, length = 45)
	private String password;
	@Column(nullable = false, length = 18)
	private String role;

	// Setters and getters
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
	@EmailValidator(key="fieldErrors.email")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "Error serializing the object!";
	}
}
