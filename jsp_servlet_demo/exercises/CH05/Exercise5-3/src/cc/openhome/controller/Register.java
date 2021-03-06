package cc.openhome.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
	    urlPatterns={"/register"}, 
	    initParams={
	        @WebInitParam(name = "SUCCESS_PATH", value = "register_success.view"),
	        @WebInitParam(name = "ERROR_PATH", value = "register_error.view")
	    }
	)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String SUCCESS_PATH;
    private String ERROR_PATH;
    private UserService userService;
    
    @Override
	public void init() throws ServletException {
    	SUCCESS_PATH = getInitParameter("SUCCESS_PATH");
    	ERROR_PATH = getInitParameter("ERROR_PATH");
    	//嘗試呼叫，如果沒有就建立一個userService
    	userService =
                (UserService) getServletContext().getAttribute("userService");
    	userService.showUSERS();
    	System.out.println("Already show USERS!");
	}

	private final Pattern emailRegex = Pattern.compile(
        "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");

    private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
    
    private final Pattern usernameRegex = Pattern.compile("^\\w{1,16}$");

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        List<String> errors = new ArrayList<String>(); 
        if (!validateEmail(email)) {
            errors.add("未填寫郵件或格式不正確");
        }
        if(!validateUsername(username)) {
            errors.add("未填寫使用者名稱或格式不正確");
        }
        if (!validatePassword(password, password2)) {
            errors.add("請確認密碼符合格式並再度確認密碼");
        }
        
        String path;
        if(errors.isEmpty()) {
            path = SUCCESS_PATH;
            userService.tryCreateUser(email, username, password);

        } else {
            path = ERROR_PATH;
            request.setAttribute("errors", errors);
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    private boolean validateEmail(String email) {
        return email != null && emailRegex.matcher(email).find();
    }
    
    private boolean validateUsername(String username) {
        return username != null && usernameRegex.matcher(username).find();
    }

    private boolean validatePassword(String password, String password2) {
        return password != null && 
               passwdRegex.matcher(password).find() && 
               password.equals(password2);
    }
}
