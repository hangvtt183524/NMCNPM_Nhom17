package gui;

import java.sql.ResultSet;

import entities.Human;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class FormFunction extends GridPane {
	protected TextArea searchText;
	protected Button saveBtn;
	protected Button searchBtn;
	protected Button addBtn;
	protected Button changeBtn;
	protected TableView<Human> ResultSet;
	
	public FormFunction() 
	{
		super();
	}
	
	public void save_event(String query) {
		
	}
	
	public void add_event() {
		
	}
	
	public void change_event(String query) {
		
	}
	
	public ResultSet search_event(String query) {
		return null;
	}

}
