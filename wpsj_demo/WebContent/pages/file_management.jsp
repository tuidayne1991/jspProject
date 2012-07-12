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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Management</title>
<script type="text/javascript">
	function processAction(action){
		var form = document.getElementById("FileManageForm");
		form.action.value = action;
		form.submit();
		
	}
</script>

</head>
<body>
<div style="width: 1000px; height: 50px;text-align: right;">Welcome <b>${user.userName}</b>, <a href="logout">Logout</a></div>
<div style="width: 1000px">
	<div style="float: left;width:200px;text-align: left;">
		<div>
			<fieldset style="height: 200px;background: #EFEDFC">
				<legend style="color: graytext;font-weight: bold;">Common Information</legend>
				<p style="font-size: 14">Capacity: 20% in use</p>
				<p style="font-size: 14">Currently Online: 10</p>
			
			</fieldset>
		</div>
		<div>
			<fieldset style="height: 200px;background: #EFEDFC">
				<legend style="color: graytext;font-weight: bold;">User Profiles</legend>
				<p style="font-size: 14">Huynh Dang</p>
				<p style="font-size: 14">0903888777</p>
				<p style="font-size: 14">hdang@test.com</p>
				<br />
				<p style="font-size: 14;text-align: right"><a href="pages/profile_editor.html">Edit Profiles</a></p>
			</fieldset>
		</div>
	</div>
   <form action="file-upload-action" enctype="multipart/form-data" method="POST" id="FileManageForm">
    <input type="hidden" id="action" value="">
	<div style="float: left;width:800px;">
		<fieldset style="min-height:500px;">
			<legend style="color: graytext;font-weight: bold;">Files Uploaded</legend>
			<br />
			<div>
				<input type="file" name="file1"><br>
			</div>
			<div style="border-bottom: 1px; border-bottom-style: solid;">
				<input type="button" name="upload" value="Upload" onclick="processAction('upload');">
				<input type="button" name="delete" value="Delete">
				<input type="button" name="download" value="Download">
				<input type="button" name="createfolder" value="Create folder">
			</div>
			
			<div>
				<p style="font-size: 14"><a href="#">Upload Folder</a>/<a href="#">Aptech Training</a></p>
				<table style="font-size: 13">
					<tr align="left">
						<th><input type="checkbox"></th>
						<th width="350px;">File Name</th>
						<th width="100px;">Kind</th>
						<th width="100px;">Size</th>
						<th width="250px;">Modified</th>
						
					</tr>
					<c:forEach var="fileInfo" items="${fileInfoList}">
						<tr align="left">
							<td><input type="checkbox"></td>
							<td><a href="#">${fileInfo.fileName }</a></td>
							<td>${fileInfo.kind }</td>
							<td>${fileInfo.sizeInfo }</td>
							<td>${fileInfo.lastModifiedDate }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>
  </form>
</div>
</body>
</html>