package gui;

import controller.RecordInformation;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class MovementForm extends FormFunction{
	private MoveInfo moveInfo;
 
	public MovementForm()
	{
		super();
		setMovementForm();
	}
	
	public void setMovementForm() {
		
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
		this.add(this.color, 0, 1, 6, 4);
		
		this.addBtn = new Button("Add");
		this.addBtn.setMaxSize(100.0, 35.0);
		this.addBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.add(this.addBtn, 1, 6, 1, 2);

		//this.add(this.saveBtn, 4, 20, 1, 2);
		//this.add(this.next, 3, 20, 1, 2);
		
		this.saveBtn = new Button("Save");
		
		setAddButtonEventHandle();
	}

	private void getMoveInfo()
	{
		this.moveInfo = new MoveInfo();
		this.add(this.moveInfo, 1, 8, 4, 12);
	}


	private void setSaveAndNextButton()
	{
		this.saveBtn = new Button("Save");
		this.saveBtn.setMaxSize(100.0, 35.0);
		this.saveBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		
		this.add(this.saveBtn, 4, 20, 1, 2);
		
		setSaveButtonEventHandle();
	}

	private void setAddButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   getMoveInfo();
				   setSaveAndNextButton();
			   } 
			};
		this.addBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	private void setSaveButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   moveInfo.saveInfo("");
				   if (moveInfo.getSuccess()) saveBtn.setDisable(true);
			   } 
			};
		this.saveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

}
