<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<!-- 1. 지도 노드 -->
		<div id="daumRoughmapContainer1682582162170" class="root_daum_roughmap root_daum_roughmap_landing" style="width:100%"></div>
		<!--2. 설치 스크립트
			* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.-->
		<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
		<!-- 3. 실행 스크립트 -->
		<script charset="UTF-8">
			new daum.roughmap.Lander({
				"timestamp" : "1682582162170",
				"key" : "2ekv2",
				"mapHeight" : "360"
			}).render();
		</script>
	</div>
</body>
</html>