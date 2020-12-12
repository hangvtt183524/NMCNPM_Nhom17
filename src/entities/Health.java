package entities;

public class Health {
	private String cccd;
	private String stt;
	private String tinh_trang_suc_khoe;
	private String bieu_hien;
	
	public Health(String cccd)
	{
		this.cccd = cccd;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getTinh_trang_suc_khoe() {
		return tinh_trang_suc_khoe;
	}

	public void setTinh_trang_suc_khoe(String tinh_trang_suc_khoe) {
		this.tinh_trang_suc_khoe = tinh_trang_suc_khoe;
	}

	public String getBieu_hien() {
		return bieu_hien;
	}

	public void setBieu_hien(String bieu_hien) {
		this.bieu_hien = bieu_hien;
	}
	
	
}
