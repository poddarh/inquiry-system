<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="/template/header.jsp">
	<s:param name="title" value="View Inquiry"/>
</s:include>



<script>
  $(function() {
    $( "#scheduledFollowupDate" ).datepicker({
      defaultDate: 1,
	  minDate:0,
      numberOfMonths: 1,
    });
  });
  
  function closeInquiry(){
	  if(confirm("Are you sure you want to close this inquiry?"))
	  	window.open("CloseInquiry.action?inquiryId=<s:property value='inquiry.inquiryId'/>","_self");
  }
</script>

<style>
.formLabel{
	text-align: right;
	font-weight: bold;
}
</style>

<span style="font-size:24px;font-weight:bold"><s:property value="inquiry.student.name"/> - <s:property value="inquiry.course.name"/></span>
<s:if test="%{inquiry.status!='c'}">
	<button onclick="closeInquiry();" style="float:right;margin-right: 20px">Close Inquiry</button>
</s:if>
<br />
<h3>Personal Info</h3>
<table>
    <tr>
        <td style="text-align:right">Name:</td>
        <td><s:property value="inquiry.student.name"/></td>
        <td style="text-align:right">Date:</td>
        <td><s:date name="inquiry.dateCreated" format="MMMM d, yyyy" /></td>
    </tr>
    <tr>
        <td style="text-align:right">Mobile:</td>
        <td><s:property value="inquiry.student.mobile"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Telephone:</td>
        <td><s:property value="inquiry.student.telephone"/></td>
    </tr>
    <tr valign="top">
        <td style="text-align:right">Address:</td>
        <td><s:property value="inquiry.student.address"/></td>
    </tr>
    <tr valign="top">
        <td style="text-align:right">Email:</td>
        <td><s:property value="inquiry.student.email"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Institution Name:</td>
        <td width="300px"><s:property value="inquiry.student.institutionName"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Education Level:</td>
        <td><s:property value="inquiry.student.educationLevelString"/></td>
    </tr>
</table>

<h3>Course Info</h3>
<table>
    <tr>
        <td style="text-align:right">Course:</td>
        <td><s:property value="inquiry.course.name"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Subjects:</td>
        <td><s:property value="inquiry.subjects"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Preferred Timings:</td>
        <td><s:property value="inquiry.preferredTiming"/></td>
    </tr>
    <tr>
    	<td colspan="2">&nbsp;</td>
    </tr>
	<s:if test="%{inquiry.status=='o'}">
	    <tr>
	        <td style="text-align:right">Next follow-up scheduled on:</td>
	        <td><s:date name="inquiry.scheduledFollowupDate" format="MMMM dd, yyyy" /></td>
	    </tr>
    </s:if>
    <tr>
        <td style="text-align:right">Inquiry Status:</td>
        <td><s:property value="inquiry.statusString"/></td>
    </tr>
    <tr>
        <td style="text-align:right">References:</td>
        <td><s:property value="inquiry.referredBy"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Inquiry Handled By:</td>
        <td><s:property value="inquiry.inquiryHandledBy"/></td>
    </tr>
    
</table>
<br/>
<br/>
<h4>Followup Details:</h4>
<s:if test="%{inquiry.status!='c'}">
	<s:form theme="simple" action="AddFollowup">
	<s:hidden name="inquiryId" value='%{inquiry.inquiryId}' />
	<s:hidden name="inquiryStatus" value='%{inquiry.status}' />
	<label for="remark">Remark: </label><s:textfield name="remark" size="50" />
	<label for="scheduledFollowupDate">Call again on: </label><s:textfield name="scheduledFollowupDate" id="scheduledFollowupDate" size="10" />
	<s:submit value="Add" />
	</s:form>
</s:if>
<br/>
<s:if test="%{!inquiry.followups.isEmpty()}">
<table border="0" cellspacing="0" cellpadding="5" class="table">
  <tr class="tableHeading">
    <th scope="col">Date &amp; Time</th>
    <th scope="col">Remark</th>
  </tr>
  <s:iterator value="inquiry.followups" var="followup">
	  <tr>
	    <td><s:date name="#followup.time" format="MMM dd, yyyy h:mm a" /></td>
	    <td><s:property value="#followup.remark"/></td>
	  </tr>
  </s:iterator>
</table>
</s:if>



<s:include value="/template/footer.jsp" />