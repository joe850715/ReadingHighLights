package spring.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JdbcController {
	
	@Autowired
	private ServletContext ctx;
	
	@RequestMapping(value = "/jdbcmvc", method = RequestMethod.GET)
	public String testJdbc(HttpServletRequest req, Model m){
		
		return null;
	}
}
