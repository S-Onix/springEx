<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ChangePWD</title>
</head>
<body>
	<form action="changePassword" method="post">
		<p>
			<label>현재 비밀번호:</label>
			<br />
			<input type="password" name="currentPassword"/>
		</p>
		<p>
			<label>새 비밀번호:</label>
			<br />
			<input type="password" name="newPassword"/>
		</p>
		
		<input type="submit" value="변경하기"/>
	</form>

</body>
</html>