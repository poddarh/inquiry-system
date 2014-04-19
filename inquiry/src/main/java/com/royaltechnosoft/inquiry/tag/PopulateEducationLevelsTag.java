package com.royaltechnosoft.inquiry.tag;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PopulateEducationLevelsTag extends SimpleTagSupport {

	// Add education levels map to the JSP context to be used by select tags
	public void doTag() throws JspException, IOException {
		Map<String, String> educationLevels = new TreeMap<String, String>();

		// Put Grades 1 to 12 in the map with the key as gXX.
		for (int i = 1; i <= 12; i++)
			educationLevels.put("g" + String.format("%02d", i), "Grade " + i);

		// Put Semesters 1 to 10 in the map with the key as sXX.
		for (int i = 1; i <= 10; i++)
			educationLevels.put("s" + String.format("%02d", i), "Semester " + i);

		// Set education levels attribute to enable it to be used from the page
		getJspContext().setAttribute("educationLevels", educationLevels);
	}

}
