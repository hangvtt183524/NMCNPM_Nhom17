package entities;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	
	private int order;
	private int id_ho_khau;
	private String fullName;
	private String day, month, year;
	private String Address;
	private String cert;
	private boolean gender;
	
	//private ArrayList<PersonForm> PersonFormList;
	public Person(int order, String address)
	{
		this.order = order;
		this.Address = address;
		this.fullName = null;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getId_ho_khau() {
		return id_ho_khau;
	}

	public void setId_ho_khau(int id_ho_khau) {
		this.id_ho_khau = id_ho_khau;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	
}
	
