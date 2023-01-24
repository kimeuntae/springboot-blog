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
		  <input type="hidden" id="id" value="${principal.user.id}">
		  <div class="form-group">
		    <label for="username">Username</label>
		    <input type="text" value="${principal.user.username}" id="username" class="form-control" placeholder="Enter username" name="username" readonly="readonly">
		  </div>
		  
		  <c:if test="${empty principal.user.oauth}">
			  <div class="form-group">
			    <label for="pwd">Password:</label>
			    <input type="password" value="" id="password" class="form-control" placeholder="Enter password" name="password">
			  </div>
			  
			  <div class="form-group">
			    <label for="email">Email</label>
			    <input type="text" value="${principal.user.email}" id="email" class="form-control" placeholder="Enter email" name="email">
			  </div>			  
		  </c:if>
		  
		  
		</form>		
		<button id="btn-update" class="btn btn-primary">회원수정</button>
	</div>
	<script src ="/js/user.js"></script>
	<%@ include file="../layout/footer.jsp" %>
</body>
</html>
