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
		<!-- 글쓰기 작성자만 삭제 가능 -->
		<c:if test="${board.user.id == principal.user.id}">
			<a class="btn btn-warning" href="/board/${board.id}/updateForm">수정</a>
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
		  
		  <!-- 댓글 -->
		 <div class="card">
		 	<form action="">
		 	<input type="hidden" id="boardId" value="${board.id}">
		 	<input type="hidden" id="userId" value="${board.user.id}">
	 		<div class="card-body"><textarea id="reply-content" class="form-control" rows="1"></textarea></div>
	 		<div class="card-footer"><button id="btn-reply-save" class="btn btn-primary">등록</button></div>
	 		</form>
		 </div>
		  <br>
		 <div class="card">
	 		<div class="card-header">댓글리스트</div>
			<ul id="reply-box" class="list-group">
			  	<c:forEach var="reply" items="${board.replys}">
				  	<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex ">
							<div class="font-italic">작성자 : ${reply.user.username} </div>
							<button onclick="index.replyDelete(${board.id},${reply.id})" type="button" class="badge">삭제</button>
						</div>
					</li>			  	
			  	</c:forEach>
			</ul>	 		
		 </div>
	</div>
	<br>
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
