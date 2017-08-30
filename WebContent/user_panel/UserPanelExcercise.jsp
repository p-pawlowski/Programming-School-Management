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
<title>Zadania</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../WEB-INF/header.jspf" />
	<jsp:include page="../WEB-INF/navbar.jspf" />
	<div class=container>
		<h1>Panel użytkowanika - lista zadań</h1>
		<h2>Zalogowano jako: ${user.getName()} ${user.getSurname()}</h2>
		<table>
			<thead>
				<tr>
					<th scope="col" colspan="2"><b>Lista zadań</b></th>
				</tr>
			</thead>

			<tr>
				<th scope="col"><b>Numer zadania</b></th>
				<th scope="col"><b>Akcje</b></th>
			</tr>

			<tbody>
				<c:forEach var="list" items="${list }">
					<tr>
						<td>${list.getExcercise().getTitle()}</td>
						<td><c:choose>
								<c:when test="${list.getSolution().getId() > 0}">
									<a
										href="../school/UserPanelExcerciseEditSolution?excercise_id=${list.getExcercise().getId() }
									&solution_id=${list.getSolution().getId()}">Edytuj
										rozwiązanie</a>
								</c:when>
								<c:otherwise>

									<a
										href="../school/UserPanelExcerciseAddSolution?excercise_id=${list.getExcercise().getId()}">Dodaj
										rozwiązanie</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="../WEB-INF/footer.jspf" />
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>