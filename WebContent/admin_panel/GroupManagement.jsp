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
	
	<div class="container">
	<h1>Zarządzanie Grupami</h1>

	<table>
		<thead>
			<tr>
				<th scope="col" colspan="1"><b>Nazwa grupy</b></th>
				<th scope="col" colspan="1"><b>Akcje</b></th>
			</tr>
		</thead>
		<c:forEach var="list" items="${list }">
			<tr>
				<td>${list.getName()}</td>
				<td><a
					href="../school/Users?
					user_group_id=${list.getId()}">Użytkownicy</a>
					<a
					href="../school/GroupManagementEdit?
					user_group_id=${list.getId()}">Edytuj
						grupę</a> <a
					href="../school/GroupManagementDelete?
					user_group_id=${list.getId()}">Usuń
						grupę</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="../school/GroupManagementAdd"><span
				class="glyphicon glyphicon-plus" aria-hidden="true"></span>Dodaj
			grupę</a>
	</p>
	</div>
	<p></p>
	<jsp:include page="../WEB-INF/footer.jspf" />
</body>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>