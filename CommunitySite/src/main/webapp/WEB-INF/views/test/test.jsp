<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>

<link href="${contextPath}/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<!-- include summernote css/js-->
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
</script>


</head>
<body>
	<textarea name="content" id="summernote"></textarea>

	<input type="button" onclick="funcMyHtml(this)" value="test">
	<input type="button" onclick="funcInputHtml(this)" value="test2">

	<script type="text/javascript">
		function funcMyHtml() {
			var con = $('#summernote').summernote('code');
			alert(con);
		}

		function funcInputHtml() {
			$('#summernote').summernote('code', "붙여 넣는다!");

		}
	</script>

</body>
</html>
