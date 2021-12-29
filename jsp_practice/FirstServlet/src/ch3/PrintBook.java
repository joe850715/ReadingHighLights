package ch3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/printbook")
public class PrintBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/jpeg");
		ImageIO.write(
					getImage(request.getParameter("name")),
					"JPG",
					response.getOutputStream()
				);
	}

	public BufferedImage getImage(String name) throws IOException {
		BufferedImage bi =ImageIO.read(
				getServletContext().getResourceAsStream("/WEB-INF/book.jpg"));
		Graphics g = bi.getGraphics();
		g.setColor(Color.BLACK);
		g.setFont(new Font("標楷體",Font.BOLD,22));
		g.drawString(name, 15, 45);
		return bi;
	}

}
