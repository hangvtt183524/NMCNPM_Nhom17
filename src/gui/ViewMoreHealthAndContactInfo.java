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

public class ViewMoreHealthAndContactInfo extends GridPane implements Info{
	private TableView<Contact> contact;
	private ObservableList<Contact> list_contact;
	
	private TableView<Health> health;
	private ObservableList<Health> list_health;
	
	private int cnt_contact;
	private String source_cccd;
	@Override
	public Label setLabel(Label label, String s) {
		
		return label;
	}

	@Override
	public void saveInfo(String s) {
		
		
	}
	
	public ViewMoreHealthAndContactInfo(String cccd)
	{
		super();
		this.cnt_contact = 0;
		this.source_cccd = cccd;
	}
	
	private void setViewIsolateInfoPane()
	{
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50.0);
		
		ColumnConstraints column2 = new ColumnConstraints();
		column1.setPercentWidth(25.0);
		
		ColumnConstraints column3 = new ColumnConstraints();
		column1.setPercentWidth(25.0);
		this.getColumnConstraints().addAll(column1, column2, column3);
		
		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(80.0);
		
		RowConstraints row1 = new RowConstraints();
		row0.setPercentHeight(20.0);
		this.getRowConstraints().addAll(row0, row1);
		
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
	      
	    this.list_contact = FXCollections.observableArrayList();
	    getContactList();
	    this.contact.setItems(this.list_contact);
	    this.add(this.contact, 1, 1, 2, 1);
	      
	    getContactList();
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
			}
			rs.close();
			getInfo.closeState();
		}
		catch(SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error!");
	        alert.setContentText("Khong the thuc hien yeu cau!");
	        alert.showAndWait();
	        return;
		}
		this.list_contact.add(contactor);
	}
}
