<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring3 Demo</title>
</head>
<body>
	<table border="1">
		<tr>
			<td><spring:message code="board.number"></spring:message></td>
			<td><spring:message code="board.subject"></spring:message></td>
			<td><spring:message code="board.writer"></spring:message></td>
			<td><spring:message code="board.writeTime"></spring:message></td>
			<td><spring:message code="board.count"></spring:message></td>
			<td><spring:message code="board.delete"></spring:message></td>
		<tr>
			<c:forEach var="board" items="${list}">
				<tr>
					<td>${board.id}</td>
					<td><a href="detail/${board.id}">${board.subject}</a>&nbsp;<a
						href="detailajax/${board.id}">AJAX</a></td>
					<td>${board.writer}</td>
					<td>${board.writeTime}</td>
					<td>${board.count}</td>
					<td><input type="button" value="삭제"
						onclick="javas-ript:document.location.href='delete/${board.id}'"></td>
				</tr>
			</c:forEach>
	</table>
	<input type="button" value="작성"
		onclick="javas-ript:document.location.href='add'">
</body>
</html>