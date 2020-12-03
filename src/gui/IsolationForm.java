package gui;

import java.sql.Date;
import java.util.List;

import javafx.scene.control.*;

public class IsolationForm extends FormFunction{
	
	private TextField ten;
	private List<RadioButton> mucdoChoose;
	private Date ngaybatdau;
	private TextField diadiemCachLy;
	private CheckBox daKiemTra;
	
	private Label ten_label;
	private Label mucdo;
	private Label ngay;
	private Label diadiem;
	private Label trangthai;
	
	public IsolationForm()
	{
		super();
		setIsolationForm();
	}
	
	private void setIsolationForm()
	{
		this.setStyle("-fx-background-color: blue;"
				+ "-fx-padding: 20 20 20 20;");
	}
}
