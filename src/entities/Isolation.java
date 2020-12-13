package entities;

import javafx.scene.control.Button;

public class Isolation {
	private String stt;
	private String cccd;
	private String ten;
	private String muc_do;
	private String dia_diem;
	private String ngay_bat_dau;
	private boolean da_xet_nghiem;
	private boolean da_het_han;
	private Button more;
	
	public Isolation(String stt) 
	{
		this.stt = stt;
		this.more = new Button("More");
	}
	public String getSTT() {
		return stt;
	}
	public void setSTT(String stt) {
		this.stt = stt;
	}
	public String getCCCD() {
		return cccd;
	}
	public void setCCCD(String cccd) {
		this.cccd = cccd;
	}
	public String getName() {
		return ten;
	}
	public void setName(String ten) {
		this.ten = ten;
	}
	public String getLevel() {
		return muc_do;
	}
	public void setLevel(String muc_do) {
		this.muc_do = muc_do;
	}
	public String getPlace() {
		return dia_diem;
	}
	public void setPlace(String dia_diem) {
		this.dia_diem = dia_diem;
	}
	public String getTime() {
		return ngay_bat_dau;
	}
	public void setTime(String ngay_bat_dau) {
		this.ngay_bat_dau = ngay_bat_dau;
	}
	public boolean getTest() {
		return da_xet_nghiem;
	}
	public void setTest(boolean da_xet_nghiem) {
		this.da_xet_nghiem = da_xet_nghiem;
	}
	public boolean getOutDate() {
		return da_het_han;
	}
	public void setOutDate(boolean da_het_han) {
		this.da_het_han = da_het_han;
	}
	public Button getMore() {
		return more;
	}
	public void setMore(Button more) {
		this.more = more;
	}
	
	
}
