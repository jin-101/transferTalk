<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="${path}/css/index.css">
<link rel="stylesheet" href="${path}/css/playerInfo.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script defer src="${path}/js/index.js"></script>
<script defer src="${path}/js/playerInfo.js"></script>
</head>

<body>
	<jsp:include page="/layout/header.jsp"></jsp:include>
	<main>
        <c:set var="pre_img" value="${transfer.previous_team.team_img_src}"></c:set>
        <c:set var="new_img" value="${transfer.new_team.team_img_src}"></c:set>
        <c:set var="no_img" value="${path}/images/defaultTeam.webp"></c:set>
		<div class="playerInfo-container">
			<div id="player_name">${player.player_name}</div>
			<div id="player_age">${transfer.age }</div>
			<div id="picture_player"></div>
			<div id="previousteam">${transfer.previous_team.team_name }</div>
			<div id="picture_previousteam">
				<img src="${pre_img==null ? no_img : pre_img}">
			</div>
			<div id="newteam">${transfer.new_team.team_name }</div>
			<div id="picture_newteam">
				<img src="${new_img==null ? no_img : new_img}">
			</div>
			<div id="position">${transfer.player_position }</div>
			<div id="picture_arrow">
				<img src="../images/free-icon-right-arrow-7013540.png">
			</div>
			<div id="fee">${transfer.fee }</div>
		</div>
		<h2 class="in-out-header">History</h2>
		<div class="transferHistory-container">
				<div class="history-title">
					<div id="history-transfer_year">이적년도</div>
					<div id="history-player_age">이적당시 나이</div>
					<div id="history-position">포지션</div>
					<div id="history-fee">이적료</div>
					<div id="history-previousteam">이전팀</div>
					<div id="history-newteam">새팀</div>
				</div>
			<c:forEach var="history" items="${transferHistory}">
				<div class="history-items">
					<div id="history-transfer_year">${history.transfer_year }</div>
					<div id="history-player_age">${history.age }</div>
					<div id="history-position">${history.player_position }</div>
					<div id="history-fee">${history.fee }</div>
					<div id="history-previousteam">${history.previous_team.team_name }</div>
					<div id="history-newteam">${history.new_team.team_name }</div>
				</div>
			</c:forEach>
			<c:choose>
				<c:when test="${isFavorite }">
					<i class="fas fa-star starImg" style="font-size: 48px; color: yellow; -webkit-text-stroke: 2px gray;" onclick="myFunction()"></i> 
				</c:when>
				<c:otherwise>
					<i class="fas fa-star starImg" style="font-size: 48px; color: white; -webkit-text-stroke: 2px gray;" onclick="myFunction()"></i>
				</c:otherwise>
			</c:choose>
			
		</div>
	</main>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
	<jsp:include page="/layout/spinner.jsp"></jsp:include>
</body>
</html>