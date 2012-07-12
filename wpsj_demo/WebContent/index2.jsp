<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
<title>Count to 10 Example (using JSTL)</title>
</head>

<body>
<c:forEach var="i" begin="1" end="10" step="1">
<c:out value="${i}" />

<br />
</c:forEach>
<c:set var="city" value="aloha"/>

<c:out value ="${city}"/>
</body>
</html>
