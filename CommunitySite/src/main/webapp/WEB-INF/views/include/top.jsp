<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script>
	$(document).ready(function() {
		function getCate() {
			$.ajax({
				url : '${pageContext.request.contextPath }/getCate',
				type : 'GET',
				success : function(data) {
					$(data).each(function(index, item) {
						var wrap = " ";
						var cnt = 0;
						$(item.cateList).each(function(index2, cate) {
							wrap = wrap + '<li><a href="${pageContext.request.contextPath }/bbs/'+cate.seq+'">' + cate.cate_name + '</a></li>';
							cnt = 1;
						})
						wrap = '<ul class="dropdown-menu">' + wrap + '</ul>';
						if (cnt > 0) {
							$('#list').append('<li class="dropdown"><a href="#">' + item.boxName + '<span class="caret"></span></a>' + wrap + '</li>');
						} else {
							$('#list').append('<li><a href="#">' + item.boxName + '</a></li>');
						}
					})
				}
			})
		}
		getCate();
	});
</script>

<style>
.dropdown:hover ul {
	display: block;
}
</style>

<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath }/">텐싱커뮤니티</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav" id="list">
				<li><a href="#">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty pageContext.request.userPrincipal }">
					<li><a class="btn" href="${pageContext.request.contextPath }/signUpForm">Sign up</a></li>
					<li><a class="btn" href="${pageContext.request.contextPath }/loginForm">Sign in</a></li>
				</c:if>
				<c:if test="${not empty pageContext.request.userPrincipal }">
					<li><a href="#"><span class="badge">ID </span> ${pageContext.request.userPrincipal.name}</a></li>
					<form id="logOutForm" action="${pageContext.request.contextPath }/logout" method="post" class="navbar-form navbar-left">
						<input class="btn btn-primary" type="submit" value="logout"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</c:if>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
