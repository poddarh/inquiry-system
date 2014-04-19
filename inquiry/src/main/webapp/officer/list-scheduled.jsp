<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="/template/header.jsp">
	<s:param name="title">List Follow-ups</s:param>
</s:include>


<script type="text/javascript">
function openInquiry(inquiryId){
	window.open("ViewInquiry.action?inquiryId="+inquiryId,"_self");
}
</script>

<s:if test="hasActionMessages()">
	<s:actionmessage />
</s:if>

<h3 style="padding-left: 0">Following up required</h3>
<s:if test="totalPages>0">
	<div class="pageNo">
		Page <s:iterator begin="1" end="totalPages" status="status">
			<s:if test="%{#status.count==page}">
				<s:property value="#status.count" />
			</s:if>
			<s:else>
				<s:a href='ListScheduled?page=%{#status.count}'>
					<s:property value="#status.count" />
				</s:a>
			</s:else>
		</s:iterator>
	</div>

<table border="0" cellspacing="0" cellpadding="8" width="97%" class="table">
  <tr class="tableHeading">
    <th scope="col">Student's Name</th>
    <th scope="col">Course</th>
    <th scope="col">Inquiry Date</th>
    <th scope="col">Follow up scheduled on</th>
  </tr>
<s:iterator value="inquiries" var="inquiry">

  <tr class="tableRow" onclick='openInquiry(<s:property value="#inquiry.inquiryId" />);'>
    <td><s:property value="#inquiry.student.name" /></td>
    <td><s:property value="#inquiry.course.name" /></td>
    <td><s:date name="#inquiry.dateCreated" format="MMM dd, yyyy" /></td>
    <td><s:date name="#inquiry.scheduledFollowupDate" format="MMM dd, yyyy" /></td>
  </tr>

</s:iterator>
</table>
</s:if>
<s:else>
No following up required!
</s:else>

<s:include value="/template/footer.jsp" />