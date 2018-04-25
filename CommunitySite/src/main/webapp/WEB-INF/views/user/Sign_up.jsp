<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="./../include/top.jsp"%>
	<spring:hasBindErrors name="userDto"/>

	<div class="container">

		<form class="form-signin" action="signUp" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<h2 class="form-signin-heading">Please sign up</h2>
			<label for="inputEmail" class="sr-only">Email address</label> <input type="email" id="inputEmail" class="form-control" name="id" placeholder="Email address" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required><input type="password"
				id="inputPassword" class="form-control" name="password2" placeholder="Password Confirm" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"> Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
		</form>
		<p>${err }</p>
		<p>${passwdConfirm }</p>
		<form:errors path="userDto.password"/>

	</div>

</body>
</html>