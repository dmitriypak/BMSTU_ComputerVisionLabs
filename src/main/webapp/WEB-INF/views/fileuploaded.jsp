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
			<p>Загруженный файл: ${file.getSourceFile().getName()}</p>
			<p></p>
			<img alt="${file.getPath()}" src="${file.getPath()} ">
			<p>Бинаризованный файл: ${file.getGreyScaleFile().getName()}</p>
			<input type="submit" value="Распознать" name="_eventId_encryptMessage"/>
			<img alt="${file.getGreyScalePath()}" src="${file.getGreyScalePath()} ">
		</form:form>

		
	</div>
	
</body>

