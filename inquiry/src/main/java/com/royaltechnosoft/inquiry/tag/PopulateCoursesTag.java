package com.royaltechnosoft.inquiry.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.royaltechnosoft.inquiry.service.CourseService;

public class PopulateCoursesTag extends SimpleTagSupport {
	
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext(); 
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext()); 
		CourseService courseService = (CourseService) ctx.getBean("courseService");
		getJspContext().setAttribute("courses", courseService.getCourses());
	}
	
}
