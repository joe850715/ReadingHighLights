package ch5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginCh5")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private Map<String, String> users = new HashMap<String, String>() {{
        put("adm", "1111");
        put("stu", "2222");
        put("test", "3333");
    }};

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Ch5Login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		if(users.containsKey(username)&&users.get(username).equals(password)) {
			User temp;
			request.getSession().setAttribute("user",
				temp=new User(username,request.getRemoteAddr(),
								request.getHeader("user-agent")));
			
			Map<String,HttpSession> sessions = UserOnline.sessions;
			Integer num = sessions.size();
			
			request.setAttribute("num", num);
			request.setAttribute("username", username);
			request.setAttribute("loginInfo", temp.toString());

			response.sendRedirect("welcome.jsp");
		}else {
			out.print("<h3>登入失敗!帳號或密碼錯誤!</h3>");
			out.print("<a href='Ch5Login.jsp'><h3>返回登入頁</h3></a>");
		}
		
	}

}
