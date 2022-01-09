package ch5;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserOnline implements HttpSessionListener {
	public final static Map<String,HttpSession> sessions =new HashMap<>();
	
	public void sessionCreated(HttpSessionEvent se)  { 
         HttpSession session = se.getSession();
         sessions.put(session.getId(),session);
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
         sessions.remove(se.getSession().getId());
    }
	
}
