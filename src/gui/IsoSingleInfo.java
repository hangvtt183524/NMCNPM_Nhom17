package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.RecordInformation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class IsoSingleInfo extends GridPane implements Info{
	
	private Label cccd_label;
	private Label cachlytheo_label;
	private Label mucdo_label;
	private Label ngaybatdau_label;
	private Label diadiem_label;
	private Label xetnghiem_label;
	private Label tiepxuc_label;
	
	private TextField cccd;
	private ChoiceBox<String> mucdo;
	private RadioButton tainha, taptrung;
	private TextField diadiem;
	private RadioButton daxetnghiem, chuaxetnghiem;
	private CheckBox tiepxuc;
	private DatePicker ngaybatdau;
	private RadioButton canhan, hogiadinh;
	
	private String muc;
	private boolean success = false;

	@Override
	public Label setLabel(Label label, String s) {
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		return label;
	}

	@Override
	public void saveInfo(String s) {
		if (this.cccd.getText() == null || this.cccd.getText().equals("") || this.muc == null || (!this.tainha.isSelected() && !this.taptrung.isSelected()) //
				|| (!this.daxetnghiem.isSelected() && !this.chuaxetnghiem.isSelected()) || (!this.canhan.isSelected() && !this.hogiadinh.isSelected())) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notification!");
	        alert.setContentText("Hay dien day du cac thong tin truoc khi luu!");
	        alert.showAndWait();
	        
	        return;
		}
		
		if (this.taptrung.isSelected() && (this.diadiem.getText() == null || this.diadiem.getText().equals(""))) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notification!");
	        alert.setContentText("Cho biet dia diem cach ly tap trung!");
	        alert.showAndWait();
	        
	        return;
		}
		
		RecordInformation saveInfo = new RecordInformation();
		try {
			saveInfo.query_change("select cccd from thong_tin_nhan_khau where cccd = ?;");
			saveInfo.getPreStatement().setString(1, this.cccd.getText());
			
			ResultSet rs = saveInfo.getPreStatement().executeQuery();
			if (!rs.next()) {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error!");
		        alert.setContentText("Ban chua dang ki thong tin vao he thong!");
		        alert.showAndWait();
		        
		        return;
			}
			
			if (this.canhan.isSelected()) {
			saveInfo.query_change("insert into quan_ly_cach_ly (cccd, muc_do, dia_diem, ngay_bat_dau, da_xet_nghiem) values (?, ?, ?, ?, ?);");
			saveInfo.getPreStatement().setString(1, this.cccd.getText());
			saveInfo.getPreStatement().setString(2, this.muc);
			
			if (this.tainha.isSelected())
				saveInfo.getPreStatement().setString(3, "Tai nha");
			else saveInfo.getPreStatement().setString(3, this.diadiem.getText());
			
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = df.parse(this.ngaybatdau.getValue().getMonthValue()+"/"+this.ngaybatdau.getValue().getDayOfMonth() + "/" + this.ngaybatdau.getValue().getYear());
			saveInfo.getPreStatement().setTimestamp(4,new Timestamp(date.getTime()));
			saveInfo.getPreStatement().setBoolean(5, this.daxetnghiem.isSelected());
			
			saveInfo.getPreStatement().executeUpdate();
			}
			
			if (this.hogiadinh.isSelected()) {
				saveInfo.query_change("select cccd from thong_tin_nhan_khau where id_ho_khau in (select id_ho_khau from thong_tin_ho_khau where cccd_chu_ho = ?);");
				saveInfo.getPreStatement().setString(1, this.cccd.getText());
				rs = saveInfo.getPreStatement().executeQuery();
				
				while (rs.next()) {
					saveInfo.query_change("insert into quan_ly_cach_ly (cccd, muc_do, dia_diem, ngay_bat_dau, da_xet_nghiem, da_het_han) values (?, ?, ?, ?, ?, ?);");
					saveInfo.getPreStatement().setString(1, rs.getString("cccd"));
					saveInfo.getPreStatement().setString(2, this.muc);
					
					if (this.tainha.isSelected())
						saveInfo.getPreStatement().setString(3, "Tai nha");
					else saveInfo.getPreStatement().setString(3, this.diadiem.getText());
					
					SimpleDateFormat dff = new SimpleDateFormat("MM/dd/yyyy");
					Date date_f = dff.parse(this.ngaybatdau.getValue().getMonthValue()+"/"+this.ngaybatdau.getValue().getDayOfMonth() + "/" + this.ngaybatdau.getValue().getYear());
					saveInfo.getPreStatement().setTimestamp(4,new Timestamp(date_f.getTime()));
					saveInfo.getPreStatement().setBoolean(5, this.daxetnghiem.isSelected());
					saveInfo.getPreStatement().setBoolean(6, false);
					saveInfo.getPreStatement().executeUpdate();
				}
			}
			rs.close();
			saveInfo.closeState();
			this.success = true;
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Khong the thuc hien yeu cau!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkValid()
	{
		if (this.cccd.getText() == null || this.cccd.getText().equals("") || this.muc == null || (!this.tainha.isSelected() && !this.taptrung.isSelected()) //
				|| (!this.daxetnghiem.isSelected() && !this.chuaxetnghiem.isSelected()) || (!this.canhan.isSelected() && !this.hogiadinh.isSelected())) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notification!");
	        alert.setContentText("Hay dien day du cac thong tin truoc khi luu!");
	        alert.showAndWait();
	        
	        return false;
		}
		
		if (this.taptrung.isSelected() && (this.diadiem.getText() == null || this.diadiem.getText().equals(""))) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notification!");
	        alert.setContentText("Cho biet dia diem cach ly tap trung!");
	        alert.showAndWait();
	        
	        return false;
		}
		
		RecordInformation saveInfo = new RecordInformation();
		try {
			saveInfo.query_change("select cccd from thong_tin_nhan_khau where cccd = ?;");
			saveInfo.getPreStatement().setString(1, this.cccd.getText());
			
			ResultSet rs = saveInfo.getPreStatement().executeQuery();
			if (!rs.next()) {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error!");
		        alert.setContentText("Ban chua dang ki thong tin vao he thong!");
		        alert.showAndWait();
		        
		        return false;
			}
			rs.close();
			saveInfo.closeState();
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Khong the thuc hien yeu cau!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return false;
		}
		
		return true;
	}
	
	public IsoSingleInfo()
	{
		super();
		this.muc = null;
		setIsoSingleInfoPane();
	}
	
	private void setIsoSingleInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;"
				+ "-fx-position: relative;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-color: #7579e7;"
				+ "-fx-border-width: 3;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		
		column1.setPercentWidth(25);
		column2.setPercentWidth(20);
		column3.setPercentWidth(20);
		column4.setPercentWidth(35);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4);
		
		for (int i=0; i< 11; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/11);
        	this.getRowConstraints().add(rowConst);
        }
		//this.setGridLinesVisible(true);
		this.setHgap(10);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		
		this.add(setLabel(this.cccd_label, "CCCD"), 0, 0, 1, 1);
		this.add(setLabel(this.cachlytheo_label, "Cach ly theo"), 0, 2, 1, 1);
		this.add(setLabel(this.mucdo_label, "Muc do"), 0, 4, 1, 1);
		this.add(setLabel(this.ngaybatdau_label, "Ngay bat dau"), 2, 4, 1, 1);
		this.add(setLabel(this.diadiem_label, "Cach ly tai"), 0, 6, 1, 1);
		this.add(setLabel(this.xetnghiem_label, "Xet nghiem"), 0, 8, 1, 1);
		this.add(setLabel(this.tiepxuc_label, "Tiep xuc gan?"), 0, 10, 1, 1);
		
		this.cccd = new TextField();
		this.cccd.setStyle("-fx-font-weight: bold;");
		this.cccd.setMaxSize(400, 1);
		this.add(this.cccd, 1, 0, 2, 1);
		
		this.canhan = new RadioButton("Ca nhan");
		this.hogiadinh = new RadioButton("Ca gia dinh");
		
		ToggleGroup group3 = new ToggleGroup();
		this.canhan.setToggleGroup(group3);
		this.hogiadinh.setToggleGroup(group3);
		
		this.add(this.canhan, 1, 2, 1, 1);
		this.add(this.hogiadinh, 2, 2, 1, 1);
		
		ObservableList<String> f = FXCollections.observableArrayList("F0", "F1", "F2", "F3", "F4");
		this.mucdo = new ChoiceBox<String>(f);
		this.add(this.mucdo, 1, 4, 1, 1);
		
		ChangeListener<String> changeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				muc = newValue;
				
			}
	       };
	    this.mucdo.getSelectionModel().selectedItemProperty().addListener(changeListener);
		
		this.ngaybatdau = new DatePicker();
		this.ngaybatdau.setPromptText("mm-dd-yyyy");
		this.ngaybatdau.setStyle("-fx-font-weight: bold;");
		this.add(this.ngaybatdau, 3, 4, 1, 1);
		
		this.tainha = new RadioButton("Tai gia dinh");
		this.taptrung = new RadioButton("Noi tap trung");
		
		ToggleGroup group1 = new ToggleGroup();
		this.tainha.setToggleGroup(group1);
		this.taptrung.setToggleGroup(group1);
		
		this.add(this.tainha, 1, 6, 1, 1);
		this.add(this.taptrung, 2, 6, 1, 1);
		
		this.daxetnghiem = new RadioButton("Da xet nghiem");
		this.chuaxetnghiem = new RadioButton("Chua xet nghiem");
		
		ToggleGroup group2 = new ToggleGroup();
		this.daxetnghiem.setToggleGroup(group2);
		this.chuaxetnghiem.setToggleGroup(group2);
		
		this.add(this.daxetnghiem, 1, 8, 1, 1);
		this.add(this.chuaxetnghiem, 2, 8, 1, 1);
		
		this.tiepxuc = new CheckBox("Tiep xuc voi nhieu nguoi?");
		this.add(this.tiepxuc, 1, 10, 1, 1);
		
	}
	
	public boolean getTiepxuc()
	{
		return this.tiepxuc.isSelected();
	}
	
	public boolean getSuccess()
	{
		return this.success;
	}
	
	public String getCCCD()
	{
		return this.cccd.getText();
	}
}
