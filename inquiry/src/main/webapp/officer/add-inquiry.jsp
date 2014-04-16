<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="populate" uri="/populate"%>
<s:include value="/template/header.jsp">
	<s:param name="title" value="Add Inquiry"/>
</s:include>

<s:actionerror/>
<s:actionmessage/>
<populate:educationLevels />
<populate:courses />
<s:form action="AddInquiry">
<s:textfield key="student.name" label="Name"/>
<s:textfield key="student.mobile" label="Mobile"/>
<s:textfield key="student.telephone" label="Tel. No."/>
<s:textfield key="student.address" label="Address"/>
<s:textfield key="student.email" label="Email"/>
<s:textfield key="student.institutionName" label="School/College"/>
<s:select list="#attr.educationLevels" key="student.educationLevel" label="Grade/Semester"></s:select>
<s:select list="#attr.courses" listKey="courseId" listValue="name" key="courseId" label="Course"></s:select>
<s:textfield key="inquiry.subjects" label="Subjects"/>
<s:textfield key="inquiry.preferredTiming" label="Preferable Timings"/>
<s:textfield key="inquiry.referredBy" label="Referred By"/>
<s:textfield key="inquiry.inquiryHandledBy" label="Inquiry Heandled by"/>
<s:submit/>
</s:form>

<s:include value="/template/footer.jsp" />