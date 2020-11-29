package entities;

public class Human {
	private String ten;
	private String socmnd;
	private String ltrinh;
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSocmnd() {
		return socmnd;
	}
	public void setSocmnd(String socmnd) {
		this.socmnd = socmnd;
	}
	public String getLtrinh() {
		return ltrinh;
	}
	public void setLtrinh(String ltrinh) {
		this.ltrinh = ltrinh;
	}
	public Human(String ten, String socmnd, String ltrinh) {
		super();
		this.ten = ten;
		this.socmnd = socmnd;
		this.ltrinh = ltrinh;
	}
	
	

}
