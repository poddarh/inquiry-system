package com.royaltechnosoft.inquiry.controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class ControllerSupport extends ActionSupport {
	public String getActionProperty(String property) {
		String propertyPackage = this.getClass().getName().replaceAll("com.royaltechnosoft.inquiry.controller", "controller");
		return getText(propertyPackage+"."+property);
	}
	
}
