 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ attribute name="color" required="true" %>
 
<div style="background-color: yellow;width: 800px;">
	<ul style="color: ${color };">
		<c:forEach var="message" items="${messages}">
			<li>${message }</li>
		</c:forEach>
	</ul>
</div>