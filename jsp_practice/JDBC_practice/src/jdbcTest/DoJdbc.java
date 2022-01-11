package jdbcTest;

import java.util.ArrayList;
import java.util.List;

public class DoJdbc {

	public static void main(String[] args) {
		CusDao cd = new CusDao();
		
	//Select All
		List<CusBean> cusList = new ArrayList<CusBean>();
		cusList=cd.selectAllCus();	
		
		for(CusBean cb : cusList) {
			System.out.println(cb.toString());
		}
		
		
	}
}
