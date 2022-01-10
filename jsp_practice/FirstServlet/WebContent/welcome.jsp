<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WELCOME PAGE</title>
</head>
<body>
	<div>
		<h1>目前線上人數:  ${num} 人</h1>
		<h1>歡迎：${username}登入</h1>
		
		<h4>LoginInfo:</h4>
		<h4>${loginInfo}</h4>
		
		<h4>Message:</h4>
		<h4>${msg}</h4>

	</div>
	
	<h3><a href='logoutCh5'>登出</a></h3>
</body>
</html>