<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원목록</title>
</head>
<body>	
 	<c:if test="${! empty members}">
 	<table>
 		<tr>
 			<th>아이디</th><th>이메일</th>
 			<th>이름</th><th>가입일</th>
 		</tr>
 		<c:forEach var="mem" items="${members}">
 		<tr>
 			<td>${mem.id}</td>
 			<td><a href="<c:url value="/member/detail/${mem.id}"/>">
 				${mem.email}</a></td>
 			<td>${mem.name}</td>
 			<td><fmt:formatDate value="${mem.registerDate}" 
 			                    pattern="yyyy-MM-dd"/></td>
 		</tr>
 		</c:forEach>
 	</table>
	</c:if>
</body>
</html>