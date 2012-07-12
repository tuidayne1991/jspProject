 <%@ taglib prefix="mine" uri="http://mywebsite.com/tags/wpsj" %>
 
<%@ attribute name="user" required="true"  type="csc.traning.wpsj.domain.User"%>

<style type="text/css">
  .header{
  	width: 800px;
  	height: 60px;
  	text-align: right;
  	background-color:  #E1EFF2;
  	padding-bottom: 50px;
  }

</style>

<SCRIPT LANGUAGE="javascript" SRC="scripts/ajax-utils.js"></SCRIPT>
<SCRIPT LANGUAGE="javascript" SRC="scripts/ajax-calls.js"></SCRIPT>

<div class="header">
	<mine:userProfiles user="${user}"/>
	<p>Welcome <b>${user.userName}</b>, &nbsp;<a href="logout">Logout</a>
	<div id="numOfOnline">Currently online: ${numOfOnline } </div>
	<p> <a href="ViewFileFolderServlet"> Go to File Dropbox </a> </p>
	<p> <a href="zip-files-download"> Download All files in Zip </a> </p>
</div>
<h1><jsp:doBody /></h1>

<script type="text/javascript">

//updateNumOfOnline();
setInterval(updateNumOfOnline, 15000);

</script>