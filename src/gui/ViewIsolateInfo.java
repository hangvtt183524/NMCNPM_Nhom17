package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.RecordInformation;
import entities.Contact;
import entities.Isolation;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class ViewIsolateInfo extends GridPane implements Info{
	private TableView<Isolation> table;
	private ObservableList<Isolation> list;
	
	private Label dangcachly;
	private ViewMoreHealthAndContactInfo more;
	
	private int cnt;
	private String curr_cccd;
	private Button done;
	private boolean success;
	@Override
	public Label setLabel(Label label, String s) {
		label.setFont(new Font("Aria", 25));
		label.setStyle("-fx-background-color: #f8efd4;");
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(1500.0, 10.0);
		return label;
	}

	@Override
	public void saveInfo(String s) {
		
	}
	public ViewIsolateInfo()
	{
		super();
		this.success = false;
		this.cnt = 0;
		this.curr_cccd = null;
		setViewIsolateInfoPane();
	}
	
	private void setViewIsolateInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;");
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(85.0);
		this.getColumnConstraints().add(column1);
		
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(15.0);
		this.getColumnConstraints().add(column2);
		
		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(10);
		this.getRowConstraints().add(row0);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(80);
		this.getRowConstraints().add(row1);
		
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(10);
		this.getRowConstraints().add(row2);
		
		this.setHgap(5);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		//this.setGridLinesVisible(true);
		
		this.dangcachly = new Label("Những người đang trong giai đoạn cách ly");
		this.add(setLabel(this.dangcachly, "Những người đang trong giai đoạn cách ly"), 0, 0, 2, 1);
		
		this.table = new TableView<Isolation>();
		TableColumn<Isolation, String> sttCol = new TableColumn<Isolation, String>("STT");
		TableColumn<Isolation, String> nameCol = new TableColumn<Isolation, String>("Ho va Ten");
		TableColumn<Isolation, String> cccdCol = new TableColumn<Isolation, String>("CCCD");
		TableColumn<Isolation, String> mucCol = new TableColumn<Isolation, String>("Muc do");
		TableColumn<Isolation, String> diadiemCol = new TableColumn<Isolation, String>("Dia diem");
		TableColumn<Isolation, String> ngayCol = new TableColumn<Isolation, String>("Ngay bat dau");
		TableColumn<Isolation, Boolean> xetnghiemCol = new TableColumn<Isolation, Boolean>("Da xet nghiem");
		TableColumn<Isolation, Boolean> hethanCol = new TableColumn<Isolation, Boolean>("Da het han");
		TableColumn<Isolation, String> moreCol = new TableColumn<Isolation, String>("");
		
		sttCol.setCellValueFactory(new PropertyValueFactory<>("STT"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		cccdCol.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
		mucCol.setCellValueFactory(new PropertyValueFactory<>("Level"));
		diadiemCol.setCellValueFactory(new PropertyValueFactory<>("Place"));
		ngayCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
		xetnghiemCol.setCellValueFactory(new PropertyValueFactory<>("Test"));
		hethanCol.setCellValueFactory(new PropertyValueFactory<>("OutDate"));
		moreCol.setCellValueFactory(new PropertyValueFactory<>("More"));
		
		sttCol.setMinWidth(38);
		sttCol.setMaxWidth(38);
		
		nameCol.setMinWidth(150);
		nameCol.setMaxWidth(150);
		
		cccdCol.setMinWidth(120);
		cccdCol.setMaxWidth(120);
		
		mucCol.setMinWidth(80);
		mucCol.setMaxWidth(80);
		
		diadiemCol.setMinWidth(240);
		diadiemCol.setMaxWidth(240);
		
		ngayCol.setMinWidth(150);
		ngayCol.setMaxWidth(150);
		
		xetnghiemCol.setMinWidth(110);
		xetnghiemCol.setMaxWidth(110);
		
		hethanCol.setMinWidth(110);
		hethanCol.setMaxWidth(110);
		
		moreCol.setMinWidth(65);
		moreCol.setMaxWidth(65);
		
		
		this.table.getColumns().addAll(sttCol, nameCol, cccdCol, mucCol, diadiemCol, ngayCol, xetnghiemCol, hethanCol, moreCol);
		this.table.setEditable(true);
		
		// bat su kien thay doi trang thai da xet nghiem hay chua
		xetnghiemCol.setCellValueFactory(new Callback<CellDataFeatures<Isolation, Boolean>, ObservableValue<Boolean>>() {
	    	  
	           @Override
	           public ObservableValue<Boolean> call(CellDataFeatures<Isolation, Boolean> param) {
	               Isolation iso = param.getValue();
	 
	               SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(iso.getTest());
	               booleanProp.addListener(new ChangeListener<Boolean>() {
	 
	                   @Override
	                   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	                       iso.setTest(newValue);
	                   }
	               });
	               return booleanProp;
	           }
	       });
	 
		xetnghiemCol.setCellFactory(new Callback<TableColumn<Isolation, Boolean>, TableCell<Isolation, Boolean>>() {
	           @Override
	           public TableCell<Isolation, Boolean> call(TableColumn<Isolation, Boolean> p) {
	               CheckBoxTableCell<Isolation, Boolean> cell = new CheckBoxTableCell<Isolation, Boolean>();
	               cell.setAlignment(Pos.CENTER);
	               return cell;
	           }
	       });
		
		// bat su kien thay doi trang thai da het han cach ly hay chua
		hethanCol.setCellValueFactory(new Callback<CellDataFeatures<Isolation, Boolean>, ObservableValue<Boolean>>() {
			    	  
			           @Override
			           public ObservableValue<Boolean> call(CellDataFeatures<Isolation, Boolean> param) {
			               Isolation iso = param.getValue();
			 
			               SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(iso.getOutDate());
			               booleanProp.addListener(new ChangeListener<Boolean>() {
			 
			                   @Override
			                   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			                       iso.setOutDate(newValue);;
			                   }
			               });
			               return booleanProp;
			           }
			       });
			 
		hethanCol.setCellFactory(new Callback<TableColumn<Isolation, Boolean>, TableCell<Isolation, Boolean>>() {
			           @Override
			           public TableCell<Isolation, Boolean> call(TableColumn<Isolation, Boolean> p) {
			               CheckBoxTableCell<Isolation, Boolean> cell = new CheckBoxTableCell<Isolation, Boolean>();
			               cell.setAlignment(Pos.CENTER);
			               return cell;
			           }
			       });
				
				
		this.list = FXCollections.observableArrayList();
		Isolation check = new Isolation("1");
		getIsolationList();
		this.table.setItems(this.list);
		setMoreButtonEventHandle();
	    this.add(this.table, 0, 1, 2, 2);
	    
		this.done = new Button("OK");
		this.done.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		this.done.setMaxSize(100.0, 35.0);
		setDoneButtonEventHandle();
	}
	
	private void getTableInfo()
	{
		this.add(this.table, 0, 1, 2, 2);
	}
	
	private void getIsolationList()
	{
		Isolation iso = null;
		RecordInformation getInfo = new RecordInformation();
		
		try {
			getInfo.query_change("select nk.ho_va_ten, nk.cccd, cl.muc_do, cl.dia_diem, cl.ngay_bat_dau, cl.da_xet_nghiem, cl.da_het_han from quan_ly_cach_ly as cl join thong_tin_nhan_khau as nk on cl.cccd = nk.cccd where datediff('d', cl.ngay_bat_dau, Date()) <=28 or cl.da_het_han = False;");
			//getInfo.query_change("select * from quan_ly_cach_ly;");
			ResultSet rs = getInfo.getPreStatement().executeQuery();
			
			while (rs.next()) {
				iso = new Isolation("" + (++this.cnt));
				iso.setName(rs.getString("ho_va_ten"));
				//System.out.println(rs.getString("ho_va_ten"));
				iso.setCCCD(rs.getString("cccd"));
				iso.setLevel(rs.getString("muc_do"));
				iso.setPlace(rs.getString("dia_diem"));
				iso.setTime(rs.getString("ngay_bat_dau"));
				iso.setTest(rs.getBoolean("da_xet_nghiem"));
				iso.setOutDate(rs.getBoolean("da_het_han"));
				
				this.list.add(iso);
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
	
	private void getMoreInfo(String cccd)
	{
		this.more = new ViewMoreHealthAndContactInfo(cccd);
		this.getChildren().remove(this.table);
		this.dangcachly.setText("Thông tin về sức khỏe và các trường hợp tiếp xúc");
		this.add(this.more, 0, 1, 2, 1);
		this.add(this.done, 1, 2, 1, 1);
	}
	
	private void removeTable(Object o) 
	{
		if (this.getChildren().contains(o))
			this.getChildren().remove(o);
	}
	
	private void setMoreButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   getMoreInfo(curr_cccd);
			   } 
			};
		
		for (int i=0; i<this.list.size(); i++) {
			curr_cccd = this.list.get(i).getCCCD();
			this.list.get(i).getMore().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		}
	}
	
	private void setDoneButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   removeTable(more);
				   dangcachly.setText("Những người đang trong giai đoạn cách ly");
				   removeTable(done);
				   getTableInfo();
			   } 
			};
		this.done.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	public boolean getSuccess()
	{
		return this.success;
	}
}

	
}
