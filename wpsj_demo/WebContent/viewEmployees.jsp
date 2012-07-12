<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib tagdir="/WEB-INF/tags" prefix="mytags" %>

<% if(session.getAttribute("user") == null){
	response.sendRedirect("logon.jsp");
}
	
	%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Employees</title>	
</head>
<body>

<mytags:header user="${user}">Employee List</mytags:header>
<mytags:message color="blue"/>

 <div style="clear:both;width: 800px;">
	<table border="1">
		<tr bgcolor="#FFAD00">
			<th><input type="checkbox" name="ID" value="All" /></th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Employee Id</th>
			<th>Cell phone</th>
			<th>Department</th>
			<th>Sex</th>
			
		</tr>
		
		<c:forEach var="e" items="${employees}">
			<tr>
			<td><input type="checkbox" name="ID" value="${e.employeeId }" /></td>
			<td>${e.firstName} &nbsp; &nbsp;</td>
			<td>${e.lastName} &nbsp; &nbsp;</td>
			<td>${e.employeeId } &nbsp; &nbsp;</td>
			<td>${e.cellphone } &nbsp; &nbsp;</td>
			<td>${e.department} &nbsp; &nbsp;</td>
			<td>
				<c:choose>
					<c:when test="${e.sex == 1 }">
						<c:out value="Male"></c:out>
					</c:when>
					<c:otherwise>
						<c:out value="Female"></c:out>
					</c:otherwise>
				</c:choose>
			<td>
			<c:choose>
				<c:when test="${user.role =='admin' }">
					<a href="edit-employee?employeeId=${e.employeeId }">Edit</a>
				</c:when>
				<c:otherwise>Edit</c:otherwise>
			</c:choose>
			
			
			</td>
		</tr>
		
		
		</c:forEach>	
	</table>
	<br />
	<input type="button" name="addemployee" value="Add" onclick="window.location.href='addEmployee.jsp'"/>
	<input type="button" name="addemployee" value="Delete" />
</div>
</body>
</html>