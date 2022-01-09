package ch4;

import static java.util.stream.Collectors.counting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("cart")==null) {
			response.sendRedirect("market");
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();	
		PrintWriter out = response.getWriter();
		List<String> cart = (List<String>) request.getSession().getAttribute("cart");
		
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>購物車</title></head><body>");
        out.printf("已採購  %s 本書籍 <br><br>", cart.size());
        out.println("<table style='text-align: left; width: 193px; height: 74px;' border='1' cellpadding='2' cellspacing='2'><tbody>");

		Map<String,Long>books = cart.stream()
							.collect(Collectors.groupingBy(String::toString, counting()));
		books.forEach((book,count) ->{
            out.println("<tr>");
            out.printf("<td style='width: 109px;'><img style='width: 89px; height: 120px;' alt='' src='images/%s.jpg'><br></td>", book);
            out.printf("<td style='width: 232px;text-align: center;'>共 %s 本<br></td></tr>",  count);
		});

        out.println("</tbody>");
        out.println("</table>");
        out.println("<br>");
        out.println("</body></html>");
	}
}
