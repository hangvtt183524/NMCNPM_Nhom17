package gui;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.RecordInformation;
import controller.ShowInformation;
import entities.Person;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class MoveInfo extends GridPane implements Info{
	
	private Label ten_label;
	private Label diadiem_label;
	private Label thoigian_label;
	
	private TextField cert;
	private TextField diadiem;
	private DatePicker thoigian;
	private CheckBox dicung;
	private ListView<String> people;
	private List<String> id_people;
	private CheckBox contact;
	
	private String cert_nguoi_di;
	private boolean success = false;
	
	public MoveInfo()
	{
		super();
		setMoveInfoPane();
	}
	@Override
	public Label setLabel(Label label, String s) {
		
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		return label;
	}
	
	private void setMoveInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		column1.setPercentWidth(25);
		column2.setPercentWidth(30);
		column3.setPercentWidth(40);
		column4.setPercentWidth(5);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4);
		
		for (int i=0; i< 10; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/10);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(10);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		//this.setGridLinesVisible(true);
		
		this.add(setLabel(this.ten_label, "CCCD"), 0, 0, 1, 1);
		this.add(setLabel(this.diadiem_label, "Vung dich di qua"), 0, 2, 1, 1);
		this.add(setLabel(this.thoigian_label, "Ngay di"), 0, 4, 1, 1);
		
		
		this.cert = new TextField();
		this.cert.setStyle("-fx-font-weight: bold;");
		this.cert.setMaxSize(400, 1);
		this.add(this.cert, 1, 0, 2, 1);
		
		this.diadiem = new TextField();
		this.diadiem.setStyle("-fx-font-weight: bold;");
		this.diadiem.setMaxSize(400, 1);
		this.add(this.diadiem, 1, 2, 2, 1);
		
		this.thoigian = new DatePicker();
		this.thoigian.setStyle("-fx-font-weight: bold;");
		this.thoigian.setPromptText("mm-dd-yyyy");
		this.add(this.thoigian, 1, 4, 2, 1);
		
		this.dicung = new CheckBox("Di cung nguoi than?");
		this.dicung.setStyle("-fx-font-size: 20;");
		this.add(this.dicung, 1, 6, 1, 1);
		
		this.contact = new CheckBox("Tiep xuc voi nguoi khac?");
		this.contact.setStyle("-fx-font-size: 20;");
		this.add(this.contact, 1, 9, 1, 1);
		this.id_people = new ArrayList<String>();
		
		checkBoxAddListener();
	}
	
	private void setListView()
	{	
		ObservableList<String> people = FXCollections.observableArrayList();
		RecordInformation getInfo = new RecordInformation();
		int id_ho_khau;
		if (this.cert.getText() != null && !this.cert.getText().equals("")) {
		try {
			getInfo.query_change("select cccd, ho_va_ten from thong_tin_nhan_khau where id_ho_khau in (select id_ho_khau from thong_tin_nhan_khau where cccd = ?);");
			getInfo.getPreStatement().setString(1, this.cert.getText());
			
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			
			while(rs.next()) {
				this.id_people.add(rs.getString("cccd"));
				if (rs.getString("cccd").equals(this.cert.getText())) {
					this.cert_nguoi_di = rs.getString("cccd");
					continue;
				}
				people.add(rs.getString("ho_va_ten"));
			}
			rs.close();
			getInfo.closeState();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Gap loi thi thuc hien yeu cau!");
	        alert.showAndWait();
	        return;
		}
		
		this.people = new ListView<String>(people);
		this.people.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		this.add(this.people, 2, 6, 1, 3);
		}
	}
	
	private void checkBoxAddListener()
	{
		this.dicung.selectedProperty().addListener(
			      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
			  		setListView();
			      });
	}
	
	public boolean getIsContact()
	{
		return this.contact.isSelected();
	}
	public TextField getCert()
	{
		return this.cert;
	}
	public TextField getDiaDiem()
	{
		return this.diadiem;
	}
	
	public DatePicker getTime()
	{
		return this.thoigian;
	}
	public void saveInfo(String s)
	{
		RecordInformation saveInfo = new RecordInformation();
		
		if (this.cert.getText() == null || this.cert.getText().equals("") || this.diadiem.getText() == null || this.diadiem.getText().equals("") || this.thoigian.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Message!");
	        alert.setContentText("Hay dien day du thong tin can thiet!");
	        alert.showAndWait();
	        return;
		}
		try {
		if (this.id_people.size() <= 0) {
			saveInfo.query_change("select cccd from thong_tin_nhan_khau where cccd = ?;");
			saveInfo.getPreStatement().setString(1, this.cert.getText());
			ResultSet rs = saveInfo.getPreStatement().executeQuery();
			
			if (rs.next()) {
				this.cert_nguoi_di = rs.getString("cccd");
				this.id_people.add(rs.getString("cccd"));
				//System.out.println(rs.getInt("id_nhan_khau"));
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error!");
		        alert.setContentText("Gap loi thi thuc hien yeu cau!");
		        alert.showAndWait();
		        return;
			}
		}
		for (int i=0; i< this.id_people.size(); i++) {
				saveInfo.query_change("insert into quan_ly_di_chuyen (cccd, vung_dich_di_qua, ngay_di) values (?, ?, ?);");
				saveInfo.getPreStatement().setString(1, this.id_people.get(i));
				saveInfo.getPreStatement().setString(2, this.diadiem.getText());
				saveInfo.getPreStatement().setDate(3, new Date(this.thoigian.getValue().getMonthValue(), this.thoigian.getValue().getDayOfMonth(), this.thoigian.getValue().getYear()));
				saveInfo.getPreStatement().executeUpdate();
		} 
		this.success = true;
		saveInfo.closeState();
			
		}catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Gap loi thi thuc hien yeu cau!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return;
		}
	}
	
	public boolean getSuccess()
	{
		return this.success;
	}
	public String getIdNguoiDi()
	{
		return this.cert_nguoi_di;
	}
}
