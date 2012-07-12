<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib tagdir="/WEB-INF/tags" prefix="minetags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation Employee Info </title>
</head>
<body>
<%@page import="java.util.*;"%>
<jsp:useBean id="employeeInfo" type="csc.traning.wpsj.domain.Employee" scope="session"></jsp:useBean>
<minetags:header user="${user}"></minetags:header>

 <form action="add-employee?action=add" method="post">
	<fieldset style="width:300px;">
		<legend><b>Confirm Employee Information</b></legend>
		<table>
			<tr>
				<td>Employee Id:</td>
				<td><c:out value="${employeeInfo.employeeId}" /></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><c:out value="${employeeInfo.firstName}" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><c:out value="${employeeInfo.lastName}" /></td>
			</tr>
			<tr>
				<td>Department:</td>
				<td>
					<c:out value="${employeeInfo.department}" />				
				</td>
			</tr>
			<tr>
				<td>Cell Phone:</td>
				<td><c:out value="${employeeInfo.cellphone}" /></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td>
					<c:choose>
						<c:when test="${employeeInfo.sex == 0}">
							<c:out value="Female" /> 
						</c:when>
						<c:otherwise>
							<c:out value="Male" /> 
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<br />
		<input type="submit" value="Accept" /><input type="reset" value="Cancel" onclick="window.location.href ='view-employees'"/>
	</fieldset>
</form>
</body>
</html>