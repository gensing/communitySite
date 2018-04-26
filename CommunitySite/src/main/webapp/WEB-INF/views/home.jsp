<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="./include/top.jsp"%>

	<div class="container" style="min-height: 800px;">

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>텐싱 커뮤니티 사이트</h1>
			<p>커뮤니티 항목을 관리자가 동적으로 추가, 삭제 할 수 있는 커뮤니티 사이트입니다.</p>
			<p>SpringFrameWork 기반 서버 구축</p>
			<p>mybatis를 이용한 db접근</p>
			<p>스프링 시큐리티를 사용한 접근 관리</p>
			<p>부트스트랩을 적용한 디자인</p>
			<p>기본적인 게시판으로서의 기능을 구현(summernote 적용)</p>
			<p>추가할 기능들: 이메일 전송 기능을 통한 회원가입, 좋아요 기능(기능 구현은 완료 아직 미적용), 마이페이지, 관리자 회원관리 페이지</p>
			<p>관리자 아이디: tensing@naver.com</p>
			<p>관리자 비밀번호: 1234</p>
			<p>
				<a class="btn btn-lg btn-primary" href="${contextPath}/admin/adminPage" role="button">관리자 페이지 &raquo;</a>
			</p>
		</div>


		<c:forEach items="${homeList }" var="cate">
			<div class="col-sm-6 ">
				<div class="panel panel-default">
					<div class="panel-heading">${cate.cate}</div>
					<table class="table">
						<c:forEach items="${cate.boardList}" var="boardList">
							<tr>
								<td width="70%"><a href="${contextPath}/Board?seq= ${boardList.seq }"> ${boardList.title } </a></td>
								<td width="30%">${boardList.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- /container -->

	<%@ include file="./include/footer.jsp"%>


</body>
</html>
