package com.royaltechnosoft.inquiry.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.UserService;

public class ForgotPasswordAction extends ControllerSupport {
	@Autowired private UserService userService;
	private String email;
	
	public String execute() {
		// Send a password reset link to the email if registered, if not, 
		if(userService.forgotPassword(email)){
			addActionMessage("A password reset link has been sent to your registered email.");
			return SUCCESS;
		}
		else{
			addActionError("This email is not registered with us.");
			return INPUT;
		}
	}
	
	
	// Getters and setters
	public String getEmail() {
		return email;
	}
	
	@RequiredFieldValidator(key="feildErrors.required")
	@EmailValidator(key="feildErrors.email")
	public void setEmail(String email) {
		this.email = email;
	}
	
}
