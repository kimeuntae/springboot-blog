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
		    <label for="username">Username</label>
		    <input type="text" id="username" class="form-control" placeholder="Enter username" name="username">
		  </div>
		  
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <input type="password" id="password" class="form-control" placeholder="Enter password" name="password">
		  </div>
		  
		  <div class="form-group">
		    <label for="email">Email</label>
		    <input type="text" id="email" class="form-control" placeholder="Enter email" name="email">
		  </div>		  
		</form>		
		<button id="btn-save" class="btn btn-primary">회원가입</button>
	</div>
	<script src ="/js/user.js"></script>
	<%@ include file="../layout/footer.jsp" %>
</body>
</html>
