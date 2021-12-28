package servlet.fragment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		name = "Fragment",
		urlPatterns= {"/frag"},
		loadOnStartup=1
		)
public class FirstFrag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String username =Optional.ofNullable( request.getParameter("username"))
						.map(value -> value.replaceAll("<","&lt;"))
						.map(value -> value.replaceAll(">","&gt;"))
						.orElse("Guest");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Hello Servlet</title>");
		out.print("</head>");
		out.print("<body>");
		out.printf("<h1>Hello!! %s%n, This is the first fragment!!</h1>",username);
		out.printf("<h1>requestURI= %s%n</h1>",requestURI);
		out.printf("<h1>contextPath= %s%n</h1>",contextPath);
		out.printf("<h1>servletPath= %s%n</h1>",servletPath);
		out.printf("<h1>pathInfo= %s%n</h1>",pathInfo);
		out.print("</body>");
		out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
