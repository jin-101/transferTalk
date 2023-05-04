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
<link rel="stylesheet" href="${path}/css/playerSearch.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/playerSearch.js"></script>
</head>
<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<div class="search">
			<label>선수명 <input type="text" id="playerName"></label>
			<div class="btn-search">
				<input type="submit" value="검색">
			</div>
		</div>
		<div class="search-result">
			<!-- <div>
				<div>선수사진</div>
				<div>이름</div>
				<div>나라</div>
			</div> -->
		</div>
	</div>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>
</html>