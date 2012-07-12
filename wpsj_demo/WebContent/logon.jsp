<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page errorPage="/pageError.jsp" %>
<%@ page import="csc.traning.wpsj.servlet.lessons.*;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logon</title>
</head>
<body>
<%if(request.getAttribute("message") != null){%>
<span style="color: red;"><%=request.getAttribute("message")%> </span>
<%	
}	
%>
	<fieldset style="width:400px;">
	
		<legend>Login Form</legend>
		<div style="clear:both;text-align: right;"><a href="pages/profile_register.html"> Register and Login </a> </div>
		<form action="logon" method="post">
			<table align="left" width="200px">
				<tr>
					<td>Username:</td>
					<%
					//session.invalidate();
					
					 String userName = CookieUtilities.getCookieValue(request, "userName", "");
					%>
					<td><input name="userName" type="text" value ="<%=userName%>"/></td>
					
				</tr>
				<tr>
					<%
					 String password = CookieUtilities.getCookieValue(request, "password", "");
					%>
					<td>Password:</td>
					<td><input name="password" type="password" value ="<%=password%>" /></td>
					
				</tr>

				<tr align="center">
					<td colspan="2"> <br /><input type="submit" value ="Submit" />
					 	<input name="rememberme" type="checkbox" value="yes" />Remember me.
					</td>
					
					
				</tr>
			</table>	
		
                     
		</form>
	</fieldset>
	
</body>
</html>