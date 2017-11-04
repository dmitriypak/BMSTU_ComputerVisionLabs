<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Лабораторные работы по Компьютерной безопасности</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
</head>
<body>
	<h2>OpenCV Java Spring Webapp</h2>
	<%@ page import = "org.opencv.core.Core" %>
	Core.VERSION: <%= Core.VERSION %>


	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h2 class="display-3">Распознавание образов</h2>
			<p class="lead">Лабораторные работы Пак Д.В. ВИУ7-88</p>
		</div>
	</div>

	<div class="list-group">
		<a href="${flowExecutionUrl}&_eventId=hogdetectorPage"
			class="list-group-item list-group-item-action">HOG-детектор</a> 
	</div>

</body>
</html>
