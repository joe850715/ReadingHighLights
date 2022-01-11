<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JNDI TEST</title>
</head>
<body>
<h1>JNDI Testing Page</h1>

	<table>
		<thead>
			<tr>
				<td>ID</td>	
				<td>Name</td>
				<td>Sex</td>
				<td>Age</td>	
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="cus" items="${allCus}">
				<tr>
					<td>${cus.cusId}</td>
					<td>${cus.cusName}</td>
					<td>${cus.cusSex}</td>
					<td>${cus.cusAge}</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>

</body>
</html>