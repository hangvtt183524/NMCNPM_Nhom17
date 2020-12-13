package gui;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.RecordInformation;
import entities.Person;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MemberInfo extends GridPane implements Info{
	private TableView<Person> table;
	private ObservableList<Person> list;
	private int number;
	private String certChuHo;
	private String diaChi;
	private boolean success = false;

	@Override
	public Label setLabel(Label label, String s) {
		return label;
	}
	
	public MemberInfo(int number, String tenChuHo, String diaChi)
	{
		super();
		this.number = number;
		this.certChuHo = tenChuHo;
		this.diaChi = diaChi;
		setMemberInfoPane();
	}
	
	private void setMemberInfoPane()
	{
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100.0);
		this.getColumnConstraints().add(column1);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100.0);
		this.getRowConstraints().add(row1);
		
		this.table = new TableView<Person>();
	      TableColumn<Person, String> userNameCol   = new TableColumn<Person, String>("Name");
	      TableColumn<Person, String> dateOfBirthCol = new TableColumn<Person, String>("Date Of Birth");
	      TableColumn<Person, String> address = new TableColumn<Person, String>("Address");
	      TableColumn<Person, String> certCol = new TableColumn<Person, String>("Cert");      
	      TableColumn<Person, Boolean> genderCol = new TableColumn<Person, Boolean>("Female");
	      TableColumn<Person, String> orderCol = new TableColumn<Person, String>("Order");
	      
	      TableColumn<Person, String> dayCol  = new TableColumn<Person, String>("Day");
	      TableColumn<Person, String> monthCol  = new TableColumn<Person, String>("Month");
	      TableColumn<Person, String> yearCol  = new TableColumn<Person, String>("Year");
	      
	      dateOfBirthCol.getColumns().addAll(dayCol, monthCol, yearCol);
	  
	      userNameCol.setCellValueFactory(new PropertyValueFactory<>("FullName"));
	      dayCol.setCellValueFactory(new PropertyValueFactory<>("Day"));
	      monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
	      yearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
	      certCol.setCellValueFactory(new PropertyValueFactory<>("Cert"));
	      address.setCellValueFactory(new PropertyValueFactory<>("Address"));
	      genderCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
	      orderCol.setCellValueFactory(new PropertyValueFactory<>("Order"));
	      
	      userNameCol.setStyle("-fx-alignment: center;");
	      dayCol.setStyle("-fx-alignment: center;");
	      monthCol.setStyle("-fx-alignment: center;");
	      yearCol.setStyle("-fx-alignment: center;");
	      certCol.setStyle("-fx-alignment: center;");
	      address.setStyle("-fx-alignment: center;");
	      orderCol.setStyle("-fx-alignment: center;");
	      
	      orderCol.setMinWidth(50);
	      orderCol.setMaxWidth(50);
	      
	      // bat su kien thay doi FullName
	      userNameCol.setMinWidth(250);
	      userNameCol.setMaxWidth(250);
	      userNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
	      userNameCol.setOnEditCommit(e -> {
	    	  e.getTableView().getItems().get(e.getTablePosition().getRow()).setFullName(e.getNewValue());
	      });
	      
	      // bat su kien thay doi so CCCD
	      certCol.setMinWidth(190);
	      certCol.setMaxWidth(190);
	      certCol.setCellFactory(TextFieldTableCell.forTableColumn());
	      certCol.setOnEditCommit(e -> {
	    	  e.getTableView().getItems().get(e.getTablePosition().getRow()).setCert(e.getNewValue());
	      });
	      
	      // bat su kien thay doi dia chi
	      address.setMinWidth(300);
	      address.setMaxWidth(300);
	      address.setCellFactory(TextFieldTableCell.forTableColumn());
	      address.setOnEditCommit(e -> {
	    	  e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
	      });
	      
	      //bat su kien danh dau gioi tinh la nu
	      genderCol.setMinWidth(88);
	      genderCol.setMaxWidth(88);
	      genderCol.setCellValueFactory(new Callback<CellDataFeatures<Person, Boolean>, ObservableValue<Boolean>>() {
	    	  
	           @Override
	           public ObservableValue<Boolean> call(CellDataFeatures<Person, Boolean> param) {
	               Person person = param.getValue();
	 
	               SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.getGender());
	               booleanProp.addListener(new ChangeListener<Boolean>() {
	 
	                   @Override
	                   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	                       person.setGender(newValue);
	                   }
	               });
	               return booleanProp;
	           }
	       });
	 
	      genderCol.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
	           @Override
	           public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
	               CheckBoxTableCell<Person, Boolean> cell = new CheckBoxTableCell<Person, Boolean>();
	               cell.setAlignment(Pos.CENTER);
	               return cell;
	           }
	       });
	      
	      // bat su kien chon ngay
	      dayCol.setMinWidth(80);
	      dayCol.setMaxWidth(80);
	      ObservableList<String> dayList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
	       dayCol.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
	           @Override
	           public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
	               Person person = param.getValue();
	               String day = person.getDay();
	               return new SimpleObjectProperty<String>(day);
	           }
	       });
	       dayCol.setCellFactory(ComboBoxTableCell.forTableColumn(dayList));
	       dayCol.setOnEditCommit(e -> {
	    	   e.getTableView().getItems().get(e.getTablePosition().getRow()).setDay(e.getNewValue());
	       });
	       
	      // bat su kien chon thang
	       monthCol.setMinWidth(100);
	       monthCol.setMaxWidth(100);
	       ObservableList<String> monthList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	       monthCol.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
	           @Override
	           public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
	               Person person = param.getValue();
	               String month = person.getMonth();
	               return new SimpleObjectProperty<String>(month);
	           }
	       });
	       monthCol.setCellFactory(ComboBoxTableCell.forTableColumn(monthList));
	       monthCol.setOnEditCommit(e -> {
	    	   e.getTableView().getItems().get(e.getTablePosition().getRow()).setMonth(e.getNewValue());
	       });
	       
	       
	       // bat su kien nhap nam
	       yearCol.setMinWidth(100);
	       yearCol.setMaxWidth(100);
	       yearCol.setCellFactory(TextFieldTableCell.forTableColumn());
	       yearCol.setOnEditCommit(e -> {
		    	  e.getTableView().getItems().get(e.getTablePosition().getRow()).setYear(e.getNewValue());
		      });
	       
	       
	       table.getColumns().addAll(orderCol, userNameCol, dateOfBirthCol, address, certCol, genderCol);
		      table.setEditable(true);
		      // Hiển thị các dòng dữ liệu
		      this.list = FXCollections.observableArrayList();
		      getUserList();
		      table.setItems(list);
		      this.add(this.table, 0, 0, 1, 1);
	  }
	 
	private void getUserList() {
		  Person person;
	      
	      for (int i=0; i< this.number; i++) {
	    	  person = new Person(i+1, this.diaChi);
	    	  list.add(person);
	      }
	  }

	@Override
	public void saveInfo(String s) {
		RecordInformation saveInDB = new RecordInformation();
		if (this.list.get(0).getCert() != null && !this.list.get(0).getCert().equals(this.certChuHo)) {
			//System.out.println(this.list.get(0).getFullName());
			//System.out.println(this.certChuHo);
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notification!");
	        alert.setContentText("Chu ho phai la nguoi dau tien duoc liet ke trong danh sach.");
	        alert.showAndWait();
	        
	        try {
	        	saveInDB.query_change("delete * from thong_tin_ho_khau where cccd_chu_ho = ?;");
	        	saveInDB.getPreStatement().setString(1, this.certChuHo);
				saveInDB.getPreStatement().executeUpdate();
				
				saveInDB.closeState();
				this.success = false;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		else {
			for (int i=0; i<this.list.size(); i++) {
				Person p = this.list.get(i);
				if (p.getFullName() == null || p.getCert() == null || p.getMonth() == null || p.getDay() == null || p.getYear() == null || p.getGender() == null) {
					Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Notification!");
			        alert.setContentText("Hay dien day du cac thong tin truoc khi luu!");
			        alert.showAndWait();
			        
			        return;
				}
			}
			try {
				Person p;
				for (int i=0; i<this.list.size(); i++) {
					p = this.list.get(i);
					saveInDB.query_change("insert into thong_tin_nhan_khau (id_ho_khau, cccd, ho_va_ten, ngay_sinh, gioi_tinh_nu) values (?, ?, ?, ?, ?);");
					saveInDB.getPreStatement().setString(1, s);
					saveInDB.getPreStatement().setString(2, p.getCert());
					saveInDB.getPreStatement().setString(3, p.getFullName());
					
					SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					Date date = df.parse(p.getMonth()+"/"+p.getDay() + "/" + p.getYear());
					saveInDB.getPreStatement().setTimestamp(4,new Timestamp(date.getTime()));
					//saveInDB.getPreStatement().setDate(4, new Date(Integer.parseInt(p.getMonth()), Integer.parseInt(p.getDay()), Integer.parseInt(p.getYear())));
					saveInDB.getPreStatement().setBoolean(5, p.getGender());
					saveInDB.getPreStatement().executeUpdate();
				}
				
				saveInDB.closeState();
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Error!");
		        alert.setContentText("Khong the thuc hien yeu cau!");
		        alert.showAndWait();
		        //e.printStackTrace();
		        return;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean getSuccess()
	  {
		  return this.success;
	  }
	}
