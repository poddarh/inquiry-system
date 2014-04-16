<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:include value="/template/header.jsp">
	<s:param name="title">Login</s:param>
</s:include>

<h3>Login</h3>
<s:actionerror/>
<s:actionmessage/>
<s:form action="Login">
<s:textfield key="email" label="Email" />
<s:password key="password" label="Password" />
<s:submit/>
</s:form>
<s:a href="forgot-password.jsp">Forgot Password?</s:a>
<s:include value="/template/footer.jsp"/>