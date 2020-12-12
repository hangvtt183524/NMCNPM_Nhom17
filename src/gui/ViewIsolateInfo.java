package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.RecordInformation;
import entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ViewIsolateInfo extends GridPane implements Info{

	@Override
	public Label setLabel(Label label, String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInfo(String s) {
		// TODO Auto-generated method stub
		
	}
	
}
