package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CusDao {
	
	String url = "jdbc:sqlserver://localhost:1433;databaseName=test123";
	String user = "sa";
	String pwd = "team4";
	
	public List<CusBean> selectAllCus(){
		
		String selectAll ="Select * from [dbo].[Customer]";
		List<CusBean> cusList = new ArrayList<CusBean>(); 
		
		try(Connection conn = DriverManager.getConnection(url,user,pwd);)
		{
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
		return cusList;
	}
}
