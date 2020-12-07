package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class MemberInfo extends GridPane{
	
	private Label stt;
	private Label name;
	private Label dateOfBirth;
	private Label cert;
	private Label gender;
	
	private List<Label> stt_mem;
	private List<TextField> name_mem;
	private List<DatePicker> dateOfBirth_mem;
	private List<TextField> cert_mem;
	private List<ToggleGroup> gender_mem;
	
	private int number;
	
	public MemberInfo(int numberOfMember)
	{
		super();
		
		this.number = numberOfMember;
		
		this.stt_mem = new ArrayList<Label>(numberOfMember);
		this.name_mem = new ArrayList<TextField>(numberOfMember);
		this.dateOfBirth_mem = new ArrayList<DatePicker>(numberOfMember);
		this.cert_mem = new ArrayList<TextField>(numberOfMember);
		this.gender_mem = new ArrayList<ToggleGroup>(numberOfMember);
		
		setMemberInfoPane();
	}
	
	private void setMemberInfoPane()
	{
		this.setHgap(5);
		this.setVgap(10);
		//this.setGridLinesVisible(true);
		
		this.setStyle("-fx-background-color: #ffe0d8");
		
		for (int i=0; i< this.number + 1; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/(this.number + 1));
        	this.getRowConstraints().add(rowConst);
        }
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		ColumnConstraints column6 = new ColumnConstraints();
		column1.setPercentWidth(9);
		column2.setPercentWidth(30);
		column3.setPercentWidth(18);
		column4.setPercentWidth(25);
		column5.setPercentWidth(9);
		column6.setPercentWidth(9);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);
		this.setAlignment(Pos.CENTER);
		
		this.add(setLabel(this.stt, "STT"), 0, 0, 1, 1);
		this.add(setLabel(this.name, "Name"), 1, 0, 1, 1);
		this.add(setLabel(this.dateOfBirth, "Date of birth"), 2, 0, 1, 1);
		this.add(setLabel(this.cert, "Cert"), 3, 0, 1, 1);
		this.add(setLabel(this.gender, "Gender"), 4, 0, 1, 1);
		
		setSttLabel();
		setNameTextField();
		setDate();
		setCertTextField();
		setGenderToggle();
	}
	
	private Label setLabel(Label label, String s)
	{
		label = new Label(s);
		
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		label.setStyle("-fx-background-color: #ffe0d8;");
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		return label;
	}
	
	private void setDate()
	{
		int pos = 1;
		this.dateOfBirth_mem = new ArrayList<DatePicker>(this.number);
		
		for (int i=0; i< this.number; i++) {
			DatePicker date = new DatePicker();
			date.setPromptText("dd-MM-yyyy");
			this.dateOfBirth_mem.add(date);
		}
		for (int i=0; i< this.number; i++) {
			this.add(this.dateOfBirth_mem.get(i), 2, pos++, 1, 1);
		}
	}
	
	private void setSttLabel()
	{
		int pos = 1;
		this.stt_mem = new ArrayList<Label>(this.number);
		/*
		
		this.cert_mem = new ArrayList<TextField>(this.number);
		this.gender_mem = new ArrayList<CheckBox>(this.number);*/
		
		for (int i=1; i<= this.number; i++) {
			this.stt_mem.add(setLabel(new Label(), ""+ i));
		}
		
		for (int i=0; i< this.number; i++) {
			this.add(this.stt_mem.get(i), 0, pos++, 1, 1);
		}
	}
	
	private void setNameTextField()
	{
		int pos = 1;
		this.name_mem = new ArrayList<TextField>(this.number);
		
		int i;
		for (i=0; i<this.number; i++) {
			TextField name = new TextField();
			name.setMaxSize(325, 35.0);
			this.name_mem.add(name);
		}
		
		for (i=0; i<this.number; i++) {
			this.add(this.name_mem.get(i), 1, pos++, 1, 1);
		}
	}
	
	private void setCertTextField()
	{
		int pos = 1;
		this.cert_mem = new ArrayList<TextField>(this.number);
		
		for (int i=0; i< this.number; i++) {
			TextField cert = new TextField();
			cert.setMaxSize(300, 35.0);
			
			this.cert_mem.add(cert);
		}
		for (int i=0; i<this.number; i++) {
			this.add(this.cert_mem.get(i), 3, pos++, 1, 1); 
		}
	}
	
	private void setGenderToggle()
	{
		int pos = 1;
		this.gender_mem = new ArrayList<ToggleGroup>(this.number);
		
		for (int i=0; i< this.number; i++) {
			ToggleGroup group = new ToggleGroup();
			
			RadioButton button1 = new RadioButton("Male");
	        button1.setToggleGroup(group);
	 
	        RadioButton button2 = new RadioButton("Female");
	        button2.setToggleGroup(group);
	        
	        this.gender_mem.add(group);
	        
	        this.add(button1, 4, pos, 1, 1);
	        this.add(button2, 5, pos, 1, 1);
	        pos++;
		}
		
	}
}

