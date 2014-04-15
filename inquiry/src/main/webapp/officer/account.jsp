<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="populate" uri="/populate"%>
<s:include value="../template/header.jsp">
	<s:param name="title" value="#inquiry.studentName"/>
</s:include>

<s:actionmessage/>
<s:form action="UpdateUser">
	<s:textfield key="name" label="Name" value="%{#session.user.name}"/>
	<s:textfield key="email" label="Email" value="%{#session.user.email}"/>
	<s:password key="password" label="Password"/>
	<s:password key="confirmPassword" label="Confirm Password"/>
	<s:submit/>
</s:form>


<s:include value="../template/footer.jsp" />