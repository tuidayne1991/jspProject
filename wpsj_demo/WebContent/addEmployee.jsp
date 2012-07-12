<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page import="csc.traning.wpsj.domain.Employee"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
<mytags:header user="${user}">Add Employee</mytags:header>
<mytags:message color="red"/>

<%
	csc.traning.wpsj.domain.Employee employeeInfo = (csc.traning.wpsj.domain.Employee)request.getAttribute("employeeInfo");
if(employeeInfo == null){
	employeeInfo =  new Employee();
}
%>
<form action="add-employee?action=confirm" method="post">
	<fieldset style="width:300px;">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" value="${employeeInfo.firstName }"/> <span style="color: red">*</span></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" value="${employeeInfo.lastName }"/><span style="color: red">*</span></td>
			</tr>
			<tr>
				<td>Department:</td>
				<td>
					<select name="department">
						<option value=""></option>
						<option value="Admin">Administrator</option>
						<option value="Accounting">Accounting</option>
						<option value="Human Resource">Human Resource</option>
						<option value="Delivery 1">Delivery 1</option>
						<option value="Delivery 2">Delivery 2</option>
						<option value="Delivery 3">Delivery 3</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Cell Phone:</td>
				<td><input type="text" name="cellPhone" value=""/></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td>
					<input type="radio" name="sex" value="1" checked="checked"/> Male
					<input type="radio" name="sex" value="0" /> Female
				</td>
			</tr>
		</table>
		<br />
		<input type="submit" value="Accept" /><input type="reset" value="Cancel" onclick="window.location.href ='view-employees'"/>
	</fieldset>
</form>
</body>
</html>