<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:include value="/template/header.jsp">
	<s:param name="title">Error Page</s:param>
</s:include>

<h3>Error</h3>

<s:set name="ex" value="%{exception}" scope="page"/>
<%
Exception exMsg = (Exception)pageContext.getAttribute("ex");
if(exMsg!=null)
	exMsg.printStackTrace(new PrintWriter(out));
%>
There was an error. Please try again or contact system administrator.
<s:include value="/template/footer.jsp"/>
