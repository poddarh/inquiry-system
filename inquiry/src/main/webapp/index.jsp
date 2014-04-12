<%@page import="java.io.PrintWriter"%>
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
<s:form action="Login">
<s:actionerror/>
<s:textfield key="username" label="Username" />
<s:password key="password" label="Password" />
<s:submit/>

<s:property value="%{exception.message}"/>
<br/>
<s:property value="%{exception.stackTrace}"/>

<s:set name="ex" value="%{exception}" scope="page"/>
<%
Exception exMsg = (Exception)pageContext.getAttribute("ex");
exMsg.printStackTrace(new PrintWriter(out));
%>

</s:form>
</body>
</html>