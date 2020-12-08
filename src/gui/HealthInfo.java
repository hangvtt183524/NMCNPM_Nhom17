package gui;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class HealthInfo extends GridPane implements Info{
	
	private Label ten_label;
	private Label tinhtrang_label;
	private Label bieuhien_label;
	private Label ngay;
	
	private TextField ten;
	private RadioButton tinhtrang_bth, tinhtrang_kbth;
	private TextArea bieuhien;
	private DatePicker ngayghinhan;
	
	public HealthInfo()
	{
		super();
		setHealthInfoPane();
	}
	
	private void setHealthInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;"
				+ "-fx-position: relative;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		column1.setPercentWidth(25);
		column2.setPercentWidth(35);
		column3.setPercentWidth(35);
		column4.setPercentWidth(5);

		this.getColumnConstraints().addAll(column1, column2, column3, column4);
		
		for (int i=0; i< 9; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/10);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(10);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		//this.setGridLinesVisible(true);
		
		this.add(setLabel(this.ten_label, "Ten"), 0, 0, 1, 1);
		this.add(setLabel(this.tinhtrang_label, "Tinh Trang"), 0, 2, 1, 1);
		this.add(setLabel(this.bieuhien_label, "Bieu Hien"), 0, 4, 1, 1);
		this.add(setLabel(this.ngay, "Ngay Ghi Nhan"), 0, 8, 1, 1);
		
		this.ten = new TextField();
		this.ten.setMaxSize(400, 1);
		this.add(this.ten, 1, 0, 2, 1);
		
		ToggleGroup group = new ToggleGroup();
		
		this.tinhtrang_bth = new RadioButton("Binh Thuong");
		this.tinhtrang_bth.setToggleGroup(group);
 
		tinhtrang_kbth = new RadioButton("Bat Thuong");
		tinhtrang_kbth.setToggleGroup(group);
		
		this.add(this.tinhtrang_bth, 1, 2, 1, 1);
		this.add(this.tinhtrang_kbth, 2, 2, 1, 1);
		
		this.bieuhien = new TextArea();
		this.setMaxSize(980.5, 355.5);
		this.add(this.bieuhien, 1, 4, 2, 3);
		
		this.ngayghinhan = new DatePicker();
		this.ngayghinhan.setPromptText("dd-MM-yyyy");
		this.add(this.ngayghinhan, 1, 8, 1, 1);
	}

	@Override
	public Label setLabel(Label label, String s)
	{
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		return label;
	}
}
