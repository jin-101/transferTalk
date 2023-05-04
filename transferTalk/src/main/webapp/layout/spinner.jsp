<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div id='my-spinner'>
  <div>
    <span>
    	<img src='${path}/images/loader.gif'>
    </span>
  </div>
</div>