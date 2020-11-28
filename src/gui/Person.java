package gui;

import java.util.ArrayList;
import java.util.Date;

public class Person extends Human {
	
	private int id_nhan_khau;
	private int id_ho_khau;
	private String ho_va_ten;
	private Date ngay_sinh;
	private String dia_chi;
	
	private ArrayList<PersonForm> PersonFormList;
	
	public int getId_nhan_khau() {
		return id_nhan_khau;
	}



	public void setId_nhan_khau(int id_nhan_khau) {
		this.id_nhan_khau = id_nhan_khau;
	}



	public int getId_ho_khau() {
		return id_ho_khau;
	}



	public void setId_ho_khau(int id_ho_khau) {
		this.id_ho_khau = id_ho_khau;
	}



	public String getHo_va_ten() {
		return ho_va_ten;
	}



	public void setHo_va_ten(String ho_va_ten) {
		this.ho_va_ten = ho_va_ten;
	}



	public Date getNgay_sinh() {
		return ngay_sinh;
	}



	public void setNgay_sinh(Date ngay_sinh) {
		this.ngay_sinh = ngay_sinh;
	}



	public String getDia_chi() {
		return dia_chi;
	}



	public void setDia_chi(String dia_chi) {
		this.dia_chi = dia_chi;
	}



	public ArrayList<PersonForm> getPersonFormList() {
		return PersonFormList;
	}



	public void setPersonFormList(ArrayList<PersonForm> personFormList) {
		PersonFormList = personFormList;
	}



	public Person(String ten, String socmnd, String ltrinh) {
		// TODO Auto-generated constructor stub
		super(ten, socmnd, ltrinh);
	}
	
	
		
}
	
