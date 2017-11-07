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
			<p>Порог яркости: ${file.getBINARY_value()}</p>
			<input type="submit" value="Пересчитать" name="_eventId_calcHistogram"/>	
			Макс.порог яркости <form:input path="BINARY_value" id="bvalue" value="${file.getBINARY_value()}"></form:input>
			<table>
				<tr style="font-weight: bold">
					<td>Бинаризованный файл: ${file.getBinaryFile().getName()}</td>
					<td>Гистограмма яркости:</td>
				</tr>
				<tr>
					<td><img alt="${file.getBinaryFilePath()}" src="${file.getBinaryFilePath()}"></td>
					<td><img alt="${file.getHistogramPath()}" src="${file.getHistogramPath()} "></td>
				</tr>
			</table>
			
		</form:form>	
	</div>
</body>

