<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- CSS only -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${path}/css/index.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/login.js"></script>
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<main>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="">
				<form method="post" action="${path}/login/MainPage">
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="user_id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="user_pw" maxlength="20">
					</div>
					<div>
						<input type="submit" class="btn btn-primary form-control" value="로그인">
					</div>
					<br>
					<div id="btn-register">
						<input type="button" class="btn btn-primary form-control" value="회원가입 페이지 이동">
					</div>	

					로그인 실패 시 에러 메시지 출력
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger">
							${errorMessage}
						</div>
					</c:if>		
				</form>
			</div>
		</div>
	</div>
	
</main> 
<jsp:include page="/layout/footer.jsp"></jsp:include>
<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>
</html>