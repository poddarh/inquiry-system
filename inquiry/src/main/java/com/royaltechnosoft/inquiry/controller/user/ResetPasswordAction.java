package com.royaltechnosoft.inquiry.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.PasswordRequest;
import com.royaltechnosoft.inquiry.service.UserService;

public class ResetPasswordAction extends ControllerSupport implements ModelDriven<PasswordRequest> {
	@Autowired private UserService userService;
	private PasswordRequest passwordRequest;
	
	public String execute() {
		if(userService.resetPassword(passwordRequest)){
			addActionMessage("Your new password has been sent to your registered email.");
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	public PasswordRequest getModel() {
		return passwordRequest = new PasswordRequest();
	}

	public PasswordRequest getPasswordRequest() {
		return passwordRequest;
	}
	
	@VisitorFieldValidator(appendPrefix=false)
	public void setPasswordRequest(PasswordRequest passwordRequest) {
		this.passwordRequest = passwordRequest;
	}
	
}
