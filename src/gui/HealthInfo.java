package gui;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import controller.RecordInformation;

public class HealthInfo extends GridPane implements Info{
	
	private Label ten_label;
	private Label tinhtrang_label;
	private Label bieuhien_label;
	private Label ngay;
	
	private TextField cert;
	private RadioButton tinhtrang_bth, tinhtrang_kbth;
	private TextArea bieuhien;
	private DatePicker ngayghinhan;
	
	private boolean success = false;
	
	public HealthInfo()
	{
		super();
		setHealthInfoPane();
	}
	
	private void setHealthInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;"
				+ "-fx-position: relative;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		column1.setPercentWidth(25);
		column2.setPercentWidth(35);
		column3.setPercentWidth(35);
		column4.setPercentWidth(5);

		this.getColumnConstraints().addAll(column1, column2, column3, column4);
		
		for (int i=0; i< 9; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/10);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(10);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		//this.setGridLinesVisible(true);
		
		this.add(setLabel(this.ten_label, "CCCD"), 0, 0, 1, 1);
		this.add(setLabel(this.tinhtrang_label, "Tinh Trang"), 0, 2, 1, 1);
		this.add(setLabel(this.bieuhien_label, "Bieu Hien"), 0, 4, 1, 1);
		this.add(setLabel(this.ngay, "Ngay Ghi Nhan"), 0, 8, 1, 1);
		
		this.cert = new TextField();
		this.cert.setStyle("-fx-font-weight: bold;");
		this.cert.setMaxSize(400, 1);
		this.add(this.cert, 1, 0, 2, 1);
		
		ToggleGroup group = new ToggleGroup();
		
		this.tinhtrang_bth = new RadioButton("Binh Thuong");
		this.tinhtrang_bth.setStyle("-fx-font-weight: bold;");
		this.tinhtrang_bth.setToggleGroup(group);
 
		tinhtrang_kbth = new RadioButton("Bat Thuong");
		this.tinhtrang_kbth.setStyle("-fx-font-weight: bold;");
		tinhtrang_kbth.setToggleGroup(group);
		
		this.add(this.tinhtrang_bth, 1, 2, 1, 1);
		this.add(this.tinhtrang_kbth, 2, 2, 1, 1);
		
		this.bieuhien = new TextArea();
		this.bieuhien.setStyle("-fx-font-weight: bold;");
		this.setMaxSize(980.5, 355.5);
		this.add(this.bieuhien, 1, 4, 2, 3);
		
		this.ngayghinhan = new DatePicker();
		this.ngayghinhan.setStyle("-fx-font-weight: bold;");
		this.ngayghinhan.setPromptText("dd-MM-yyyy");
		this.add(this.ngayghinhan, 1, 8, 1, 1);
	}

	@Override
	public Label setLabel(Label label, String s)
	{
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		return label;
	}
	@Override
	public void saveInfo(String s) {
		if (this.cert == null || this.cert.getText() == null ||(!this.tinhtrang_bth.isSelected() && !this.tinhtrang_kbth.isSelected()) || this.ngayghinhan == null || this.ngayghinhan.getValue() == null){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification!");
			alert.setContentText("Hay dien day du cac thong tin truoc khi luu!");
			alert.showAndWait();
			return;
		}
		if (this.tinhtrang_kbth.isSelected() && (this.bieuhien.getText() == null || this.bieuhien.getText().equals(""))){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification!");
			alert.setContentText("Cho biet thong tin suc khoe cu the!");
			alert.showAndWait();
			return;
		}
		RecordInformation saveInfo = new RecordInformation();
		String cccd;
		try {
			saveInfo.query_change("select cccd from thong_tin_nhan_khau where cccd = ?;");
			saveInfo.getPreStatement().setString(1, this.cert.getText());
			ResultSet rs = saveInfo.getPreStatement().executeQuery();
			if (!rs.next()) {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error!");
				alert.setContentText("Ban chua dang ki thong tin vao he thong!");
				alert.showAndWait();
				return;
			}
			saveInfo.query_change("insert into quan_ly_suc_khoe (cccd, tinh_trang_suc_khoe, bieu_hien, ngay_ghi_nhan) values (?, ?, ?, ?);");
			saveInfo.getPreStatement().setString(1, this.cert.getText());
			if (this.tinhtrang_bth.isSelected()) {
				saveInfo.getPreStatement().setString(2, "binh thuong");
				saveInfo.getPreStatement().setString(3, "none");
			}
			else{
				saveInfo.getPreStatement().setString(2, "bat thuong");
				saveInfo.getPreStatement().setString(3, this.bieuhien.getText());
			}
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = df.parse(this.ngayghinhan.getValue().getMonthValue()+"/"+this.ngayghinhan.getValue().getDayOfMonth() + "/" + this.ngayghinhan.getValue().getYear());
			saveInfo.getPreStatement().setTimestamp(4,new Timestamp(date.getTime()));
			//saveInfo.getPreStatement().setDate(4, new Date(this.ngayghinhan.getValue().getMonthValue(), this.ngayghinhan.getValue().getDayOfMonth(), this.ngayghinhan.getValue().getYear()));
			
			saveInfo.getPreStatement().executeUpdate();
			saveInfo.closeState();
			this.success = true;
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
			alert.setContentText("Khong the thuc hien yeu cau!");
			alert.showAndWait();
			return;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean getSuccess()
	{
		return this.success;
	}
}
