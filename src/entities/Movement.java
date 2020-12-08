package entities;

import java.sql.Date;

public class Movement {
	private int id_nhan_khau;
	private String vung_dich_di_qua;
	private Date ngay_di;
	
	public Movement()
	{
		
	}
	
	public Movement(int id, String diadiem, Date thoigian)
	{
		this.id_nhan_khau = id;
		this.vung_dich_di_qua = diadiem;
		this.ngay_di = thoigian;
	}

	public int getId_nhan_khau() {
		return id_nhan_khau;
	}

	public void setId_nhan_khau(int id_nhan_khau) {
		this.id_nhan_khau = id_nhan_khau;
	}

	public String getVung_dich_di_qua() {
		return vung_dich_di_qua;
	}

	public void setVung_dich_di_qua(String vung_dich_di_qua) {
		this.vung_dich_di_qua = vung_dich_di_qua;
	}

	public Date getNgay_di() {
		return ngay_di;
	}

	public void setNgay_di(Date ngay_di) {
		this.ngay_di = ngay_di;
	}
	
	
}
