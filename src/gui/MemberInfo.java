package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private List<CheckBox> gender_mem;
	
	private int number;
	
	public MemberInfo(int numberOfMember)
	{
		super();
		
		this.number = numberOfMember;
		
		this.stt_mem = new ArrayList<Label>(numberOfMember);
		this.name_mem = new ArrayList<TextField>(numberOfMember);
		this.dateOfBirth_mem = new ArrayList<DatePicker>(numberOfMember);
		this.cert_mem = new ArrayList<TextField>(numberOfMember);
		this.gender_mem = new ArrayList<CheckBox>(numberOfMember);
		
		setMemberInfoPane();
	}
	
	private void setMemberInfoPane()
	{
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
		column1.setPercentWidth(7);
		column2.setPercentWidth(35);
		column3.setPercentWidth(15);
		column4.setPercentWidth(25);
		column5.setPercentWidth(18);
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
	}
	
	private Label setLabel(Label label, String s)
	{
		label = new Label(s);
		
		label.setFont(new Font("Aria", 23));
		label.setAlignment(Pos.CENTER);
		
		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		return label;
	}
	
	private DatePicker setDate(DatePicker date)
	{
		return date;
	}
}

