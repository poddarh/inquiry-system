package com.royaltechnosoft.inquiry.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "passwordResetRequests")
public class PasswordResetRequest implements Model {

	@Id
	@GeneratedValue
	private Integer requestId;
	@Column(nullable = false, length = 60)
	private String email;
	@Column(nullable = false)
	private Date timeCreated;
	@Column(nullable = false, length = 32)
	private String token;
	
	
	// Getters and setters
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getEmail() {
		return email;
	}
	@RequiredFieldValidator(key="fieldErrors.requiredString")
	@EmailValidator(key="fieldErrors.email")
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getToken() {
		return token;
	}
	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
