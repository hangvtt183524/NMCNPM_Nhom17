package entities;

public class Health {
	private String cccd;
	private String sTT;
	private String tinh_trang;
	private String bieu_hien;
	private String ngay_ghi_nhan;
	
	public Health(String sTT)
	{
		this.sTT = sTT;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getStt() {
		return sTT;
	}

	public void setStt(String stt) {
		this.sTT = stt;
	}

	public String getTinh_trang() {
		return tinh_trang;
	}

	public void setTinh_trang(String tinh_trang_suc_khoe) {
		this.tinh_trang = tinh_trang_suc_khoe;
	}

	public String getBieu_hien() {
		return bieu_hien;
	}

	public void setBieu_hien(String bieu_hien) {
		this.bieu_hien = bieu_hien;
	}
	
	public String getNgay_ghi_nhan() {
		return ngay_ghi_nhan;
	}

	public void setNgay_ghi_nhan(String ngay_ghi_nhan) {
		this.ngay_ghi_nhan = ngay_ghi_nhan;
	}
}
