package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.RecordInformation;
import entities.Person;
import entities.Result;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class RecordInfo extends GridPane implements Info{
	private TableView<Result> record;
	private ObservableList<Result> list;
	private String query;
	private String cert;
	
	private int pos, neg, non;
	private Label notify;
	
	@Override
	public Label setLabel(Label label, String s) {
		label = new Label(s);
		label.setFont(new Font("Aria", 25));
		label.setStyle("-fx-background-color: #f8efd4;");
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(1500.0, 150.0);
		return label;
	}

	@Override
	public void saveInfo(String s) {
		// TODO Auto-generated method stub
		
	}
	
	public RecordInfo(String cert)
	{
		super();
		this.cert = cert;
		setRecordInfoPane();
	}
	
	public RecordInfo()
	{
		super();
		this.pos = 0;
		this.neg = 0;
		this.non = 0;
		setRecordInfoAllPane();
		
	}
	
	private void setRecordInfoAllPane()
	{
		this.setStyle("-fx-border-style: solid;"
				+ "-fx-border-color: #7579e7;"
				+ "-fx-border-width: 3;");
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100.0);
		this.getColumnConstraints().add(column1);
		
		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(10.0);
		this.getRowConstraints().add(row0);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(90.0);
		this.getRowConstraints().add(row1);
		
		this.record = new TableView<Result>();
		TableColumn<Result, String> nameCol = new TableColumn<Result, String>("Họ và Tên");
		TableColumn<Result, String> numberCol = new TableColumn<Result, String>("Lần Thứ");
		TableColumn<Result, String> timeCol = new TableColumn<Result, String>("Thời Gian");
		TableColumn<Result, String> resultCol = new TableColumn<Result, String>("Kết Quả");
		TableColumn<Result, String> methodCol = new TableColumn<Result, String>("Phương pháp");
		TableColumn<Result, String> updateCol = new TableColumn<Result, String>("Cập Nhật");
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		numberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
		methodCol.setCellValueFactory(new PropertyValueFactory<>("Method"));
		resultCol.setCellValueFactory(new PropertyValueFactory<>("Result"));
		updateCol.setCellValueFactory(new PropertyValueFactory<>("Update"));
		
		nameCol.setMinWidth(300);
		nameCol.setMaxWidth(300);
		
		numberCol.setMinWidth(150);
		numberCol.setMaxWidth(150);
		
		timeCol.setMinWidth(150);
		timeCol.setMaxWidth(150);
		
		methodCol.setMinWidth(150);
		methodCol.setMaxWidth(150);
		
		resultCol.setMinWidth(200);
		resultCol.setMaxWidth(200);
		
		updateCol.setMinWidth(100);
		updateCol.setMaxWidth(100);
		
		nameCol.setStyle("-fx-alignment: center;");
		numberCol.setStyle("-fx-alignment: center;");
		timeCol.setStyle("-fx-alignment: center;");
		methodCol.setStyle("-fx-alignment: center;");
		updateCol.setStyle("-fx-alignment: center;");
		
		// bat su kien thay doi ket qua (neu co)
		ObservableList<String> resultList = FXCollections.observableArrayList("Âm Tính", "Dương Tính", "Chưa có kết quả");
		resultCol.setCellValueFactory(new Callback<CellDataFeatures<Result, String>, ObservableValue<String>>() {
	           @Override
	           public ObservableValue<String> call(CellDataFeatures<Result, String> param) {
	               Result rec = param.getValue();
	               String result = rec.getResult();
	               return new SimpleObjectProperty<String>(result);
	           }
	       });
		resultCol.setCellFactory(ComboBoxTableCell.forTableColumn(resultList));
		resultCol.setOnEditCommit(e -> {
	    	   e.getTableView().getItems().get(e.getTablePosition().getRow()).setResult(e.getNewValue());
	       });
		
		record.getColumns().addAll(nameCol, numberCol, timeCol, methodCol, resultCol, updateCol);
		record.setEditable(true);
		
		this.list = FXCollections.observableArrayList();
		getRecordListAll();
		record.setItems(list);
		this.add(this.record, 0, 1, 1, 1);
		
	}
	
	private void setRecordInfoPane()
	{
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100.0);
		this.getColumnConstraints().add(column1);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100.0);
		this.getRowConstraints().add(row1);
		
		this.record = new TableView<Result>();
		TableColumn<Result, String> nameCol = new TableColumn<Result, String>("Họ và Tên");
		TableColumn<Result, String> numberCol = new TableColumn<Result, String>("Lần Thứ");
		TableColumn<Result, String> timeCol = new TableColumn<Result, String>("Thời Gian");
		TableColumn<Result, String> resultCol = new TableColumn<Result, String>("Kết Quả");
		TableColumn<Result, String> methodCol = new TableColumn<Result, String>("Phương Pháp");
		TableColumn<Result, String> updateCol = new TableColumn<Result, String>("Cập Nhật");
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		numberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
		methodCol.setCellValueFactory(new PropertyValueFactory<>("Method"));
		resultCol.setCellValueFactory(new PropertyValueFactory<>("Result"));
		updateCol.setCellValueFactory(new PropertyValueFactory<>("Update"));
		
		nameCol.setMinWidth(300);
		nameCol.setMaxWidth(300);
		
		numberCol.setMinWidth(150);
		numberCol.setMaxWidth(150);
		
		timeCol.setMinWidth(150);
		timeCol.setMaxWidth(150);
		
		methodCol.setMinWidth(150);
		methodCol.setMaxWidth(150);
		
		resultCol.setMinWidth(200);
		resultCol.setMaxWidth(200);
		
		updateCol.setMinWidth(100);
		updateCol.setMaxWidth(100);
		
		
		// bat su kien thay doi ket qua (neu co)
		ObservableList<String> resultList = FXCollections.observableArrayList("Âm Tính", "Dương Tính", "Chưa có kết quả");
		resultCol.setCellValueFactory(new Callback<CellDataFeatures<Result, String>, ObservableValue<String>>() {
	           @Override
	           public ObservableValue<String> call(CellDataFeatures<Result, String> param) {
	               Result rec = param.getValue();
	               String result = rec.getResult();
	               return new SimpleObjectProperty<String>(result);
	           }
	       });
		resultCol.setCellFactory(ComboBoxTableCell.forTableColumn(resultList));
		resultCol.setOnEditCommit(e -> {
	    	   e.getTableView().getItems().get(e.getTablePosition().getRow()).setResult(e.getNewValue());
	       });
		
		record.getColumns().addAll(nameCol, numberCol, timeCol, methodCol, resultCol, updateCol);
		record.setEditable(true);
		
		this.list = FXCollections.observableArrayList();
		getRecordList();
		record.setItems(list);
		this.add(this.record, 0, 0, 1, 1);
	}
	
	private void getRecordList() {
		Result res;
		String ten;
		
		RecordInformation getInfo = new RecordInformation();
		try {
			getInfo.query_change("select ho_va_ten from thong_tin_nhan_khau where cccd = ?;");
			getInfo.getPreStatement().setString(1, this.cert);
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			
			if (!rs.next()) {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Message!");
		        alert.setContentText("Bạn chưa đăng kí với hệ thống!");
		        alert.showAndWait();
		        return;
			}
			else {
				ten = rs.getString("ho_va_ten");
			}
			
			getInfo.query_change("select * from quan_ly_kiem_tra where cccd = ?;");
			getInfo.getPreStatement().setString(1, this.cert);
			rs = getInfo.getPreStatement().executeQuery();
			
			while(rs.next()) {
				res = new Result(this.cert, ten, rs.getString("lan_thu"), rs.getString("thoi_gian"), rs.getString("hinh_thuc"), rs.getString("ket_qua"));
				this.list.add(res);
			}
			rs.close();
			getInfo.closeState();
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Không thể thực hiện yêu cầu!");
	        alert.showAndWait();
	        return;
		}
	  }
	
	private void getRecordListAll()
	{
		Result res;
		RecordInformation getInfo = new RecordInformation();
		try {
			getInfo.query_change("select nk.cccd, nk.ho_va_ten, kt.lan_thu, kt.thoi_gian, kt.hinh_thuc, kt.ket_qua from quan_ly_kiem_tra as kt join thong_tin_nhan_khau as nk on kt.cccd = nk.cccd where datediff('d', kt.thoi_gian, Date()) <=7;");
			
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			
			while (rs.next()) {
				if (rs.getString("ket_qua").equals("Âm Tính")) this.neg++;
				else if (rs.getString("ket_qua").equals("Dương Tính")) this.pos++;
				else this.non++;
				
				res = new Result(rs.getString("cccd"), rs.getString("ho_va_ten"), rs.getString("lan_thu"), rs.getString("thoi_gian"), rs.getString("hinh_thuc"), rs.getString("ket_qua"));
				this.list.add(res);
			}
			
			this.add(setLabel(this.notify, "Dương tính: " + this.pos + " \t\tÂm tính: " + this.neg + " \tChưa có kết quả: " + this.non), 0, 0, 1, 1);
			
			rs.close();
			getInfo.closeState();
		}
		catch(SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Không thể thực hiện yêu cầu!");
	        alert.showAndWait();
	        e.printStackTrace();
	        return;
		}
	}
}