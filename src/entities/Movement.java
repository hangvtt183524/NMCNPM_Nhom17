package entities;


public class Movement {
	private String stt;
	private String ten;
	private String diachi;
	private String diemden;
	private String thoigian;
	
	public Movement(String diemden, String stt)
	{
		this.stt = stt;
		this.diemden = diemden;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getDiemden() {
		return diemden;
	}

	public void setDiemden(String diemden) {
		this.diemden = diemden;
	}

	public String getThoigian() {
		return thoigian;
	}

	public void setThoigian(String thoigian) {
		this.thoigian = thoigian;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}
	
	
}
