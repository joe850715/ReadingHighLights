package jdbcTest;

public class CusBean {
	private Integer cusId;
	private String cusName;
	private String cusSex;
	private Integer cusAge;
	
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusSex() {
		return cusSex;
	}
	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}
	public Integer getCusAge() {
		return cusAge;
	}
	public void setCusAge(Integer cusAge) {
		this.cusAge = cusAge;
	}
	@Override
	public String toString() {
		return "CusBean [cusId=" + cusId + ", cusName=" + cusName + ", cusSex=" + cusSex + ", cusAge=" + cusAge + "]";
	}
	
}
