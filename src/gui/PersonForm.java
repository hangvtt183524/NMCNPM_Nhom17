package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PersonForm extends FormFunction{
	
	private Label tenChuHo;
	private Label soThanhVien;
	private Label diachi;
	
	private TextField tenChuHo_text;
	private Spinner soThanhVien_spinner;
	private TextField diachi_text;
	
	private MemberInfo memberInfoPane;
	
	
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
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		column1.setPercentWidth(12);
		column2.setPercentWidth(35);
		column3.setPercentWidth(28);
		column4.setPercentWidth(15);
		column5.setPercentWidth(10);
		
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
		
		for (int i=0; i< 18; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/18);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(5);
		this.setVgap(10);
		//this.setGridLinesVisible(true);
		
		this.add(setLabel(this.tenChuHo, "Ten chu ho"), 0, 1, 1, 1);
		this.add(setLabel(this.soThanhVien, "So thanh vien trong gia dinh"), 2, 1, 1, 1);
		this.add(setLabel(this.diachi, "Dia chi"), 0, 2, 1, 1);
		
		this.tenChuHo_text = new TextField();
		//this.tenChuHo_text.setPrefColumnCount(1);
		this.tenChuHo_text.setMaxSize(300, 1);
		//this.tenChuHo_text.setFont(new Font("Arial", 17));
		this.add(this.tenChuHo_text, 1, 1, 1, 1);
		
		this.diachi_text = new TextField();
		this.diachi_text.setMaxSize(400, 1);
		this.add(this.diachi_text, 1, 2, 1, 1);
		
		
		this.soThanhVien_spinner = new Spinner(1, 10, 1);
		this.soThanhVien_spinner.setEditable(true);
		//this.soThanhVien_spinner.setPrefSize(100, 4);
		this.soThanhVien_spinner.setMaxSize(100, 4);
		this.add(this.soThanhVien_spinner, 3, 1, 1, 1);
		
		this.addBtn = new Button("Add");
		this.addBtn.setPrefSize(70, 5);
		this.addBtn.setMaxSize(70, 5);
		this.add(this.addBtn, 2, 2, 1, 1);
		
		this.memberInfoPane = new MemberInfo(5);
		this.add(this.memberInfoPane, 0, 6, 5, 8);
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