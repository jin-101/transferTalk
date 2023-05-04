<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="header-selecter-contents">
	<form class="header-selecter-form">
	  <select name="header-league" id="header-league" class="header-selecter-select">
	  	<option value="none" class="header-selecter-option">리그를 선택하세요.</option>
	  </select>
	  <%-- <span class="iconArrow"><img src="${path}/images/arrow-down.png" alt="arrow_down"></span --%>
	</form>
</div> 
<div class="header-selecter-sign sign2">
	<img class="next-arrow-Img selected" src="${path}/images/next-arrow.png" alt="next-arrow">
	<img class="next-arrow-selected-Img" src="${path}/images/next-arrow-selected.png" alt="next-arrow-selected">
</div>
<div class="header-selecter-contents">
	<form class="header-selecter-form">
	  <select name="header-team" id="header-team" class="header-selecter-select">
	  	<option value="none" class="header-selecter-option">팀을 선택하세요.</option>
	  </select>
	  <%-- <span class="iconArrow"><img src="${path}/images/arrow-down.png" alt="arrow_down"></span> --%>
	</form>
</div>
<div class="header-detail-btn detail-btn">
	<button class="btn-primary">상세보기</button>
</div>