package com.royaltechnosoft.inquiry.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PopulateStudentLevelsTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		List<String> studentLevels = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) 
			studentLevels.add("Grade " + i);
		for (int i = 1; i <= 10; i++) 
			studentLevels.add("Semester " + i);
		getJspContext().setAttribute("studentLevels", studentLevels);
	}
	
}
