package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.RecordInformation;
import entities.Contact;
import entities.Health;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class ViewMoreHealthAndContactInfo extends GridPane implements Info{
	private TableView<Contact> contact;
	private ObservableList<Contact> list_contact;
	
	private TableView<Health> health;
	private ObservableList<Health> list_health;
	
	private int cnt_contact, cnt_health;
	private String source_cccd;
	@Override
	public Label setLabel(Label label, String s) {
		
		label = new Label(s);
		label.setFont(new Font("Aria", 25));
		label.setStyle("-fx-background-color: #f8efd4;");
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(1500.0, 10.0);
		return label;
	}

	@Override
	public void saveInfo(String s) {
		
		
	}
	
	public ViewMoreHealthAndContactInfo(String cccd)
	{
		super();
		this.cnt_contact = 0;
		this.cnt_health = 0;
		this.source_cccd = cccd;
		setViewIsolateInfoPane();
	}
	
	private void setViewIsolateInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;");
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(25);
		
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(25);
		this.getColumnConstraints().addAll(column1, column2, column3);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		this.getRowConstraints().add(row1);
		
		this.setHgap(10);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		this.setGridLinesVisible(true);
		
		this.contact = new TableView<Contact>();
		TableColumn<Contact, String> sttCol = new TableColumn<Contact, String>("STT");
		TableColumn<Contact, String> nameCol = new TableColumn<Contact, String>("Name");
		TableColumn<Contact, String> addressCol = new TableColumn<Contact, String>("Address");
		
		sttCol.setCellValueFactory(new PropertyValueFactory<>("STT"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
		
		nameCol.setMinWidth(300);
		nameCol.setMaxWidth(300);
		
		addressCol.setMinWidth(350);
		addressCol.setMaxWidth(350);
		
		sttCol.setMinWidth(80);
		sttCol.setMaxWidth(80);
		
		this.contact.getColumns().addAll(sttCol, nameCol, addressCol);
		this.contact.setEditable(false);
		
		this.health = new TableView<Health>();
		TableColumn<Health, String> sttCol2 = new TableColumn<Health, String>("STT");
		TableColumn<Health, String> tinhtrang = new TableColumn<Health, String>("Tinh Trang");
		TableColumn<Health, String> bieuhien = new TableColumn<Health, String>("Bieu hien");
		TableColumn<Health, String> ngayghinhan = new TableColumn<Health, String>("Ngay ghi nhan");
		
		sttCol2.setCellValueFactory(new PropertyValueFactory<>("STT"));
		tinhtrang.setCellValueFactory(new PropertyValueFactory<>("Tinh_trang"));
		ngayghinhan.setCellValueFactory(new PropertyValueFactory<>("Ngay_ghi_nhan"));
		bieuhien.setCellValueFactory(new PropertyValueFactory<>("Bieu_hien"));
		
		sttCol2.setMinWidth(60);
		sttCol2.setMaxWidth(60);
		
		tinhtrang.setMinWidth(120);
		tinhtrang.setMaxWidth(120);
		
		bieuhien.setMinWidth(210);
		bieuhien.setMaxWidth(210);
		
		ngayghinhan.setMinWidth(130);
		ngayghinhan.setMaxWidth(130);

		
		this.health.getColumns().addAll(sttCol2, tinhtrang, bieuhien, ngayghinhan);
		this.health.setEditable(false);
	      
	    this.list_contact = FXCollections.observableArrayList();
	    this.list_health = FXCollections.observableArrayList();
	    getContactList();
	    getHealthList();
	    this.contact.setItems(this.list_contact);
	    this.health.setItems(this.list_health);
	    this.add(this.health, 0, 0, 1, 1);
	    this.add(this.contact, 1, 0, 2, 1);
	}
	
	private void getContactList()
	{
		Contact contactor = null;
		RecordInformation getInfo = new RecordInformation();
		try {
			getInfo.query_change("select ho_va_ten, noi_tiep_xuc from quan_ly_tiep_xuc where id_nguon = ?;");
			getInfo.getPreStatement().setString(1, this.source_cccd);
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			
			while (rs.next()) {
				contactor = new Contact(""+ (++this.cnt_contact));
				contactor.setName(rs.getString("ho_va_ten"));
				contactor.setAddress(rs.getString("noi_tiep_xuc"));
				
				this.list_contact.add(contactor);
			}
			rs.close();
			getInfo.closeState();
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Khong the thuc hien yeu cau!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return;
		}
	}
	
	private void getHealthList()
	{
		Health healthy = null;
		RecordInformation getInfo = new RecordInformation();
		
		try {
			getInfo.query_change("select bieu_hien, tinh_trang_suc_khoe, ngay_ghi_nhan from quan_ly_suc_khoe where cccd = ?;");
			getInfo.getPreStatement().setString(1, this.source_cccd);
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			
			while (rs.next()) {
				healthy = new Health("" + (++this.cnt_health));
				healthy.setBieu_hien(rs.getString("bieu_hien"));
				healthy.setTinh_trang(rs.getString("tinh_trang_suc_khoe"));
				healthy.setNgay_ghi_nhan(rs.getString("ngay_ghi_nhan"));
				
				this.list_health.add(healthy);
			}
			rs.close();
			getInfo.closeState();
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Khong the thuc hien yeu cau!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return;
		}
	}
}
