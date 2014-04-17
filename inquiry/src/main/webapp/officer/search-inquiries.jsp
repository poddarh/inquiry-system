<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="populate" uri="/populate"%>
<s:include value="/template/header.jsp">
	<s:param name="title" value="Search Inquiries"/>
</s:include>

<script>
  $(function() {
    $( "#searchInquiryForm_newerThan" ).datepicker({
      defaultDate: "-1w",
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
     	var aDayAfter = $('#searchInquiryForm_newerThan').datepicker('getDate');
     	aDayAfter.setDate(aDayAfter.getDate()+1);
        $( "#searchInquiryForm_olderThan" ).datepicker( "option", "minDate", aDayAfter );
		$( "#searchInquiryForm_olderThan" ).focus();
      }
    });
    $( "#searchInquiryForm_olderThan" ).datepicker({
      defaultDate: "+1d",
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
    	var aDayBefore = $('#searchInquiryForm_olderThan').datepicker('getDate');
    	aDayBefore.setDate(aDayBefore.getDate()-1);
        $( "#searchInquiryForm_newerThan" ).datepicker( "option", "maxDate", aDayBefore );
      }
    });
  });
  function openInquiry(inquiryId){
		window.open("GetInquiry.action?inquiryId="+inquiryId,"_self");
  }
  function openPage(page){
	  $("#openPageForm_page").val(page);
	  $("#openPageForm").submit();
	  
  }
</script>

<style>
.formLabel{
	text-align: right;
	font-weight: bold;
}
</style>

<s:actionmessage/>
<populate:courses />

<h3>Find an Inquiry</h3>
<s:form action="SearchInquiries" theme="simple" cssStyle="display:none;" id="openPageForm">
<s:hidden name="page"/>
<s:hidden name="name"/>
<s:hidden name="newerThan"/>
<s:hidden name="olderThan"/>
<s:hidden name="courseId"/>
<s:hidden name="status"/>
</s:form>
<s:form action="SearchInquiries" theme="simple" id="searchInquiryForm" >
    <table border="0" cellspacing="0" cellpadding="6">
      <tr>
        <td class="formLabel">Name</td>
        <td><s:textfield name="name"/></td>
      </tr>
      <tr>
        <td class="formLabel">Newer than</td>
        <td><s:textfield name="newerThan" /></td>
      </tr>
      <tr>
        <td class="formLabel">Older than</td>
        <td><s:textfield name="olderThan"/></td>
      </tr>
      <tr>
        <td class="formLabel">Course</td>
        <td><s:select list="#attr.courses" listKey="courseId" listValue="name" name="courseId" emptyOption="true" /></td>
      </tr>
      <tr>
        <td class="formLabel">Status</td>
        <td><s:select list="#{'f':'Fresh','fo':'Open','c':'Closed'}" name="status" emptyOption="true" /></td>
      </tr>
      <tr>
        <td class="formLabel">&nbsp;</td>
        <td><s:submit value="Search"/>
          <s:reset/></td>
      </tr>
	</table>
</s:form>

<s:if test="totalPages>0">
	<div class="pageNo">
		Page <s:iterator begin="1" end="totalPages" status="status">
			<s:if test="%{#status.count==page}">
				<s:property value="#status.count" />
			</s:if>
			<s:else>
				<s:a href="#" onclick="openPage(%{#status.count})">
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
    <th scope="col">Status</th>
  </tr>
  <s:iterator value="inquiries" var="inquiry">
	  <tr class="tableRow" onclick="openInquiry(<s:property value='#inquiry.inquiryId'/>)">
	    <td><s:property value="#inquiry.student.name"/></td>
	    <td><s:property value="#inquiry.course.name"/></td>
	    <td><s:date name="#inquiry.dateCreated" format="MMM dd, yyyy"/></td>
	    <td><s:property value="#inquiry.statusString"/></td>
	  </tr>
  </s:iterator>
</table>
</s:if>
<s:else>
No results found!
</s:else>



<s:include value="/template/footer.jsp" />