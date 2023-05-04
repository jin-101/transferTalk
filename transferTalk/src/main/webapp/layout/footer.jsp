<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<footer>
	<div id="footer-container">
		 <img alt="main_logo" src="${path}/images/transferTalk_logo.png">
		 <div class="inner">
		 	<div>
			    <div class="btn-group">
			      	<a href="javascript:void(0)" class="btn btn--white" onclick="window.open('${path}/layout/map.jsp','popup','width=640 height=360')">찾아오시는 길</a>
			     	<a href="javascript:void(0)" class="btn btn--white" onclick="window.open('https://www.transfermarkt.com/','popup','width=1280 height=720')">참고사이트</a>
			    </div>
			     <div class="info">
			       	<span>TEL : 02) 6050-3114 </span>
			       	<span>담당자 : 손준범, 김창겸, 서준호, 한진</span>
			     </div>
			     <p class="copyright">
			       &copy; <span class="this-year"></span> Transfer_Talk Team.
			     </p>
		     </div>
	     </div>
	</div>
</footer>