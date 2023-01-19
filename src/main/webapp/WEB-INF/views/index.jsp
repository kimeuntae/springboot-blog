<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%@ include file="layout/header.jsp" %>
	<br>
	<div class="container">
	<c:forEach var="boards" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${boards.title}</h4>
				<a href="/board/${boards.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
		
	<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${boards.first}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
		</c:when>
		<c:otherwise>
		  	<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${boards.last}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:when>
		<c:otherwise>
		  <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li> 
		</c:otherwise>
	</c:choose>	

	</ul>		
		
	</div>
	<%@ include file="layout/footer.jsp" %>
</body>
</html>
