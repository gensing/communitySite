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
	<%@ include file="./../include/top.jsp"%>

	<div class="container" style="min-height: 800px;">
		<div class="page-header">
			<h1>${board.box_name } > ${board.cateName }</h1>
		</div>

		<div class="col-sm-10 col-sm-offset-1">
			<div id="article" class="content" role="main">
				<div class="panel panel-default clearfix">
					<div class="panel-heading clearfix">
						<div class='avatar avatar-medium clearfix pull-left'>
							<div class="avatar-info">
								<a class="nickname" href="#">${board.writer}</a>
								<div class="date-created">
									<span class="timeago">${board.regDate }</span>
								</div>
							</div>
						</div>
						<div class="content-identity pull-right">
							<div class="content-identity-count">방문자: ${board.cnt }</div>
						</div>
					</div>

					<div class="content-container clearfix">
						<div id="content-body" class="panel-body content-body ">


							<span class="label label-info">제목</span> ${board.title}

							<hr />

							<article class="content-text" style="min-height: 300px;">${board.content}</article>
							<!--파일-->
							<table class="table">
								<c:forEach items="${file }" var="file">
									<tr>
										<td width="400"><a href="${contextPath }/download/${file.fileName}?filename=${file.fileName}">[다운]:${file.fileRealName }</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>


			<div class="nav" role="navigation">
				<a href="${contextPath }/BoardList?cate=${board.cate}" class="create btn btn-success btn-wide pull-right"><i class="fa fa-pencil"></i> 글 목록</a>
				<c:if test="${board.writer == pageContext.request.userPrincipal.name }">
					<a href="${contextPath }/updateBoardForm?seq=${board.seq }" class="create btn btn-success btn-wide pull-right"><i class="fa fa-pencil"></i> 글 수정</a>
					<a href="${contextPath }/deleteBoard?seq=${board.seq }&cate=${board.cate }" class="create btn btn-success btn-wide pull-right"><i class="fa fa-pencil"></i> 글 삭제</a>
				</c:if>

			</div>
			<hr>

			<!--댓글 내용  -->
			<div class="panel panel-default ">
				<div class="panel-heading">댓글</div>
				<table class="table">
					<c:forEach items="${reply }" var="reply">
						<tr>
							<td width="15%"><span class="label label-default">${reply.writer }</span></td>
							<td width="70%">${reply.content }</td>
							<td width="10%">${reply.regDate }</td>

							<c:if test="${reply.writer == pageContext.request.userPrincipal.name }">
								<td width="5%"><a href="${contextPath }/deleteReply?seq=${reply.seq }&bId=${board.seq}">X</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="from" action="${contextPath }/insertReply" method="post">
						<input name="bId" type="hidden" value="${board.seq}" /><input type="hidden" name="boardNum" value="${bbsViewInfo.number}" />
						<textarea class="form-control" name="content" rows="2"></textarea>
						<button type="submit" class="btn btn-default pull-right">댓글 입력</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="./../include/footer.jsp"%>
</body>
</html>