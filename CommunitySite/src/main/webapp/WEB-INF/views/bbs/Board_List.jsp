<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="./../include/top.jsp"%>


	<div class="container" style="min-height:800px;">

		<div class="page-header">
			<!-- 검색 시작 -->
			<form action="${contextPath }/BoardList" method="post" style="float: right;">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <input type="hidden" name="cate" value="${pagingInfo.cate }">
				<table>
					<tr>
						<td align="right"><select name="searchCondition">
								<option value="TITLE" label="제목" />
								<option value="CONTENT" label="내용" <c:if test="${searchCondition == 'CONTENT' }">selected </c:if>></option>
						</select></td>
						<td><input type="search" name="searchKeyword" id="search-field" class="form-control" placeholder="검색어" value="${searchKeyword }" /></td>
						<td><input type="submit" class="create btn btn-success" value="검색" /></td>
					</tr>
				</table>
			</form>
			<!-- 검색 종료 -->
			<h1>${pagingInfo.cateName }게시판</h1>
		</div>

		<!-- boardList Start -->
		<div class="panel panel-default">
			<table class="table">
				<thead>
					<tr>
						<th scope="col" width="10%">번호</th>
						<th scope="col" width="60%">제목</th>
						<th scope="col" width="10%">작성자</th>
						<th scope="col" width="10%">등록일</th>
						<th scope="col" width="10%">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${BoardList }" var="board">
						<tr>
							<td scope="col">${board.seq }</td>
							<td align="left"><a href="${contextPath}/Board?seq= ${board.seq }"> ${board.title } </a></td>
							<td>${board.writer }</td>
							<td>${board.regDate }</td>
							<td>${board.cnt }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- boardList End -->

		<a href="${contextPath }/insertBoardForm?cate=${pagingInfo.cate }" class="create btn btn-success btn-wide pull-right">새 글 쓰기</a> <br>

		<!--paging start -->
		<nav class="text-center">
			<ul class="pagination" style="margin: 0 auto;">
				<li><a href="${contextPath }/BoardList?cate=${pagingInfo.cate }&page=${pagingInfo.nowPage-1}&searchCondition=${pagingInfo.searchCondition}&searchKeyword=${pagingInfo.searchKeyword }"
					aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<c:forEach begin="${pagingInfo.startPage}" end="${pagingInfo.endPage}" step="1" var="cnt">
					<c:if test="${cnt == pagingInfo.nowPage}">
						<li class="active"><a href="${contextPath }/BoardList?cate=${pagingInfo.cate }&page=${cnt}&searchCondition=${pagingInfo.searchCondition}&searchKeyword=${pagingInfo.searchKeyword }">${cnt}</a></li>
					</c:if>
					<c:if test="${cnt != pagingInfo.nowPage}">
						<li><a href="${contextPath }/BoardList?cate=${pagingInfo.cate }&page=${cnt}&searchCondition=${pagingInfo.searchCondition}&searchKeyword=${pagingInfo.searchKeyword }">${cnt}</a></li>
					</c:if>
				</c:forEach>
				<li><a href="${contextPath }/BoardList?cate=${pagingInfo.cate }&page=${pagingInfo.nowPage+1}&searchCondition=${pagingInfo.searchCondition}&searchKeyword=${pagingInfo.searchKeyword }"
					aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
		<!--paging end -->
	</div>
	<%@ include file="./../include/footer.jsp"%>

</body>
</html>