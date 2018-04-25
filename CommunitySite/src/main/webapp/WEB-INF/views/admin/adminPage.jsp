<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="UTF-8">
	$(document)
			.ready(
					function() {
						$(document.body).on('click', '#addBox', function() {
							var boxName = $(this).prev().val();
							location.replace('${contextPath}/admin/addBox/' + boxName);
						});

						$(document.body).on('click', '#addCate', function() {
							var cateName = $(this).parent().prev().children().val();
							var boxName = $(this).parent().prev().children().attr('id');

							location.replace('${contextPath}/admin/addCate/' + cateName + '/' + boxName);

						});

						$(document.body).on('click', '#delBox', function() {
							var boxName = $(this).prev().attr("id");
							location.replace('${contextPath}/admin/delBox/' + boxName);
						});

						$(document.body).on('click', '#delCate', function() {
							var cateSeq = $(this).prev().attr('id');
							location.replace('${contextPath}/admin/delCate/' + cateSeq);

						});

						function getCate() {
							$
									.ajax({
										url : 'http://localhost:8080/community/getCate',
										type : 'GET',
										success : function(data) {
											$(data)
													.each(
															function(index, item) {
																var wrap = " ";
																$(item.cateList)
																		.each(
																				function(index2, cate) {
																					wrap = wrap
																							+ '<tr><td  id="cateName">'
																							+ cate.cate_name
																							+ '</td><td><div id="'+cate.seq+'" style="display:none;"></div><input type="button" id="delCate" value="삭제"></td><tr>';
																				})
																$('#boxList')
																		.append(
																				'<div class="col-sm-3 " id="box"><div id="boxName">'
																						+ item.boxName
																						+ '<div id="'+item.boxName+'" style="display:none;"></div><input type="button" id="delBox" value="삭제"></div><div class="panel panel-default" id="cateList"><table class="table">'
																						+ wrap
																						+ '</table><table><tr><td><input type="search" id="'+item.boxName+'" class="form-control" placeholder="카테고리"></td><td><input type="button" class="create btn btn-success" id="addCate" value="입력"></td></tr></table></div></div>');
															})
										},
										error : function() {
											alert("에러.");
										}
									})
						}
						getCate();
					});
</script>

<style type="text/css">
#box {
	background-color: skyblue;
	text-align: center;
	vertical-align: middle;
	line-height: 30px;
	font-weight: 300;
	font-size: 18px;
	border-style: solid;
	border-width: 1px;
}

#cateList {
	width: 90%;
	text-align: center;
	vertical-align: middle;
	line-height: 30px;
	font-weight: 300;
	font-size: 18px;
	margin: 0 auto;
}
</style>

</head>

<body>
	<%@ include file="./../include/top.jsp"%>

	<div class="container" style="min-height: 800px;">
		<div class="page-header">
			<h1>관리자 페이지입니다</h1>
			<div>${err }</div>
		</div>


		<div>
			<input type="text"><input type="button" id="addBox" value="박스입력">
		</div>

		<div class="col-sm-12" id="boxList"></div>

	</div>

</body>
</html>