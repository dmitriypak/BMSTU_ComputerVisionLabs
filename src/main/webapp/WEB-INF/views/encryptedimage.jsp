<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags"%>


<head>

<title>Оригинальное изображение</title>

</head>
<body>
	<div>
		<p>Сообщение зашифровано</p>
		<p>Зашифрованное сообщение</p>
		<p></p>
		<textarea rows="5" cols="120">${file.getMessage()} </textarea>
		<p></p>
		<img alt="${file.getPath()}" src="${file.getPath()} ">
		
	</div>

</body>

