<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib tagdir="/WEB-INF/tags" prefix="mytags" %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Employee</title>
</head>
<body>
<%@page import="java.util.*;"%>
<jsp:useBean id="employeeInfo" type="csc.traning.wpsj.domain.Employee" scope="request"></jsp:useBean>

<mytags:header user="${user}">Edit Employee</mytags:header>
<mytags:message color="red"/>
 <form action="add-employee?action=edit" method="post">
	<fieldset style="width:300px;">
		<legend><b></b></legend>
		<table>
			<tr>
				<td>Employee Id:</td>
				<td>${employeeInfo.employeeId}
				<input type="hidden" name="employeeId" value="${employeeInfo.employeeId}"/>
				
				</td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" value="${employeeInfo.firstName}"/> <span style="color: red">*</span></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" value="${employeeInfo.lastName}"/><span style="color: red">*</span></td>
			</tr>
			<tr>
				<td>Department:</td>
				<td>
				<%String[] departments = {"Administrator","Accounting","Human Resource","Delivery 1","Delivery 2","Delivery 3"};%>
					<select name="department">
						<option value=""></option>
						<%for(int i=0;i<departments.length;i++) {
							if(employeeInfo.getDepartment().equals(departments[i])){%>
								<option value="<%= departments[i]%>" selected="selected"><%= departments[i]%></option>
							<%}else{ %>
								<option value="<%= departments[i]%>"><%= departments[i]%></option>
							<%}

						} %>
					</select>
				
				</td>
			</tr>
			<tr>
				<td>Cell Phone:</td>
				<td><input type="text" name="cellPhone" value="${employeeInfo.cellphone}"/></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td>
					<c:choose>
						<c:when test="${employeeInfo.sex == 0}">
							<input type="radio" name="sex" value="1" /> Male
							<input type="radio" name="sex" value="0" checked="checked" /> Female
						</c:when>
						<c:otherwise>
							<input type="radio" name="sex" value="1" checked="checked"/> Male
							<input type="radio" name="sex" value="0"/> Female
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