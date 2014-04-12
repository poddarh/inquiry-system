package com.royaltechnosoft.inquiry.controller.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;

public class LoginAction extends ControllerSupport implements SessionAware {
	@Autowired private UserService userService;
	private String email;
	private String password;
	private Map<String, Object> session;
	
	public String execute() {
		User user = userService.authenticate(email,password);
		if(user==null){
			addActionError("Incorrect email or password!");
			return INPUT;
		}else{
			session.put("user", user);
			return SUCCESS;
		}
	}

	public String getEmail() {
		return email;
	}

	@RequiredFieldValidator(key = "fieldErrors.required")
	@EmailValidator(key = "fieldErrors.email")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	@RequiredStringValidator(key = "fieldErrors.requiredString")
	public void setPassword(String password) {
		this.password = password;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
