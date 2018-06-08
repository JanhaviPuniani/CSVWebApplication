<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>CSV Web Application!</h1>

	<h2>Duplicate Records</h2>
	<c:set var="count" value="1" />

	<c:forEach var="viewMap" items="${requestScope.viewMap.Duplicates}">
		
		<c:if test="${viewMap.value.size() > 1}"> 
		
		<c:out value="Duplicate Set: ${count}" /><br>
  		 ${viewMap.value}<br>
			<c:set var="count" value="${count + 1}" />
		</c:if>
	</c:forEach>

	<h2>Non Duplicate Records</h2>
	<c:forEach var="viewMap" items="${requestScope.viewMap.NonDuplicates}">

   ${viewMap}<br>
	</c:forEach>

</body>
</html>
