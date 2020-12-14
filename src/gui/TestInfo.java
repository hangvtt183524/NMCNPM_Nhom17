package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import controller.RecordInformation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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

public class TestInfo extends GridPane implements Info{
	
	private Label name_label;
	private Label number_label;
	private Label time_label;
	private Label method_label;
	private Label result_label;
	
	private TextField cert;
	private Spinner number;
	private DatePicker time;
	private RadioButton method1, method2;
	private RadioButton result_pos, result_neg, result_non;
	private boolean success = false;
	
	public TestInfo()
	{
		super();
		setTestInfoPane();
	}
	
	private void setTestInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		column1.setPercentWidth(25);
		column2.setPercentWidth(25);
		column3.setPercentWidth(25);
		column4.setPercentWidth(25);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4);
		
		for (int i=0; i< 9; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/10);
        	this.getRowConstraints().add(rowConst);
        }
		
		this.setHgap(5);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		//this.setGridLinesVisible(true);
		
		this.add(setLabel(this.name_label, "CCCD"), 0, 0, 1, 1);
		this.add(setLabel(this.number_label, "Lần thứ"), 0, 2, 1, 1);
		this.add(setLabel(this.time_label, "Thời gian"), 0, 4, 1, 1);
		this.add(setLabel(this.method_label, "Phương pháp"), 0, 6, 1, 1);
		this.add(setLabel(this.result_label, "Kết quả"), 0, 8, 1, 1);
		
		this.cert = new TextField();
		this.cert.setMaxSize(400, 1);
		this.add(this.cert, 1, 0, 2, 1);
		
		this.number = new Spinner(1, 10, 1);
		this.add(this.number, 1, 2, 1, 1);
		
		this.time = new DatePicker();
		this.time.setPromptText("mm-dd-yyyy");
		this.add(this.time, 1, 4, 1, 1);
		
		this.method1 = new RadioButton("Test Nhanh");
		this.method2 = new RadioButton("PCR");
		ToggleGroup group_method = new ToggleGroup();
		method1.setToggleGroup(group_method);
		method2.setToggleGroup(group_method);
		this.add(this.method1, 1, 6, 1, 1);
		this.add(this.method2, 2, 6, 1, 1);
		
		this.result_neg = new RadioButton("Âm Tính");
		this.result_pos = new RadioButton("Dương Tính");
		this.result_non = new RadioButton("Chưa có kết quả");
		ToggleGroup group_result = new ToggleGroup();
		result_neg.setToggleGroup(group_result);
		result_pos.setToggleGroup(group_result);
		result_non.setToggleGroup(group_result);
		this.add(this.result_neg, 1, 8, 1, 1);
		this.add(this.result_pos, 2, 8, 1, 1);
		this.add(this.result_non, 3, 8, 1, 1);
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

	@Override
	public void saveInfo(String s) {
		if (this.cert.getText() == null || this.cert.getText().equals("") || this.time.getValue() == null || (!this.method1.isSelected() && !this.method2.isSelected()) || (!this.result_neg.isSelected() && !this.result_non.isSelected() && !this.result_pos.isSelected()))
		{
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notification!");
	        alert.setContentText("Hãy điền đầy đủ thông tin trước khi lưu!");
	        alert.showAndWait();
	        
	        return;
		}
		
		RecordInformation saveInfo = new RecordInformation();
		ResultSet rs;
		try {
			saveInfo.query_change("select lan_thu from quan_ly_kiem_tra where cccd = ?;");
			saveInfo.getPreStatement().setString(1, this.cert.getText());
			
			rs = saveInfo.getPreStatement().executeQuery();
			if (rs.next()) {
				if (rs.getInt("lan_thu") >= (int) this.number.getValue()) {
					Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Message!");
			        alert.setContentText("Bạn đã được kiểm tra " + rs.getInt("lan_thu") + " lần. Hãy nhập số lớn hơn!");
			        alert.showAndWait();
			        return;
				}
				else if (rs.getInt("lan_thu") < (int) this.number.getValue() - 1){
					Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Message!");
			        alert.setContentText("Bạn mới được kiểm tra " + rs.getInt("lan_thu") + " lần. Hãy nhập số tiếp theo!");
			        alert.showAndWait();
			        return;
				}
				else {
					saveInfo.query_change("insert into quan_ly_kiem_tra (cccd, thoi_gian, hinh_thuc, ket_qua, lan_thu) values (?, ?, ?, ?, ?);");
					saveInfo.getPreStatement().setString(1, this.cert.getText());
					saveInfo.getPreStatement().setDate(2, new Date(this.time.getValue().getMonthValue(), this.time.getValue().getDayOfMonth(), this.time.getValue().getYear()));
					if (this.method1.isSelected()) saveInfo.getPreStatement().setString(3, "Test Nhanh");
					else saveInfo.getPreStatement().setString(3, "PCR");
					
					if (this.result_neg.isSelected()) saveInfo.getPreStatement().setString(4, "Âm Tính");
					else if (this.result_pos.isSelected()) saveInfo.getPreStatement().setString(4, "Dương Tính");
					else saveInfo.getPreStatement().setString(4, "Chưa Xác Nhận");
					
					saveInfo.getPreStatement().setInt(5, (int)this.number.getValue());
					saveInfo.getPreStatement().executeUpdate();

				}
			}
			else {
				saveInfo.query_change("insert into quan_ly_kiem_tra (cccd, thoi_gian, hinh_thuc, ket_qua, lan_thu) values (?, ?, ?, ?, ?);");
				saveInfo.getPreStatement().setString(1, this.cert.getText());
				saveInfo.getPreStatement().setDate(2, new Date(this.time.getValue().getMonthValue(), this.time.getValue().getDayOfMonth(), this.time.getValue().getYear()));
				if (this.method1.isSelected()) saveInfo.getPreStatement().setString(3, "Test Nhanh");
				else saveInfo.getPreStatement().setString(3, "PCR");
				
				if (this.result_neg.isSelected()) saveInfo.getPreStatement().setString(4, "Âm Tính");
				else if (this.result_pos.isSelected()) saveInfo.getPreStatement().setString(4, "Dương Tính");
				else saveInfo.getPreStatement().setString(4, "Chưa Xác Nhận");
				
				saveInfo.getPreStatement().setInt(5, 1);
				saveInfo.getPreStatement().executeUpdate();
				
			}
			rs.close();
			saveInfo.closeState();
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Không thể thực hiện yêu cầu!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return;
		}
		
	}
	public boolean getSuccess()
	{
		return this.success;
	}
}