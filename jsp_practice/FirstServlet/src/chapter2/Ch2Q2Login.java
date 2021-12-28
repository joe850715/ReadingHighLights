package chapter2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Ch2Q2Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String header = 
			"<!DOCTYPE html>"+
			"<html>"+
			"<head>"+
			"<title>Login Result</title>"+
			"</head>"+
			"<body>";
	String footer=
			"</body>"+
			"</html>";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		out.print(header);
		if("barinhow".equals(username)&&"850715".equals(password)) {
			out.print("<h3>登入成功!!</h3>");
		}else {
			out.print("<h3>登入失敗!帳號或密碼錯誤!</h3>");
			out.print("<a href='ch2Form.html'><h3>返回登入頁</h3></a>");
		}
		out.print(footer);
	}

}
