package entities;

import java.sql.Date;

import javafx.scene.control.CheckBox;


public class Isolation {
	private int id_nhan_khau;
	private int muc_do_cach_ly;
	private String da_tiep_xuc;
	private Date ngay_bat_dau;
	private String dia_diem_cach_ly;
	private CheckBox da_kiem_tra;

	
	public Isolation()
	{
		
	}
	
	public Isolation(int id, int muc_do, String tiep_xuc, CheckBox kt, String diadiem, Date thoigian)
	{
		this.id_nhan_khau = id;
		this.muc_do_cach_ly = muc_do;
		this.da_tiep_xuc = tiep_xuc;
		this.da_kiem_tra = kt;
		this.dia_diem_cach_ly = diadiem;
		this.ngay_bat_dau = thoigian;
	}

	public int getId_nhan_khau() {
		return id_nhan_khau;
	}

	public void setId_nhan_khau(int id_nhan_khau) {
		this.id_nhan_khau = id_nhan_khau;
	}

	public String getdia_diem_cach_ly() {
		return dia_diem_cach_ly;
	}

	public void setdia_diem_cach_ly(String dia_diem_cach_ly) {
		this.dia_diem_cach_ly = dia_diem_cach_ly;
	}

	public Date getngay_bat_dau() {
		return ngay_bat_dau;
	}

	public void setngay_bat_dau(Date ngay_bat_dau) {
		this.ngay_bat_dau = ngay_bat_dau;
	}
	
	public int getmuc_do_cach_ly(){
		return muc_do_cach_ly;
	}

	public void setmuc_do_cach_ly(int muc_do_cach_ly){
		this.muc_do_cach_ly = muc_do_cach_ly;
	}

	public String getda_tiep_xuc(){
		return da_tiep_xuc;
	}

	public void setda_tiep_xuc(String da_tiep_xuc){
		this.da_tiep_xuc = da_tiep_xuc;
	}

	public CheckBox getda_kiem_tra(){
		return this.da_kiem_tra;
	}

	public void setda_kiem_tra(CheckBox da_kiem_tra){
		this.da_kiem_tra = da_kiem_tra;
	}
}
