<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Market Place</title>
</head>
<body>
	<table style='text-align: left; width: 254px; height: 94px;' border='0'
		cellpadding='2' cellspacing='2'>
		<tbody>
			<tr>
				<td style='vertical-align: top; width: 115px;'><img
					style='width: 99px; height: 82px;' alt=''
					src='images/shoppingCart.png' align='left'></td>
				<td
					style='vertical-align: middle; text-align: center; width: 1245px;'><a
					href='cart'>已採購  ${sessionScope.num} 本書籍</a><br></td>
			</tr>
		</tbody>
	</table>
	<br>
	<table style='text-align: left; width: 394px; height: 174px;'
		border='0' cellpadding='2' cellspacing='2'>
		<tbody>
			<tr>
				<td style='vertical-align: top; text-align: center;'><img
					style='width: 104px; height: 142px;' alt='' src='images/Java.jpg'><br>
					<a href='buy?book=Java'>採購此書</a><br></td>
				<td style='vertical-align: top; text-align: center;'><img
					style='width: 109px; height: 138px;' alt=''
					src='images/JavaScript.jpg'><br> <a
					href='buy?book=JavaScript'>採購此書</a><br></td>
				<td style='vertical-align: top; text-align: center;'><img
					style='width: 106px; height: 142px;' alt='' src='images/Python.jpg'><br>
					<a href='buy?book=Python'>採購此書</a><br></td>
			</tr>
		</tbody>
	</table>
	<br>
	<br> &nbsp;&nbsp;
	<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br>
	<br>

</body>
</html>