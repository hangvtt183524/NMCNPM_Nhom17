package gui;

import java.sql.Date;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HealthForm extends FormFunction{
	
	private Label ten_label;
	private Label tinhtrang_label;
	private Label bieuhien_label;
	private Label ngay;
	
	private TextField ten;
	private ChoiceBox  tinhtrang;
	private TextField bieuhien;
	private Date ngayghinhan;
	
	public HealthForm() 
	{
		super();
		setHealthForm();
	}
	
	private void setHealthForm()
	{
		this.setStyle("-fx-background-color: red;"
				+ "-fx-padding: 20 20 20 20;");
	}
}
