package gui;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import java.sql.Date;
import java.util.List;

import javafx.scene.control.*;

public class IsolationForm extends FormFunction{
	
	private TextField ten;
	private List<RadioButton> mucdoChoose;
	private Date ngaybatdau;
	private TextField diadiemCachLy;
	private CheckBox daKiemTra;
	
	private Label ten_label;
	private Label mucdo;
	private Label ngay;
	private Label diadiem;
	private Label trangthai;
	
	public IsolationForm()
	{
		super();
		setIsolationForm();
	}
	@SuppressWarnings("unchecked")

	private void setIsolationForm()
	{
		this.setStyle("-fx-background-color: blue;"
				+ "-fx-padding: 20 20 20 20;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		
		column1.setPercentWidth(23);
		column2.setPercentWidth(30);
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


		this.searchLb = new Label("Nhập thông tin tìm kiếm");
		this.searchLb.setFont(new Font("Aria", 23));
		this.searchLb.setAlignment(Pos.CENTER_LEFT);
		this.searchLb.setMaxSize(390.0, 150.0);
		this.searchLb.setPrefSize(100.0, 75.0);
		this.add(this.searchLb, 0, 1, 1, 1);
		
		this.choiceLb = new Label("Tìm kiếm theo");
		this.choiceLb.setFont(new Font("Aria", 23));
		this.choiceLb.setAlignment(Pos.CENTER_RIGHT);
		this.choiceLb.setMaxSize(390.0, 150.0);
		this.choiceLb.setPrefSize(100.0, 75.0);
		this.add(this.choiceLb, 2, 1, 1, 1);
		
		this.searchBtn = new Button("Search");
		this.add(this.searchBtn, 4, 1, 1, 1);
		
		this.searchTxt = new TextField();
		this.searchTxt.setMaxSize(250, 1);
		this.add(this.searchTxt, 1, 1, 1, 1);
		
		this.table = new TableView<Person>();
		TableColumn<Person, Integer> idHoKhauCl = new TableColumn<Person, Integer>("ID hộ khẩu");
		TableColumn<Person, Integer> idNhanKhauCl = new TableColumn<Person, Integer>("ID nhân khẩu");
		TableColumn<Person, String> hoTenCl = new TableColumn<Person, String>("Họ và tên");
		TableColumn<Person, String> cccdCl = new TableColumn<Person, String>("Số CCCD");
		
		idHoKhauCl.setPrefWidth(250);
		idNhanKhauCl.setPrefWidth(250);
		hoTenCl.setPrefWidth(400);
		cccdCl.setPrefWidth(300);
		
		this.table.getColumns().addAll(idHoKhauCl, idNhanKhauCl, hoTenCl, cccdCl);
		this.add(this.table, 0, 3, 5, 16);
		
		ObservableList<String> choices = FXCollections.observableArrayList("ID hộ khẩu", "ID nhân khẩu", "Tên", "Số CCCD");
	    ChoiceBox<String> choiceBox = new ChoiceBox<>(choices);
		this.add(choiceBox, 3, 1, 1, 1);
}
