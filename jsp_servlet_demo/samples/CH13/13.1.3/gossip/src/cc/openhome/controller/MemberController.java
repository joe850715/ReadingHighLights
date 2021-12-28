package cc.openhome.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cc.openhome.model.UserService;

@Controller
public class MemberController {
    @Value("${page.member}")
    private String MEMBER_PAGE;
    
    @Value( "${path.redirect.member}")
    private String REDIRECT_MEMBER_PATH;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("member")
    @PostMapping("member")
    protected void member(
                HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {
        request.setAttribute("messages", userService.messages(getUsername(request)));
        request.getRequestDispatcher(MEMBER_PAGE).forward(request, response);
    }
    
    @PostMapping("new_message")
    protected void newMessage(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        var blabla = request.getParameter("blabla");
        
        if(blabla == null || blabla.length() == 0) {
            response.sendRedirect(REDIRECT_MEMBER_PATH);
            return;
        }        
       
        if(blabla.length() <= 140) {
            userService.addMessage(getUsername(request), blabla);
            response.sendRedirect(REDIRECT_MEMBER_PATH);
        }
        else {
            request.getRequestDispatcher(MEMBER_PAGE).forward(request, response);
        }
    }    
    
    @PostMapping("del_message")
    protected void delMessage(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var millis = request.getParameter("millis");
        if(millis != null) {
            userService.deleteMessage(getUsername(request), millis);
        }
        
        response.sendRedirect(REDIRECT_MEMBER_PATH);
    }    
    
    private String getUsername(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("login");
    }
}