<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="populate" uri="/populate"%>
<s:include value="../template/header.jsp">
	<s:param name="title" value="#inquiry.studentName"/>
</s:include>

<s:property value="message"/>
<populate:studentLevels />
<populate:courses />
<s:form action="AddInquiry">
<s:textfield key="studentName" label="Name"/>
<s:textfield key="studentMobile" label="Mobile"/>
<s:textfield key="studentTelephone" label="Tel. No."/>
<s:textfield key="studentAddress" label="Address"/>
<s:textfield key="studentEmail" label="Email"/>
<s:textfield key="institutionName" label="School/College"/>
<s:select list="#attr.studentLevels" key="studentLevel" label="Grade/Semester"></s:select>
<s:select list="#attr.courses" listKey="courseId" listValue="name" key="courseID" label="Course"></s:select>
<s:textfield key="subjects" label="Subjects"/>
<s:textfield key="preferredTiming" label="Preferable Timings"/>
<s:textfield key="referredBy" label="Referred By"/>
<s:textfield key="inquiryHandledBy" label="Inquiry Heandled by"/>
<s:submit/>
</s:form>


<s:include value="../template/footer.jsp" />