<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/register.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/register.js"></script>
</head>
<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
		<div class="container">
			<!-- action="${pageContext.request.contextPath}/login/signup"  -->
			<form method="post" action="${path}/login/signup">
				<table class="table table-bordered table-hover"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="4"><h4>회원가입양식</h4></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 110px;"><h5>아이디</h5></td>
							<td><input class="form-control" type="text" id="userid"
								name="user_id" maxLength="20" required placeholder="아이디를 입력해주세요"></td>
							<td style="width: 110px;"><button
									class="btn btn-primary register-id-check">중복체크</button>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>비밀번호</h5></td>
							<td colspan="2"><input class="form-control" type="password"
								id="userpw" name="user_pw" maxLength="50" required
								placeholder="비밀번호를 입력해주세요"></td>

						</tr>
						<tr>
							<td style="width: 110px;"><h5>이름</h5></td>
							<td colspan="2"><input class="form-control" type="text"
								id="username" name="user_name" maxLength="20" required
								placeholder="이름을 입력해주세요"></td>

						</tr>
						<tr>
							<td style="width: 110px;"><h5>전화번호</h5></td>
							<td colspan="2"><input class="form-control" type="tel"
								id="phone" name="phone" pattern="010-[0-9]{4}-[0-9]{4}"
								maxLength="13" required
								placeholder="전화번호를 입력해주세요 (예시:010-****-****)"></td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>이메일</h5></td>
							<td colspan="2"><input class="form-control" type="email"
								id="email" name="email" maxLength="40" required
								placeholder="이메일을 입력해주세요"></td>
						</tr>
						<tr>
							<td style="text-align: right" colspan="3">
								<!-- <input id="signup-btn" class="btn btn-primary pull-right" type="button" value="회원가입"> -->
								<button id="signup-btn" class="btn btn-primary pull-right">회원가입</button>
							</td>

						</tr>
						<tr>
							<td style="text-align: right" colspan="3"><input
								class="btn btn-primary pull-right" type="button" value="로그인하러가기"
								onclick="location.href='${path}/layout/login.jsp';"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<%
		String messageContent = null;
		if (session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if (session.getAttribute("messageType") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		if (messageContent != null) {
		%>
		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
			aria-hidden="ture">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div 
						class="modal-content">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								<%=messageType%>
							</h4>
						</div>
						<div class="modal-body">
							<%=messageContent%>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
	</div> 
		<script>
			console.log($('#messageModal'));
			$('#messageModal').modal("show");
		</script>
		<%
		session.removeAttribute("messgaeContent");
		session.removeAttribute("messageType");
		}
		%>
		<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
			aria-hidden="ture">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div id="checkType" class="modal-content panel-info %>">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">확인메시지</h4>
						</div>
						<div class="modal-body" id="checkMessage"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>
</html>