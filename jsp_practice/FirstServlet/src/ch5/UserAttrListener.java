package ch5;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class UserAttrListener implements HttpSessionAttributeListener{
	
	public void attributeAdded(HttpSessionBindingEvent se) {
		User user = (User)se.getValue();
		user.data = String.format("%s data from DB", user.name);
	}
}
