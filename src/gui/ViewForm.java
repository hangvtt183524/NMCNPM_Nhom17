package gui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class ViewForm extends FormFunction{

	public ViewForm()
	{
		super();
		setViewForm();
	}
	
	private void setViewForm()
	{
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		ColumnConstraints column4 = new ColumnConstraints();
		ColumnConstraints column5 = new ColumnConstraints();
		column1.setPercentWidth(12);
		column2.setPercentWidth(35);
		column3.setPercentWidth(28);
		column4.setPercentWidth(15);
		column5.setPercentWidth(10);
		
		
		this.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
		
		for (int i=0; i< 20; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/20);
        	this.getRowConstraints().add(rowConst);
        }
		this.setGridLinesVisible(true);
	}
}
