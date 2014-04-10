package com.royaltechnosoft.inquiry.controller;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public abstract class ControllerSupport extends ActionSupport implements Preparable {
	public String getActionProperty(String property) {
		String propertyPackage = this.getClass().getName().replaceAll("com.royaltechnosoft.inquiry.controller", "controller");
		return getText(propertyPackage+"."+property);
	}
	
	public void prepare() throws Exception {
	}
	
	@SkipValidation
	public String make() {
		return INPUT;
	}
	
}
