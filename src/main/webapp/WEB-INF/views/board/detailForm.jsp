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
	
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<button class="btn btn-warning">수정</button>
		<!-- 글쓰기 작성자만 삭제 가능 -->
		<c:if test="${board.user.id == principal.user.id}">
			<button class="btn btn-danger" id="btn-board-delete">삭제</button>
		</c:if>
		<br><br>
		<div>
			글번호 : <span id="id"><i>${board.id}</i></span>
			작성자 : <span><i>${board.user.username }</i></span>
			
		</div>
		  <div class="form-group">
		    <h3>${board.title}</h3>
		  </div>
		  <hr>
		  <div class="form-group">
		    	<div>${board.content}</div>
		  </div>
		  <hr>
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
