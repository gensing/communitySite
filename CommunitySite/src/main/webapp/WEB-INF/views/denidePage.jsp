<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>

<script type="text/javascript">
	$(document).ready(function() {
		function setContent() {
			($('#content')).append('${board.content}');
		}
		setContent();
	});
</script>

<body>
	<%@ include file="./include/top.jsp"%>

	<div class="container" style="min-height: 800px;">
		<div class="page-header">
			<h1>권한이 부족합니다.</h1>
		</div>

	</div>

	<%@ include file="./include/footer.jsp"%>
</body>
</html>