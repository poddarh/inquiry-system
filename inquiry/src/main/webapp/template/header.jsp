<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.title}</title>
<link href="/inquiry/template/style/layout.css" rel="stylesheet" type="text/css" />
<link href="/inquiry/template/style/table.css" rel="stylesheet" type="text/css" />
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
			<a href="#"><img src="" alt="Insert Logo Here" name="Insert_logo"
				width="200" height="90" id="Insert_logo"
				style="background-color: #C6D580; display: block;" /></a>
			<!-- end .header -->
		</div>
		<div class="sidebar1">
			<ul class="nav">
				<li><a href="ListFollowups.action">Home</a></li>
				<li><a href="#">Inquiry</a></li>
				<li><a href="SearchInquiries.action">&nbsp;-&gt;Search</a></li>
				<li><a href="add-inquiry.jsp">&nbsp;-&gt;Create New</a></li>
				<li><a href="manage-courses.jsp">Manage Courses</a></li>
				<li><a href="#">Account</a></li>
				<li><a href=".action">&nbsp;-&gt;Change Password</a></li>
				<li><a href=".action">&nbsp;-&gt;Logout</a></li>
			</ul>

		</div>
		<div class="content">
