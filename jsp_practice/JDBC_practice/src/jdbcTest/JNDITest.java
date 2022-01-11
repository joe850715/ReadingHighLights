package jdbcTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/jnditest")
public class JNDITest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sctx = request.getServletContext();
		HttpSession session = request.getSession();
		DataSource ds = (DataSource)sctx.getAttribute("datasource");
		
		String selectAll ="Select * from [dbo].[Customer]";
		List<CusBean> cusList = new ArrayList<CusBean>(); 
		
		try(Connection conn = ds.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CusBean cb = new CusBean();
				cb.setCusId(rs.getInt(1));
				cb.setCusName(rs.getString(2));
				cb.setCusSex(rs.getString(3));
				cb.setCusAge(rs.getInt(4));
				cusList.add(cb);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		session.setAttribute("allCus", cusList);
		System.out.println(cusList.toString());
		response.sendRedirect("jndiTest.jsp");
	}

}
