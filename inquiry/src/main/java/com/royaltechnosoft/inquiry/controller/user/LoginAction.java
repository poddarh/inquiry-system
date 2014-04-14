package com.royaltechnosoft.inquiry.controller.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;

public class LoginAction extends ControllerSupport implements SessionAware,ModelDriven<User> {
	@Autowired private UserService userService;
	private User user;
	private Map<String, Object> session;
	
	public String execute() {
//		if(user!=null)
			user = userService.authenticate(user.getEmail(),user.getPassword());
		if(user==null){
			addActionError("Incorrect email or password!");
			return INPUT;
		}else{
			session.put("user", user);
			return SUCCESS;
		}
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	@VisitorFieldValidator(appendPrefix=false)
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getModel() {
		return user = new User();
	}
}
