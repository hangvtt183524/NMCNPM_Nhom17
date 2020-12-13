package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class IsolationForm extends FormFunction{
	
	private Button next;
	
	private IsoSingleInfo singleInfo;
	private ContactInfo contactInfo;
	
	public IsolationForm()
	{
		super();
		setIsolationForm();
	}
	
	private void setIsolationForm()
	{
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		ColumnConstraints column7 = new ColumnConstraints();
		ColumnConstraints column8 = new ColumnConstraints();
		column1.setPercentWidth(5);
		column2.setPercentWidth(20);
		column3.setPercentWidth(15);
		column4.setPercentWidth(15);
		column5.setPercentWidth(15);
		column6.setPercentWidth(12.5);
		column7.setPercentWidth(12.5);
		column8.setPercentWidth(5);
		
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
		
		for (int i=0; i< 20; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/20);
        	this.getRowConstraints().add(rowConst);
        }
		//this.setGridLinesVisible(true);
		
		this.setColorLabel("Khai báo thông tin cách ly");
		this.add(this.color, 0, 1, 8, 3);
		
		this.addBtn = new Button("Add");
		this.addBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.addBtn.setMaxSize(100.0, 35.0);
		this.add(this.addBtn, 1, 5, 1, 1);
		
		this.next = new Button("next");
		this.saveBtn = new Button("Save");

		setAddButtonEventHandle();	
	}
	
	private void setNextAndSaveSingleButton()
	{
		this.next = new Button("next");
		this.saveBtn = new Button("Save");
		
		this.next.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.next.setMaxSize(100.0, 35.0);
		
		this.saveBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.saveBtn.setMaxSize(100.0, 35.0);
		
		this.add(this.next, 5, 17, 1, 1);
		this.add(this.saveBtn, 6, 17, 1, 1);
		
		this.next.setDisable(false);
		this.saveBtn.setDisable(false);
		
		setSaveSingleButtonEventHandle();
		setNextSingleButtonEventHandle();
	}
	
	private void getSingleInfo()
	{
		this.singleInfo = new IsoSingleInfo();
		this.add(this.singleInfo, 1, 7, 6, 9);
	}
	
	private void getContactInfo()
	{
		this.contactInfo = new ContactInfo();
		this.add(this.contactInfo, 1, 7, 6, 9);
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
	
	private void setSaveSingleButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   if (singleInfo.getTiepxuc()) {
					   if (contactInfo.checkValid()) {
						   singleInfo.saveInfo("");
						   if (singleInfo.getSuccess() && singleInfo.getTiepxuc()) {
							   contactInfo.saveInfo(singleInfo.getCCCD());
							   if (contactInfo.getSuccess()) {
								   saveBtn.setDisable(true);
								   addBtn.setDisable(false);
							   }
						   }
					   }
				   }
				   else {
					   singleInfo.saveInfo("");
					   if (singleInfo.getSuccess()) {
						   saveBtn.setDisable(true);
						   addBtn.setDisable(false);
					   }
				   }
			   } 
			};
		this.saveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	private void setNextSingleButtonEventHandle() 
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   if (singleInfo.checkValid() && singleInfo.getTiepxuc()) {
					   getContactInfo();
					   next.setDisable(true);
					   return;
				   }
			   } 
			};
		this.next.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	private void setAddButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
					   setNextAndSaveSingleButton();
					   getSingleInfo();
					   addBtn.setDisable(true);
			   } 
			};
		this.addBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
}
