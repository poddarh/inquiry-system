<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:include value="/template/header.jsp">
	<s:param name="title">Forgot Password</s:param>
</s:include>


<h3>Forgot Password</h3>
<s:actionerror/>
<s:actionmessage/>
<s:form action="ForgotPassword">
<s:textfield key="email" label="Email" />
<s:submit/>

</s:form>
<s:include value="/template/footer.jsp"/>