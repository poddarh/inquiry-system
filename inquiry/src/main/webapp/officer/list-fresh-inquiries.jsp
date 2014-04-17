<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="populate" uri="/populate"%>
<s:include value="/template/header.jsp">
	<s:param name="title" value="Fresh Inquiries"/>
</s:include>

<script>
  function openInquiry(inquiryId){
		window.open("GetInquiry.action?inquiryId="+inquiryId,"_self");
  }
</script>

<s:actionmessage/>
<populate:courses />

<h3>Fresh Inquiries</h3>
<s:if test="totalPages>0">
	<div class="pageNo">
		Page <s:iterator begin="1" end="totalPages" status="status">
			<s:if test="%{#status.count==page}">
				<s:property value="#status.count" />
			</s:if>
			<s:else>
				<s:a href="ListFreshInquiries.action?page=%{#status.count}">
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
    <th scope="col">Handled by</th>
  </tr>
  <s:iterator value="inquiries" var="inquiry">
	  <tr class="tableRow" onclick="openInquiry(<s:property value='#inquiry.inquiryId'/>)">
	    <td><s:property value="#inquiry.student.name"/></td>
	    <td><s:property value="#inquiry.course.name"/></td>
	    <td><s:date name="#inquiry.dateCreated" format="MMM dd, yyyy"/></td>
	    <td><s:property value="#inquiry.inquiryHandledBy"/></td>
	  </tr>
  </s:iterator>
</table>
</s:if>
<s:else>
No results found!
</s:else>



<s:include value="/template/footer.jsp" />