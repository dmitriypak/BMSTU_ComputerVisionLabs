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
			<p>В оттенках серого: ${file.getGreyScaleFile().getName()}</p>
			<input type="submit" value="Гистограмма" name="_eventId_createHistogram"/>	
			
			Макс.порог яркости <form:input path="brightness_value" id="bvalue" value="${file.getBrightness_value()}"></form:input>
			
			<p><img alt="${file.getGreyScalePath()}" src="${file.getGreyScalePath()} "></p>
			
			<p>Бинаризованный файл: ${file.getBinaryFile().getName()}</p>
			<p>Порог яркости: ${file.getBrightness_value()}</p>
			<p><img alt="${file.getBinaryFilePath()}" src="${file.getBinaryFilePath()} "></p>			
		</form:form>	
	</div>
</body>

