package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ViewForm extends FormFunction{
	private ChoiceBox<String> choiceBox;
	private ObservableList<String> menu; 
	private TextField search;
    private Button search_btn;
    
    private String select;
    
    private RecordInfo viewResult;
    private ViewMoveInfo viewMove;
    private ViewIsolateInfo viewIsolation;

	public ViewForm()
	{
		super();
		this.search = null;
		setViewForm();
	}
	
	private void setViewForm()
	{		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		column1.setPercentWidth(5);
		column2.setPercentWidth(17);
		column3.setPercentWidth(40);
		column4.setPercentWidth(18);
		column5.setPercentWidth(15);
		column6.setPercentWidth(5);
		
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);
		
		for (int i=0; i< 19; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/19);
        	this.getRowConstraints().add(rowConst);
        }
		//this.setGridLinesVisible(true);
		this.setHgap(10);
		this.setVgap(5);
		
		this.setColorLabel("Thống kê tình hình dịch bệnh hàng tuần");
		this.add(this.color, 0, 1, 6, 3);
		
		this.menu = FXCollections.observableArrayList("Địa điểm", "Kết quả xét nghiệm", "Cách ly");
		this.choiceBox = new ChoiceBox<String>(menu);
		
		this.add(this.choiceBox, 1, 5, 1, 1);
		
		this.search = new TextField("Thông tin cần tìm");
		this.add(this.search, 2, 5, 1, 1);
		
		this.search_btn = new Button("Search");
		this.add(this.search_btn, 3, 5, 1, 1);
		this.search_btn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.search_btn.setMaxSize(100.0, 35.0);
		setChoiceBoxEventHandle();
		setSearchButtonEventHandle();
		
		this.saveBtn = new Button("Update");
		this.saveBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.saveBtn.setMaxSize(100.0, 35.0);
		setUpdateButtonEventHandle();
	}
	
	private void getViewResult()
	{
		this.viewResult = new RecordInfo();
		this.add(this.viewResult, 1, 8, 4, 6);
	}
	
	private void getViewMove()
	{
		this.viewMove = new ViewMoveInfo(this.search.getText());
		this.add(this.viewMove, 1, 8, 4, 6);
	}
	
	private void setUpdateButton()
	{
		this.add(this.saveBtn, 4, 15, 1, 2);
	}
	private void getViewIsolation()
	{
		this.viewIsolation = new ViewIsolateInfo();
		this.add(this.viewIsolation, 1, 8, 4, 6);
	}
	private void setChoiceBoxEventHandle()
	{
		ChangeListener<String> changeListener = new ChangeListener<String>() {
			 
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				select = newValue;
				if(newValue.equals("Ket qua xet nghiem")) search.setDisable(true);
				else search.setDisable(false);
				//System.out.println(select);
			}
	       };
	       this.choiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
	 
	}
	
	private void removeComponent(Object o)
	{
		if (this.getChildren().contains(o))
			this.getChildren().remove(o);
	}
	
	private void setSearchButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   if (select != null && select.equals("Kết quả xét nghiệm")) {
					   if (viewIsolation != null) {
							removeComponent(viewIsolation);
							removeComponent(saveBtn);
					   }
					   if (viewMove != null) removeComponent(viewMove);
					   getViewResult();
				   }
				   else if (select != null && select.equals("Địa điểm") && !search.getText().equals("") && !search.getText().equals("Thông tin cần tìm")) {
						
							if (viewResult != null) {
								removeComponent(viewResult);
								removeComponent(saveBtn);
							}
							if (viewIsolation != null) {
								removeComponent(viewIsolation);
								removeComponent(saveBtn);
							}
							getViewMove();
					}
				   else if (select != null && select.equals("Cách ly")) {
					   if (viewResult != null)
							removeComponent(viewResult);
						if (viewMove != null)
							removeComponent(viewMove);
						getViewIsolation();
						removeComponent(saveBtn);
						setUpdateButton();
				   }
					else {
						Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("Error!");
				        alert.setContentText("Hay chon thong tin muon tim kiem!");
				        alert.showAndWait();
				        return;
					}
			   } 
			};
		this.search_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	private void setUpdateButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   viewIsolation.saveInfo("");
			   } 
			};
		this.saveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
}
