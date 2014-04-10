<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<s:property value="message"/>
<s:if test="hasActionMessages()">
   <s:actionmessage/>
</s:if>
<s:form action="AddCourse">
<s:textfield key="courseName" label="Name"/><s:submit/>
</s:form>

<s:iterator value="courses" var="course">
<s:property value="#course.name"/>
<a href="RemoveCourse?courseId=<s:property value="#course.courseId"/>">Remove <s:property value="#course.courseId"/></a>
</s:iterator>

<s:debug></s:debug>

</body>
</html>