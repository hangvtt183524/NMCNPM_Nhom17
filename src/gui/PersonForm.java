package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class PersonForm extends FormFunction{
	
	private Label tenChuHo;
	private Label soThanhVien;
	
	private TextArea tenChuHo_text;
	private Spinner soThanhVien_spinner;
	
	
	public PersonForm()
	{
		super();
		setPersonForm();
	}
	public void save_event(String query) {
		
	}
	
	public void add_event(String query) {
		
	}
	
	private void setPersonForm()
	{
		this.setStyle("-fx-background-color: #c09ae6;"
				+ "-fx-padding: 20 20 20 20;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		column1.setPercentWidth(50);
		column2.setPercentWidth(50);
		
		this.getColumnConstraints().addAll(column1, column2);
		
		for (int i=0; i< 18; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/18);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.add(setLabel(this.tenChuHo, "Ten chu ho"), 0, 1, 1, 1);
		this.add(setLabel(this.soThanhVien, "So thanh vien trong gia dinh"), 1, 1, 1, 1);
		
		this.tenChuHo_text = new TextArea();
		this.tenChuHo_text.setPrefSize(200, 4);
		this.tenChuHo_text.setMaxSize(200, 4);
		this.add(this.tenChuHo_text, 0, 2, 1, 1);
		
		
		this.soThanhVien_spinner = new Spinner(1, 10, 1);
		this.soThanhVien_spinner.setEditable(true);
		this.soThanhVien_spinner.setPrefSize(100, 4);
		this.soThanhVien_spinner.setMaxSize(100, 4);
		this.add(this.soThanhVien_spinner, 1, 2, 1, 1);
	}
	
	private Label setLabel(Label label, String s) 
	{
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER_LEFT);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		
		return label;
	}
	
}