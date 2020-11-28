package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main_GUI extends Application{
	
	private Scene scene;
	private GridPane root;
	private GridPane navigate;
	private GridPane function;
	
	
	private Button person;
	private Button health;
	private Button movement;
	private Button isolation;
	private Button test;
	private Button view;
	private Button logout;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		setNavigatePane();
		setFunctionPane();
		setRootPane();
		
		Scene scene = new Scene(root, 750, 550);
		 
        primaryStage.setTitle("FlowPane Layout Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
	// design main pane
	private void setRootPane()
	{
		this.root = new GridPane();

        for (int i = 0; i < 5; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 5);
            this.root.getColumnConstraints().add(colConst);
        }
        
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPercentHeight(100.0);
        this.root.getRowConstraints().add(rowConst);
        
        this.root.add(this.navigate, 0, 0, 1, 1);
        this.root.add(this.function, 1, 0, 4, 1);
        
	}
	
	// design navigate pane
	private void setNavigatePane()
	{
        this.navigate = new GridPane();
        
        this.navigate.setGridLinesVisible(true);
        
        for (int i=0; i< 10; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100.0/10);
        	this.navigate.getRowConstraints().add(rowConst);
        }

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0);
        this.navigate.getColumnConstraints().add(colConst);
        
        this.person = new Button("Person");
        this.health = new Button("Health");
        this.isolation = new Button("Isolation");
        this.movement = new Button("Movement");
        this.test = new Button("Test");
        this.view = new Button("View");
        
        this.navigate.add(setButton(this.person), 0, 1, 1, 1);
        this.navigate.add(setButton(this.movement), 0, 2, 1, 1);
        this.navigate.add(setButton(this.health), 0, 3, 1, 1);
        this.navigate.add(setButton(this.isolation), 0, 4, 1, 1);
        this.navigate.add(setButton(this.test), 0, 5, 1, 1);
        this.navigate.add(setButton(this.view), 0, 6, 1, 1);
        //this.navigate.add(child, columnIndex, rowIndex);
        
	}
	
	// design function pane
	private void setFunctionPane()
	{
		this.function = new GridPane();
		//this.function.setStyle("-fx-background-color: violet;");
	}
	
	private Button setButton(Button button)
	{
		button.setFont(new Font("Aria", 25));
		button.setStyle("-fx-background-color: #3f4d71;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		button.setAlignment(Pos.CENTER);

		button.setMaxSize(390.0, 150.0);
		button.setPrefSize(100.0, 75.0);
		
		return button;
	}

}