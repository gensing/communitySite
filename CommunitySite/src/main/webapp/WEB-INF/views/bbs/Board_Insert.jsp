<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${contextPath}/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>


<!-- include summernote css/js-->
<%-- <link href="${contextPath}/resources/summernote-0.8.9/summernote.css" rel="stylesheet">
<script src="${contextPath}/resources/summernote-0.8.9/summernote.min.js"></script> --%>

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 300, // set editor height
			minHeight : null, // set minimum height of editor
			maxHeight : null, // set maximum height of editor
			focus : true,
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					for (var i = files.length - 1; i >= 0; i--) {
						sendFile(files[i], this);
					}
				}

			}
		// set focus to editable area after initializing summernote
		});

		function sendFile(file, el) {
			var form_data = new FormData();
			form_data.append('file', file);
			$.ajax({
				data : form_data,
				type : "POST",
				url : '${pageContext.request.contextPath }/image',
				cache : false,
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(url) {
					$(el).summernote('editor.insertImage', url);
					$('#imageBoard > ul').append('<li><img src="'+url+'" width="480" height="auto"/></li>');
				},
				error : function() {
					alert("이미지 첨부 실패");
				}
			});
		}
	});

	function addFile() {
		var fileInput = $("<input type='file' name='file' style='display: none' />");
		fileInput.bind("change", function() {
			var wrap = $("<div></div>");
			$(wrap).append(fileInput);
			$(wrap).append($("<p>[업로드 파일]:" + this.files[0].name + "<input type='button' value='취소' onclick='delFile(this)'></P>"));
			$($('#fileList')).append(wrap);
		}).click();
	}

	function delFile(wrap) {
		$(wrap).parent().parent().remove();
	}
</script>

</head>

<body>
	<%@ include file="./../include/top.jsp"%>
	<spring:hasBindErrors name="boardDto"/>
	<div class="container" style="min-height: 800px;">
		<div class="page-header">
			<h1>글 작성</h1>
		</div>
		
		<div class="col-sm-10 col-sm-offset-1">
			<form  action="${contextPath }/insertBoard" method="post" enctype="multipart/form-data">
				<input name="cate" type="hidden" value="${cate}" />
				<div class="btn btn-default btn-lg">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>: ${pageContext.request.userPrincipal.name }
				</div>
				<input type="text" name="title" id="search-field" class="form-control" placeholder="제목을 입력하세요" value="${re.title }" />
				<form:errors path="boardDto.title"/>
				
				<textarea id="summernote" name="content">${re.content}</textarea>
				<div class="panel panel-default">
					<div class="panel-heading">
						파일 목록 <input type="button" class="pull-right" onclick="addFile();" value="파일 추가" />
					</div>
					<div id="fileList" class="panel-body">
						<input type="file" name="file" style="display: none;">
					</div>
				</div>
				<a href="${contextPath }/BoardList?cate=${cate}" class="create btn btn-success btn-wide pull-right">작성취소</a>
				<input type="submit" class="create btn btn-success pull-right" value="작성완료" />
			</form>
		</div>
	</div>

	<%@ include file="./../include/footer.jsp"%>

</body>
</html>