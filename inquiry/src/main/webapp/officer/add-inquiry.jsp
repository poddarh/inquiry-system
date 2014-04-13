<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="populate" uri="/populate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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
<s:select list="#attr.courses" listKey="courseId" listValue="name" key="course.courseId" label="Course"></s:select>
<s:textfield key="subjects" label="Subjects"/>
<s:textfield key="preferredTiming" label="Preferable Timings"/>
<s:textfield key="referredBy" label="Referred By"/>
<s:textfield key="inquiryHandledBy" label="Inquiry Heandled by"/>
<s:submit/>
</s:form>

<s:debug/>

</body>
</html>