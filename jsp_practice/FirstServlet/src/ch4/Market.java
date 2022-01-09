package ch4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/market")
public class Market extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int num=0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		List<String> cart = Optional.ofNullable(
                (List<String>) request.getSession().getAttribute("cart"))
				.orElse(new ArrayList<>());

		HttpSession session =request.getSession();
		session.setAttribute("cart", cart);
		session.setAttribute("num",cart.size());
		
		response.sendRedirect("marketView.jsp");
	}

}
