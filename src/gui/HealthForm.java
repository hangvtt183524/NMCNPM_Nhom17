package gui;

import java.sql.Date;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class HealthForm extends FormFunction{

	private Label color;
	private HealthInfo healthInfo;

	public HealthForm() 
	{
		super();
		setHealthForm();
	}
	
	private void setHealthForm()
	{
		this.setStyle("-fx-background-color: #fff0f0;"
				+ "-fx-position: relative;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		column1.setPercentWidth(5);
		column2.setPercentWidth(15);
		column3.setPercentWidth(50);
		column4.setPercentWidth(8);
		column5.setPercentWidth(10);
		column6.setPercentWidth(12);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);
		
		for (int i=0; i< 25; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/25);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(10);
		this.setVgap(5);
		//this.setGridLinesVisible(true);
		
		this.color = new Label("");
		this.color.setStyle("-fx-background-color: #9ab3f5;");
		this.color.setMaxSize(1200, 150.0);
		
		this.add(this.color, 0, 1, 6, 4);
		
		this.addBtn = new Button("Add New");
		this.addBtn.setMaxSize(100.0, 35.0);
		this.addBtn.setStyle("-fx-border: red solid;");
		this.add(this.addBtn, 1, 6, 1, 2);
		
		
		this.healthInfo = new HealthInfo();
		this.add(this.healthInfo, 1, 8, 4, 12);
		
		this.saveBtn = new Button("Save");
		this.saveBtn.setMaxSize(100.0, 35.0);
		this.add(this.saveBtn, 4, 20, 1, 2);
	}

}
