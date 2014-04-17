package com.royaltechnosoft.inquiry.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.royaltechnosoft.inquiry.service.CourseService;

public class PopulateCoursesTag extends SimpleTagSupport {

	// Add courses list to the JSP context to be used by select tags
	public void doTag() throws JspException, IOException {
		// Get the injected courseService bean from the spring's application
		// context
		PageContext pageContext = (PageContext) getJspContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(pageContext.getServletContext());
		CourseService courseService = (CourseService) ctx
				.getBean("courseService");
		
		// Set courses attribute to enable it to be used from the page
		getJspContext().setAttribute("courses", courseService.getCourses());
	}

}
