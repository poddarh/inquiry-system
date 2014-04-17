<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="populate" uri="/populate" %>
<s:include value="/template/header.jsp">
	<s:param name="title">Manage Courses</s:param>
</s:include>

<script>
  function removeCourse(courseId){
	  if(confirm("Are you sure you want to delete this course?\nNOTE: Deleting this course will also delete inquiries related to this course!"))
	  	window.open("RemoveCourse?courseId="+courseId,"_self");
  }
</script>

<populate:courses/>
<h3>Manage Courses</h3>
<s:actionmessage/>
<s:actionerror/>
<s:form action="AddCourse" theme="simple">
  <table border="0" cellspacing="0" cellpadding="5" class="table">
  <tr class="tableHeading">
    <th><s:textfield key="courseName"/></th>
    <th><s:submit value="Add"/></th>
  </tr>
  <s:iterator value="#attr.courses" var="course">
	<tr>
	  <td><s:property value="#course.name"/></td>
	  <td><a href="#" onclick="removeCourse(<s:property value='#course.courseId'/>)">Remove</a></td>
	</tr>
	
  </s:iterator>
</table>
</s:form>




<s:include value="/template/footer.jsp" />