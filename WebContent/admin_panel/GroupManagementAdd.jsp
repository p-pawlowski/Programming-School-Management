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
<title>Zarządzanie grupami</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../WEB-INF/header.jspf" />
	<jsp:include page="../WEB-INF/navbar.jspf" />
	<div class=container>
		<h1>Zarządzanie Grupami</h1>

		<form method="POST"
			action="../school/GroupManagementAdd">
			<legend>
				<h2>Dodaj grupę</h2>
			</legend>
			<p>
			<p>Nazwa grupy
			<p>
				<input type="text" placeholder="nazwa grupy" name="name"
					style="width: 50%;"> <br>
			</p>

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