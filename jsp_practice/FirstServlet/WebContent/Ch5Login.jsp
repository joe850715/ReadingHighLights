<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
</head>
<body>
	<h1>-輸入名稱與密碼-</h1>
	<hr/>
	<form action="loginCh5" method="post">
		<div>
			<label for="username">使用者名稱:</label>
			<input type="text" name="username"/>
		</div>
		<div>
			<label for="password" style="width:200px;">密碼:</label>
			<input type="password" name="password"/>
		</div>
		<br>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>