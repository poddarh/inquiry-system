<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../template/header.jsp">
	<s:param name="title" value="#inquiry.studentName"/>
</s:include>



<script>
  $(function() {
    $( "#nextScheduledDate" ).datepicker({
      defaultDate: 1,
	  minDate:0,
      numberOfMonths: 1,
    });
  });
  
  function closeInquiry(){
	  if(confirm("Are you sure you want to close this inquiry?"))
	  	window.open("CloseInquiry.action?inquiryID=<s:property value='inquiry.inquiryID'/>","_self");
  }
</script>

<style>
.formLabel{
	text-align: right;
	font-weight: bold;
}
</style>

<span style="font-size:24px;font-weight:bold"><s:property value="inquiry.studentName"/> - <s:property value="inquiry.course.name"/></span>
<s:if test="%{inquiry.status!='c'}">
	<button onclick="closeInquiry();" style="float:right;margin-right: 20px">Close Inquiry</button>
</s:if>
<br />
<h3>Personal Info</h3>
<table>
    <tr>
        <td style="text-align:right">Name:</td>
        <td><s:property value="inquiry.studentName"/></td>
        <td style="text-align:right">Date:</td>
        <td><s:date name="inquiry.dateCreated" format="MMMM d, yyyy" /></td>
    </tr>
    <tr>
        <td style="text-align:right">Mobile:</td>
        <td><s:property value="inquiry.studentMobile"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Telephone:</td>
        <td><s:property value="inquiry.studentTelephone"/>0</td>
    </tr>
    <tr valign="top">
        <td style="text-align:right">Address:</td>
        <td><s:property value="inquiry.studentAddress"/></td>
    </tr>
    <tr valign="top">
        <td style="text-align:right">Email:</td>
        <td><s:property value="inquiry.studentEmail"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Institution Name:</td>
        <td width="300px"><s:property value="inquiry.institutionName"/></td>
    </tr>
    <tr>
        <td style="text-align:right">Grade/Semester:</td>
        <td><s:property value="inquiry.studentLevel"/></td>
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
        <td><s:property value="inquiry.preferedTiming"/></td>
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
<h4>Add Followup Details:</h4>
<s:form theme="simple" action="AddFollowup">
<s:hidden name="inquiryID" value='%{inquiry.inquiryID}' />
<s:hidden name="inquiryStatus" value='%{inquiry.status}' />
<label for="remark">Remark: </label><s:textfield name="remark" size="50" />
<label for="nextScheduledDate">Call again on: </label><s:textfield name="nextScheduledDate" id="nextScheduledDate" size="10" />
<s:submit value="Add" />
</s:form>
<s:if test="%{inquiry.followups!=null}">
	<table border="0" cellspacing="0" cellpadding="5" class="table">
	  <tr class="tableHeading">
	    <th scope="col">Date &amp; Time</th>
	    <th scope="col">Remark</th>
	    <th scope="col">Scheduled Call</th>
	  </tr>
	  <s:iterator value="inquiry.followups" var="followup">
		  <tr>
		    <td><s:date name="#followup.time" format="MMM dd, yyyy h:mm a" /></td>
		    <td><s:property value="#followup.remark"/></td>
		    <td><s:date name="#followup.nextScheduledDate" format="MMM dd, yyyy" /></td>
		  </tr>
	  </s:iterator>
	</table>
</s:if>



<s:include value="../template/footer.jsp" />