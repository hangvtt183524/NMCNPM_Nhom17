package gui;

import java.sql.ResultSet;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormFunction extends GridPane {
	protected Label color;
	//protected Label searchLb;
	//protected Label choiceLb;
	
	//protected TextField searchText;
	protected Button saveBtn;
	//protected Button searchBtn;
	protected Button addBtn;
	//protected Button changeBtn;
	//protected TableView<Human> ResultSet;
	
	public FormFunction() 
	{
		super();
		this.setStyle("-fx-background-color: #f7f2e7;");
		this.setHgap(5);
		this.setVgap(10);
		
		
		this.color = new Label("");
		this.color.setStyle("-fx-background-color: #7579e7;");
		this.color.setMaxSize(1200, 150.0);
	}
	
	protected void setColorLabel(String s)
	{
		this.color.setText(s);
		this.color.setStyle("-fx-font-weight: bold;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 40;"
				+ "-fx-padding: 10 10 10 10;"
				+ "-fx-background-color: #7579e7;");
	}

}
