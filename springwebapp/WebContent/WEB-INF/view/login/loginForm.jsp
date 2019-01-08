<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="login" method=post>
		<p>
			<label>이메일 : </label>
			<c:if test="${!loginCommand.rememberEmail}">
				<input type="text" name="email" />
			</c:if>
			<c:if test="${loginCommand.rememberEmail}">
				<input type="text" name="email" value="${loginCommand.email}"/>
			</c:if>
		</p>
		<p>
			<label>암 호 : </label> <input type="password" name="password" />
		</p>
		<label>이메일 기억하기 :</label>
		<c:if test="${!loginCommand.rememberEmail }">
			<input type="checkbox" name="rememberEmail" />
		</c:if>
		<c:if test="${loginCommand.rememberEmail}">
			<input type="checkbox" name="rememberEmail" checked="checked"/>
		</c:if>
		<p>
			<input type="submit" value="로그인" /> 
			<input type="reset" />
		</p>
	</form>
</body>
</html>