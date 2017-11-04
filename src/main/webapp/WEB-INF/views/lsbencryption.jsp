<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
</head>
<body>
	<form:form method="POST" enctype="multipart/form-data" modelAttribute="file">
		<fieldset>
			<input type="file" name="file" path="file" />
		</fieldset>
		<footer>
			<input class="btn btn-success" type="submit" value="Загрузить" name="_eventId_upload" />		
		</footer>
		
	</form:form>

	<%-- <P>  The time on the server is ${serverTime}. </P> --%>
</body>
</html>
