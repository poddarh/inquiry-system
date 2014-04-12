package com.royaltechnosoft.inquiry.controller.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;

public class LogoutAction extends ControllerSupport implements SessionAware {
	private Map<String, Object> session;
	
	public String execute() {
		session.remove("user");
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
