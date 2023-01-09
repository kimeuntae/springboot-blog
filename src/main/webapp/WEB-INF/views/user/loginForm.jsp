<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="../layout/header.jsp" %>
	<br>
	<div class="container">
		<form action="/action_page.php">
		  <div class="form-group">
		    <label for="username">Username</label>
		    <input type="username" class="form-control" placeholder="Enter username" id="username">
		  </div>
		  
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <input type="password" class="form-control" placeholder="Enter password" id="pwd">
		  </div>
		  
		  <div class="form-group form-check">
		    <label class="form-check-label">
		      <input class="form-check-input" type="checkbox"> Remember me
		    </label>
		  </div>
		  
		  <button type="submit" class="btn btn-primary">로그인</button>
		</form>		
	</div>
	<%@ include file="../layout/footer.jsp" %>
</body>
</html>
