<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/index.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
</head>

<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<h1>선수상세보기</h1>
		<table>
			<tr>
				<td>player_name</td>
				<td><input type="text" value="${player.player_name}"
					name="player_name"></td>
			</tr>
			<tr>
				<td>previous_team</td>
				<td><input type="text" value="${transfer.previous_team}"
					name="previous_team" required="required"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" value="${emp.email}" name="email"
					required="required"></td>
			</tr>
			<tr>
				<td>phone</td>
				<td><input type="tel" value="${emp.phone_number}"
					name="phone_number"></td>
			</tr>
		</table>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>