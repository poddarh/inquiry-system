package com.royaltechnosoft.inquiry.controller.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;

public class UpdateAction extends ControllerSupport implements SessionAware, ModelDriven<User> {
	@Autowired private UserService userService;
	private Map<String, Object> session;
	private User modifiedUser;
	private String confirmPassword;
	private String currentPassword;
	
	public void validate() {
		String password = null;
		if(modifiedUser!=null)
			password=modifiedUser.getPassword();
		if(confirmPassword!=null || password!=null){
			if(currentPassword!=null && currentPassword.trim().length()!=0){
				if(!currentPassword.equals(((User)session.get("user")).getPassword())){
					addFieldError("currentPassword", "Incorrect current password");
				}
			}else
				addFieldError("currentPassword", "Incorrect current password");
		}else if(currentPassword!=null){
			addFieldError("password", "Please enter new password");
		}
		if(confirmPassword!=null && password!=null){
			if(!confirmPassword.equals(password)){
				addFieldError("password", "Password and confirm password do not match");
			}
		}
		
	}
	
	public String execute() {
		User user = (User) session.get("user");
		if(user==null)
			return LOGIN;
		session.put("user",userService.updateUserDetails(user,modifiedUser));
		addActionMessage("Successfully updated.");
		return SUCCESS;
	}
	
	public User getModel() {
		return modifiedUser = new User();
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User getModifiedUser() {
		return modifiedUser;
	}
	
	@VisitorFieldValidator(appendPrefix=false)
	public void setModifiedUser(User modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
}
