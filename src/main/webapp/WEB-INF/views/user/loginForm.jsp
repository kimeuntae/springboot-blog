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
		<form action="/auth/loginProc" method="post">
		  <div class="form-group">
		    <label for="username">Username</label>
		    <input type="text" name="username" id="username" class="form-control" placeholder="Enter username" >
		  </div>
		  
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <input type="password" name="password" id="password"  class="form-control" placeholder="Enter password" >
		  </div>
		  
		  <button id="btn-login" class="btn btn-primary">로그인</button>
		</form>		
	</div>
	<script src ="/js/user.js"></script>
	<%@ include file="../layout/footer.jsp" %>
</body>
</html>
