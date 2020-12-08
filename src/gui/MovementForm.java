package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.ConnectDB;
import controller.ShowInformation;
import entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class MovementForm extends FormFunction{
	private Label searchLb;
	private Label choiceLb;
    private TextField searchTxt;
	private Button searchBtn;
	private TableView<Person> table;
	private ObservableList<Person> data = FXCollections.observableArrayList();
 
	public MovementForm()
	{
		super();
		setMovementForm();
	}
	
	@SuppressWarnings("unchecked")
	public void setMovementForm() {
		this.setStyle("-fx-background-color: #c09ae6;"
				+ "-fx-padding: 20 20 20 20;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		
		column1.setPercentWidth(19);
		column2.setPercentWidth(34);
		column3.setPercentWidth(15);
		column4.setPercentWidth(15);
		column5.setPercentWidth(20);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
		
		for (int i=0; i< 20; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/18);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(5);
		this.setVgap(10);
		this.setGridLinesVisible(true);
		
		this.searchLb = new Label("Enter Information");
		this.searchLb.setFont(new Font("Aria", 23));
		this.searchLb.setAlignment(Pos.CENTER_RIGHT);
		this.searchLb.setMaxSize(390.0, 150.0);
		this.searchLb.setPrefSize(100.0, 75.0);
		this.add(this.searchLb, 0, 1, 1, 1);
		
		this.choiceLb = new Label("Search by");
		this.choiceLb.setFont(new Font("Aria", 23));
		this.choiceLb.setAlignment(Pos.CENTER_RIGHT);
		this.choiceLb.setMaxSize(390.0, 150.0);
		this.choiceLb.setPrefSize(100.0, 75.0);
		this.add(this.choiceLb, 2, 1, 1, 1);
		
		this.searchBtn = new Button("Search");
		this.add(this.searchBtn, 4, 1, 1, 1);
		this.searchBtn.setOnAction(e -> {
			
		});
		
		this.searchTxt = new TextField();
		this.searchTxt.setMaxSize(250, 1);
		this.add(this.searchTxt, 1, 1, 1, 1);
		
		this.table = new TableView<Person>();
		this.table.setItems(data);
		TableColumn<Person, Integer> idHoKhauCl = new TableColumn<Person, Integer>("id_ho_khau");
		TableColumn<Person, Integer> idNhanKhauCl = new TableColumn<Person, Integer>("id_nhan_khau");
		TableColumn<Person, String> hoTenCl = new TableColumn<Person, String>("ho_va_ten");
		TableColumn<Person, String> cccdCl = new TableColumn<Person, String>("cccd");
		
		idHoKhauCl.setPrefWidth(250);
		idNhanKhauCl.setPrefWidth(250);
		hoTenCl.setPrefWidth(400);
		cccdCl.setPrefWidth(300);
		
		idHoKhauCl.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id_ho_khau"));
		idNhanKhauCl.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id_nhan_khau"));
		hoTenCl.setCellValueFactory(new PropertyValueFactory<Person, String>("ho_va_ten"));
		cccdCl.setCellValueFactory(new PropertyValueFactory<Person, String>("cccd"));
		
		this.table.getColumns().addAll(idHoKhauCl, idNhanKhauCl, hoTenCl, cccdCl);
		this.add(this.table, 0, 3, 5, 16);
		
		ObservableList<String> choices = FXCollections.observableArrayList("id_ho_khau", "id_nhan_khau", "ho_va_ten", "cccd");
	    ChoiceBox<String> choiceBox = new ChoiceBox<>(choices);
		this.add(choiceBox, 3, 1, 1, 1);
	}
	public ResultSet search_event(String query) {
		return null;
		
	}
	
}
