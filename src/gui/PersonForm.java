package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.RecordInformation;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PersonForm extends FormFunction{
	
	private Label tenChuHo;
	private Label soThanhVien;
	private Label diachi;
	
	private TextField tenChuHo_text;
	private Spinner soThanhVien_spinner;
	private TextField diachi_text;
	
	private MemberInfo memberInfoPane;
	
	
	public PersonForm()
	{
		super();
		setPersonForm();
	}
	
	private void setPersonForm()
	{
		
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		column1.setPercentWidth(5);
		column2.setPercentWidth(12);
		column3.setPercentWidth(35);
		column4.setPercentWidth(28);
		column5.setPercentWidth(15);
		column6.setPercentWidth(5);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);
		
		for (int i=0; i< 20; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/20);
        	this.getRowConstraints().add(rowConst);
        }
		
		//.setGridLinesVisible(true);
		this.setColorLabel("Khai báo thông tin người dân");
		this.add(this.color, 0, 1, 6, 3);
		
		this.add(setLabel(this.tenChuHo, "CCCD Chủ Hộ"), 1, 5, 1, 1);
		this.add(setLabel(this.soThanhVien, "Số thành viên trong gia đình"), 3, 5, 1, 1);
		this.add(setLabel(this.diachi, "Địa chỉ"), 1, 6, 1, 1);
		
		this.tenChuHo_text = new TextField();
		//this.tenChuHo_text.setPrefColumnCount(1);
		this.tenChuHo_text.setMaxSize(300, 1);

		this.tenChuHo_text.setStyle("-fx-font-weight: bold;");
		//this.tenChuHo_text.setFont(new Font("Arial", 17));
		this.add(this.tenChuHo_text, 2, 5, 1, 1);
		
		this.diachi_text = new TextField();
		this.diachi_text.setStyle("-fx-font-weight: bold;");
		this.diachi_text.setMaxSize(400, 1);
		this.add(this.diachi_text, 2, 6, 1, 1);
		
		
		this.soThanhVien_spinner = new Spinner(1, 10, 1);
		this.soThanhVien_spinner.setEditable(true);
		//this.soThanhVien_spinner.setPrefSize(100, 4);
		this.soThanhVien_spinner.setMaxSize(100, 4);
		this.add(this.soThanhVien_spinner, 4, 5, 1, 1);
		
		this.addBtn = new Button("Add");
		this.addBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.addBtn.setPrefSize(70, 5);
		this.addBtn.setMaxSize(70, 5);
		this.add(this.addBtn, 3, 6, 1, 1);
		
		this.saveBtn = new Button("Lưu");
		
		setAddButtonEventHandle();
	}
	
	private void setSaveButton()
	{
		this.saveBtn = new Button("Lưu");
		this.saveBtn.setMaxSize(100.0, 35.0);
		this.saveBtn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.add(this.saveBtn, 4, 18, 1, 1);
		setSaveButtonEventHandle();
	}
	
	private void getMemberInfo()
	{
		this.memberInfoPane = new MemberInfo((int) this.soThanhVien_spinner.getValue(), this.tenChuHo_text.getText(), this.diachi_text.getText());
		this.add(this.memberInfoPane, 1, 8, 4, 10);
	}
	
	private Label setLabel(Label label, String s) 
	{
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER_LEFT);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		
		return label;
	}
	
	private void setAddButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   if (tenChuHo_text.getText() == null || tenChuHo_text.getText().equals("") || diachi_text.getText() == null || diachi_text.getText().equals("")) {
					   Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("Message!");
				        alert.setContentText("Bạn cần cung cấp đầy đủ thông tin!");
				        alert.showAndWait();
				        return;
				   }
				   getMemberInfo();
				   //saveBtn an di, khong nhin thay dc
				   setSaveButton();
				   addBtn.setDisable(true);
				   tenChuHo_text.setEditable(false);
				   soThanhVien_spinner.setEditable(false);
				   soThanhVien_spinner.setDisable(true);
				   diachi_text.setEditable(false);
			   } 
			};
		this.addBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	private void setSaveButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				  RecordInformation saveInfo = new RecordInformation();
				  String s = "";
				  try {
					  saveInfo.query_change("insert into thong_tin_ho_khau (cccd_chu_ho, dia_chi, so_thanh_vien) values (?, ?, ?);");
					  saveInfo.getPreStatement().setString(1, tenChuHo_text.getText());
					  saveInfo.getPreStatement().setString(2, diachi_text.getText());
					  saveInfo.getPreStatement().setInt(3, (int)soThanhVien_spinner.getValue());
					  saveInfo.getPreStatement().executeUpdate();
					  
					  saveInfo.query_change("select top 1 id_ho_khau from thong_tin_ho_khau order by id_ho_khau desc;");
					  ResultSet rs = saveInfo.getPreStatement().executeQuery();
					  
					  if (rs.next()) {
						  s = rs.getString("id_ho_khau");
					  }
					  saveInfo.closeState();
				  }
				  catch(SQLException ex) {
						Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("Error!");
				        alert.setContentText("Không thể thực hiện yêu cầu!");
				        alert.showAndWait();
				        return;
					  //ex.printStackTrace();
					}
				  memberInfoPane.saveInfo(s);
				  if (memberInfoPane.getSuccess()) {
					  saveBtn.setDisable(true);
					  tenChuHo_text.setEditable(true);
					  soThanhVien_spinner.setDisable(false);
					  soThanhVien_spinner.setEditable(true);
					  diachi_text.setEditable(true);
					  addBtn.setDisable(false);
				  }
			   } 
			};
		this.saveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
}