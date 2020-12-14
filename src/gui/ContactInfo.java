package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.RecordInformation;
import entities.Contact;
import entities.Person;
import entities.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class ContactInfo extends GridPane implements Info{
	
	private TableView<Contact> contact;
	private ObservableList<Contact> list;
	private int cnt;
	private boolean success = false;
	public ContactInfo()
	{
		super();
		this.cnt = 0;
		setContactInfoPane();
	}
	
	private void setContactInfoPane()
	{
		this.setStyle("-fx-background-color: #f8efd4;");
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100.0);
		this.getColumnConstraints().add(column1);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100.0);
		this.getRowConstraints().add(row1);
		this.contact = new TableView<Contact>();
		TableColumn<Contact, String> sttCol = new TableColumn<Contact, String>("STT");
		TableColumn<Contact, String> nameCol = new TableColumn<Contact, String>("Họ và Tên");
		TableColumn<Contact, String> addressCol = new TableColumn<Contact, String>("Địa Chỉ");
		TableColumn<Contact, String> nextCol = new TableColumn<Contact, String>("");
		
		sttCol.setCellValueFactory(new PropertyValueFactory<>("STT"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
		nextCol.setCellValueFactory(new PropertyValueFactory<>("Next"));
		
		sttCol.setStyle("-fx-alignment: center;");
		nameCol.setStyle("-fx-alignment: center;");
		addressCol.setStyle("-fx-alignment: center;");
		nextCol.setStyle("-fx-alignment: center;");
		
		nameCol.setMinWidth(300);
		nameCol.setMaxWidth(300);
		
		addressCol.setMinWidth(350);
		addressCol.setMaxWidth(350);
		
		sttCol.setMinWidth(80);
		sttCol.setMaxWidth(80);
		
		nextCol.setMinWidth(250);
		nextCol.setMaxWidth(250);
		
		
		this.contact.getColumns().addAll(sttCol, nameCol, addressCol, nextCol);
		this.contact.setEditable(true);
		
		// bat su kien nhap ten 
	      nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
	      nameCol.setOnEditCommit(e -> {
	    	  e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
	      });
	      
	      // bat su kien nhap dia chi
	      addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
	      addressCol.setOnEditCommit(e -> {
	    	  e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
	      });
	      
	      this.list = FXCollections.observableArrayList();
	      getUserList();
	      this.contact.setItems(this.list);
	      this.add(this.contact, 0, 0, 1, 1);
	}
	@Override
	public Label setLabel(Label label, String s) {
		label = new Label(s);
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);

		label.setPrefSize(300.0, 75.0);
		return label;
	}
	
	private void getUserList() {
		  Contact contactor = new Contact(""+ (++this.cnt));
		  this.list.add(contactor);
		  setNextButtonEventHandle();
	  }
	
	private void setNextButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   getUserList();
				   ((Button)e.getSource()).setDisable(true);
			   } 
			};
		this.list.get(this.list.size() - 1).getNext().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	public boolean checkValid()
	{
		for (int i=0; i< this.cnt; i++) {
			if (this.list.get(i) == null || this.list.get(i).getName() == null || this.list.get(i).getAddress() == null || this.list.get(i).getName().equals("") || this.list.get(i).getAddress().equals("")) {
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Notification!");
		        alert.setContentText("Điền đầy đủ thông tin trước khi lưu!");
		        alert.showAndWait();
		        return false;
			}
		}
		this.success = true;
		return getSuccess();
	}
	
	public boolean getSuccess()
	{
		return this.success;
	}

	@Override
	public void saveInfo(String s) {
		RecordInformation saveInfo = new RecordInformation();
		try {
		for (int i=0; i< this.cnt; i++) {
				saveInfo.query_change("insert into quan_ly_tiep_xuc (ho_va_ten, noi_tiep_xuc, id_nguon) values (?, ?, ?);");
				saveInfo.getPreStatement().setString(1, this.list.get(i).getName());
				saveInfo.getPreStatement().setString(2, this.list.get(i).getAddress());
				saveInfo.getPreStatement().setString(3, s);
				
				saveInfo.getPreStatement().executeUpdate();
				this.success = true;

				//System.out.println(Integer.parseInt(s));
			}
		saveInfo.closeState();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}