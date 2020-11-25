package gui;

import java.util.ArrayList;
import java.util.Date;

public class Person extends Human {
	
	public Person(String ten, String socmnd, String ltrinh) {
		// TODO Auto-generated constructor stub
		super(ten, socmnd, ltrinh);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public ArrayList<PersonForm> getPersonFormList() {
		return PersonFormList;
	}

	public void setPersonFormList(ArrayList<PersonForm> personFormList) {
		PersonFormList = personFormList;
	}

	private int id; 
	private Date ngaySinh;
	private String gioiTinh;
	
	private ArrayList<PersonForm> PersonFormList;
		
}
	
