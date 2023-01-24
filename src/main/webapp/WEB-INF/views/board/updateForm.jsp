<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%@ include file="../layout/header.jsp" %>
	<br>
	<div class="container">
		<form>
			<input type="hidden" id="id" value="${board.id}">
		  <div class="form-group">
		    <input type="text" name="title" id="title" class="form-control" placeholder="Enter title" value="${board.title}" >
		  </div>
		  
		  <div class="form-group">
		    	<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		  </div>
		</form>
		  <button id="btn-board-update" class="btn btn-primary">수정완료</button>
	</div>
	
	<script type="text/javascript">
	 $('.summernote').summernote({
	        placeholder: 'Hello Bootstrap 4',
	        tabsize: 2,
	        height: 300
	  });
	
	</script>
	<script src ="/js/board.js"></script>
	<%@ include file="../layout/footer.jsp" %>
</body>
</html>
