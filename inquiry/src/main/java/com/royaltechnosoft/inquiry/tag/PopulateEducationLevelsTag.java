package com.royaltechnosoft.inquiry.tag;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PopulateEducationLevelsTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		Map<String,String> educationLevels = new TreeMap<String, String>();
		for (int i = 1; i <= 12; i++) 
			educationLevels.put("g"+String.format("%02d",i),"Grade " + i);
		for (int i = 1; i <= 10; i++) 
			educationLevels.put("s"+String.format("%02d",i),"Semester " + i);
		getJspContext().setAttribute("educationLevels", educationLevels);
	}
	
}
