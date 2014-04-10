<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../template/header.jsp">
	<s:param name="title">Homepage</s:param>
</s:include>

<s:property value="message" />
<s:if test="hasActionMessages()">
	<s:actionmessage />
</s:if>

<h3>Following up required</h3>
<s:if test="#totalPages>0">
	<s:iterator begin="1" end="#totalPages" status="status">
		<s:if test="#status.index==#page">
			<s:property value="#status.index" />
		</s:if>
		<s:else>
			<s:a href='ListFollowups?page=<s:property value="#status.index"/>'>
				<s:property value="#status.index" />
			</s:a>
		</s:else>
	</s:iterator>

<table border="0" cellspacing="0" cellpadding="8" width="97%" class="table">
  <tr class="tableHeading">
    <th scope="col">Student's Name</th>
    <th scope="col">Course</th>
    <th scope="col">Inquiry Date</th>
    <th scope="col">Scheduled Followup</th>
  </tr>
<s:iterator value="followups" var="followup">
	<s:property value="#followup.time" /> | <s:property
		value="#followup.remark" /> | <s:property
		value="#followup.nextScheduledDate" />

  <!-- <tr class="tableRow">
    <td>Harsh Poddar</td>
    <td>OCJP</td>
    <td>21 Feb'14</td>
    <td>Today</td>
  </tr> -->

</s:iterator>
</table>
</s:if>
<s:else>
No following up required!
</s:else>

<s:include value="../template/footer.jsp" />