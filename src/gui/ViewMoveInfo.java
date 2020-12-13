package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.RecordInformation;
import entities.Movement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ViewMoveInfo extends GridPane{
	
	private TableView<Movement> table;
	private String noiden;
	private ObservableList<Movement> list;
	private int cnt;
	
	public ViewMoveInfo(String noiden)
	{
		super();
		this.noiden = noiden;
		this.cnt = 0;
		setMovementInfoPane();
	}
	
	private void setMovementInfoPane()
	{
		//this.setMinSize(1000.0, 400.0);
		this.setStyle("-fx-background-color: #f8efd4;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100.0);
		this.getColumnConstraints().add(column1);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100.0);
		this.getRowConstraints().add(row1);
		
		this.table = new TableView<Movement>();
		TableColumn<Movement, String> sttCol = new TableColumn<Movement, String>("STT");
		TableColumn<Movement, String> nameCol = new TableColumn<Movement, String>("Ho va Ten");
		TableColumn<Movement, String> addressCol = new TableColumn<Movement, String>("Dia Chi");
		TableColumn<Movement, String> timeCol = new TableColumn<Movement, String>("Thoi Gian Di");
		
		sttCol.setCellValueFactory(new PropertyValueFactory<>("Stt"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Ten"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("Diachi"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("Thoigian"));
		
		nameCol.setMinWidth(300);
		nameCol.setMaxWidth(300);
		
		sttCol.setMinWidth(80);
		sttCol.setMaxWidth(80);
		
		addressCol.setMinWidth(350);
		addressCol.setMaxWidth(350);
		
		timeCol.setMinWidth(300);
		timeCol.setMaxWidth(300);
		
		this.table.getColumns().addAll(sttCol, nameCol, addressCol, timeCol);
		this.table.setEditable(false);
		
		this.add(this.table, 0, 0, 1, 1);
		this.list = FXCollections.observableArrayList();
		getMoveList();
		this.table.setItems(this.list);
	}
	
	private void getMoveList() 
	{
		Movement move;
		RecordInformation getInfo = new RecordInformation();
		try {
			getInfo.query_change("select nk.ho_va_ten, hk.dia_chi, dc.ngay_di from quan_ly_di_chuyen as dc join thong_tin_nhan_khau as nk on dc.cccd = nk.cccd join thong_tin_ho_khau as hk on nk.id_ho_khau = hk.id_ho_khau where dc.vung_dich_di_qua like ? and datediff('d', dc.ngay_di, date()) <= 28;");
			getInfo.getPreStatement().setString(1, "%"+ this.noiden + "%");
			
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			while (rs.next()) {
				move = new Movement(this.noiden, "" + (++this.cnt));
				move.setTen(rs.getString("ho_va_ten"));
				//System.out.println(rs.getString("ho_va_ten"));
				move.setThoigian(rs.getString("ngay_di"));
				//System.out.println(rs.getString("ngay_di"));
				move.setDiachi(rs.getString("dia_chi"));
				this.list.add(move);
			}
			rs.close();
			getInfo.closeState();
			
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Khong the thuc hien yeu cau!");
	        alert.showAndWait();
	        return;
		}
	}
}
