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
		  <div class="form-group">
		    <label for="username">Title</label>
		    <input type="text" name="title" id="title" class="form-control" placeholder="Enter title" >
		  </div>
		  
		  <div class="form-group">
		    <label for="content">Content</label>
		    	<textarea class="form-control summernote" rows="5" id="content"></textarea>
		  </div>
		</form>
		  <button id="btn-board-save" class="btn btn-primary">저장</button>
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
