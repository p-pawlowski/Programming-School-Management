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
<title>Strona główna</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>


	<jsp:include page="WEB-INF/header.jspf" />
	<jsp:include page="WEB-INF/navbar.jspf" />
	<div class=container>
		<h1>Strona główna</h1>
		<h2>Ostatnie rozwiązania zadań</h2>
		<table>
			<thead>
				<tr>
					<th scope="col" colspan="4"><b>Ostatnie rozwiązania zadań</b></th>
				</tr>
			</thead>
			<tr>
				<th scope="col"><b>Numer zadania</b></th>
				<th scope="col"><b>Autor rozwiązania</b></th>
				<th scope="col"><b>Data dodania</b></th>
				<th scope="col"></th>
			</tr>
			<tbody>
				<c:forEach var="list" items="${list }">
					<tr>
						<td>${list.getExcercise().getTitle()}</td>
						<td>${list.getUser().getName()}${list.getUser().getSurname()}</td>
						<td>${list.getSolution().getCreatedDate()}</td>
						<td><a
							href="../school/user-solution?
					user_id=${list.getUser().getId()}&
					excercise_id=${list.getExcercise().getId()}&
					solution_id=${list.getSolution().getId()}">Szczegóły</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p></p>
	</div>
	<jsp:include page="WEB-INF/footer.jspf" />
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>