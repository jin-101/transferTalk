<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
</head>

<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<main>
	<div>이전팀</div>
	<div>새팀</div>
	<div>포지션</div>
	<div>이적 당시 나이</div>
	<div>이적료</div>
	
	
</main>
<jsp:include page="/layout/footer.jsp"></jsp:include>
<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>

</html>