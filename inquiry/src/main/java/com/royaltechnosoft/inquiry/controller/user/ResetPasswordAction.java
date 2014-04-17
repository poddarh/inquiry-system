package com.royaltechnosoft.inquiry.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.PasswordResetRequest;
import com.royaltechnosoft.inquiry.service.UserService;

public class ResetPasswordAction extends ControllerSupport implements ModelDriven<PasswordResetRequest> {
	@Autowired private UserService userService;
	private PasswordResetRequest passwordRequest;
	
	public String execute() {
		// Checks if the request is valid and resets and emails the password if valid, otherwise, returns false
		if(userService.resetPassword(passwordRequest)){
			addActionMessage("Your new password has been sent to your registered email.");
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	// Getters and setters
	public PasswordResetRequest getModel() {
		return passwordRequest = new PasswordResetRequest();
	}

	public PasswordResetRequest getPasswordResetRequest() {
		return passwordRequest;
	}
	
	@VisitorFieldValidator(appendPrefix=false)
	public void setPasswordResetRequest(PasswordResetRequest passwordRequest) {
		this.passwordRequest = passwordRequest;
	}
	
}
