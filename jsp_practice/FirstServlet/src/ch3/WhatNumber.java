package ch3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/whatnumber")
public class WhatNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String pwd;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		pwd = new Random().ints(0,10)
								.limit(4)
								.mapToObj(String::valueOf)
								.collect(Collectors.joining());
		ImageIO.write(getpwdImage(pwd), "JPG", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		if(password.equals(pwd)) {
			out.print("<h3>登入成功!!</h3>");
		}else {
			out.print("<h3>登入失敗!帳號或密碼錯誤!</h3>");
			out.print("<a href='ch3Form.html'><h3>返回登入頁</h3></a>");
		}
	}
	
	public BufferedImage getpwdImage(String pwd) {
		BufferedImage bi = new BufferedImage(60,25,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.setColor(Color.WHITE);
		g.setFont(new Font("標楷體",Font.BOLD,16));
		g.drawString(pwd, 10, 15);
		
		return bi;
	}
}
