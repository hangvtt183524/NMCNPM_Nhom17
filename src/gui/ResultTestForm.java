package gui;

import entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class ResultTestForm extends FormFunction{
	
	private Label searchLb;
	private Label choiceLb;
    private TextField searchTxt;
	private Button searchBtn;
	private ChoiceBox option;

	private TestInfo testInfo;
	private RecordInfo recordInfo;
	
	public ResultTestForm()
	{
		super();
		setResultTestForm();
	}
	
	private void setResultTestForm() 
	{
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		ColumnConstraints column7 = new ColumnConstraints();
		column1.setPercentWidth(5);
		column2.setPercentWidth(15);
		column3.setPercentWidth(25);
		column4.setPercentWidth(15);
		column5.setPercentWidth(25);
		column6.setPercentWidth(10);
		column7.setPercentWidth(5);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7);
		
		for (int i=0; i< 20; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/20);
        	this.getRowConstraints().add(rowConst);
        }
		//this.setGridLinesVisible(true);
		
		this.add(this.color, 0, 1, 7, 3);
		
		this.add(setLabel(this.searchLb, "Nhap so CCCD"), 1, 5, 1, 1);

		this.searchTxt = new TextField();
		this.searchTxt.setStyle("-fx-font-weight: bold;");
		this.searchTxt.setStyle("-fx-font-weight: bold;");
		this.add(this.searchTxt, 2, 5, 1, 1);
		
		this.searchBtn = new Button("Search");
		this.searchBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.searchBtn.setMaxSize(100.0, 35.0);
		this.add(this.searchBtn, 3, 5, 2, 1);
		
		this.addBtn = new Button("Add");
		this.addBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.addBtn.setMaxSize(100.0, 35.0);
		this.add(this.addBtn, 1, 6, 1, 1);
		
		this.saveBtn = new Button("Save");
		this.saveBtn.setMaxSize(100.0, 35.0);
		
		setAddButtonEventHandle();
		setSearchButtonEventHandle();
	}
	
	private void setSaveButton()
	{
		this.saveBtn = new Button("Save");
		this.saveBtn.setMaxSize(100.0, 35.0);
		setSaveButtonEventHandle();
		this.saveBtn.setDisable(false);
		this.add(this.saveBtn, 5, 16, 1, 1);
	}
	private void getTestInfo()
	{
		this.testInfo = new TestInfo();
		this.add(this.testInfo, 1, 8, 5, 7);
	}
	
	private void getRecordInfo()
	{
		this.recordInfo = new RecordInfo(this.searchTxt.getText());
		this.add(this.recordInfo, 1, 8, 5, 7);
	}
	
	private Label setLabel(Label label, String s) 
	{
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		
		return label;
	}
	
	private Button setButton(Button button)
	{
		button.setMaxSize(100.0, 35.0);
		return button;
	}
	
	private void setAddButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   getTestInfo();
				   setSaveButton();
			   } 
			};
		this.addBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	private void setSaveButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   testInfo.saveInfo("");
			   } 
			};
		this.saveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	private void setSearchButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   if (searchTxt.getText() == null || searchTxt.getText().equals("")) {
					   Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("Message!");
				        alert.setContentText("Hay nhap so CCCD!");
				        alert.showAndWait();
				        return;
				   }
				   getRecordInfo();
				   removeTestInfo();
				   saveBtn.setDisable(true);
			   } 
			};
		this.searchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	private void removeTestInfo()
	{
		this.getChildren().remove(this.testInfo);
	}
}