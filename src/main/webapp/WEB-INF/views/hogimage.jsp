<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags" %>


<head>

<title>Оригинальное изображение</title>
</head>
<body>
	<div>
		<form:form method="post" modelAttribute="file">
			<p>Распознанный файл: ${file.getDetectPeopleFile().getName()}</p>
			<img alt="${file.getDetectPeopleFilePath()}" src="${file.getDetectPeopleFilePath()} ">
		</form:form>	
	</div>
</body>

