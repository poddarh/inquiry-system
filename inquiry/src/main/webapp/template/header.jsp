<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.title}</title>
<link href="/inquiry/template/style/layout.css" rel="stylesheet"
	type="text/css" />
<link href="/inquiry/template/style/table.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<style type="text/css">
</style>
</head>

<body>

	<div class="container">
		<div class="header">
			<a href="Index"><img src="/inquiry/template/royal-logo.png"
				alt="Insert Logo Here" name="Insert_logo" width="200"
				id="Insert_logo" style="background-color: #C6D580; display: block;" /></a>
			<!-- end .header -->
		</div>
		<table border="0" cellspacing="0" cellpadding="0"
			style="margin: auto;">
			<tr>
				<s:if test="%{#session.user!=null}">
					<td class="sidebar1" valign="top">
						<ul class="nav">
							<s:if test="%{#session.user.role=='officer'}">
								<li><a href="/inquiry/officer/ListScheduled.action">Scheduled
										Followups</a></li>
								<li><a href="#">Inquiry</a></li>
								<li><a href="/inquiry/officer/add-inquiry.jsp">-&gt;Create
										New</a></li>
								<li><a href="/inquiry/officer/ListFreshInquiries.action">-&gt;List
										Fresh</a></li>
								<li><a href="/inquiry/officer/SearchInquiries.action">-&gt;Search</a></li>
								<li><a href="/inquiry/officer/manage-courses.jsp">Manage
										Courses</a></li>
								<li><a href="/inquiry/officer/account.jsp">Account</a></li>
							</s:if>
							<s:if test="%{#session.user.role=='typist'}">
								<li><a href="/inquiry/typist/add-inquiry.jsp">Create
										New</a></li>
								<li><a href="/inquiry/typist/account.jsp">Account</a></li>
							</s:if>
							<li><a href="/inquiry/Logout.action">Logout</a></li>
						</ul>
					</td>
				</s:if>
				<s:else>
					<style>
.content {
	width: auto;
	display: table;
	margin: 0 auto;
}
</style>
				</s:else>
				<td class="content" valign="top">