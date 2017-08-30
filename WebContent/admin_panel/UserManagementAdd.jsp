<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zarządzanie użytkownikami</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../WEB-INF/header.jspf" />
	<jsp:include page="../WEB-INF/navbar.jspf" />
	<div class=container>
		<h1>Dodawanie użytkownika</h1>


		<form method="POST" action="../school/UserManagementAdd">
			<legend>
				<h2>Dodaj użytkownika</h2>
			</legend>

			<p>Imię</p>
			<p>
			<p></p>
			<input type="text" placeholder="Imię" name="name" style="width: 50%;">
			</p>
			<p>Nazwisko</p>
			<p>
				<input type="text" placeholder="Nazwisko" name="surname"
					style="width: 50%;">
			</p>

			<p>e-mail</p>
			<p>
				<input type="text" placeholder="e-mail" name="email"
					style="width: 50%;">
			</p>
			<p>
				<label>Grupa użytkowników </label> <select name="user_group_id">
					<c:forEach var="list1" items="${list}">
						<option value=${list1.getId()}>${list1.getName()}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<input type="submit" value="ZAPISZ">
			</p>
		</form>
	</div>
	<jsp:include page="../WEB-INF/footer.jspf" />
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>