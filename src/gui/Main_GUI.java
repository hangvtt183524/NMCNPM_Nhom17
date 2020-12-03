package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
	
	
	private Label person;
	private Label health;
	private Label movement;
	private Label isolation;
	private Label test;
	private Label view;
	private Label logout;
	
	private PersonForm personForm;
	private MovementForm movementForm;
	
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		setNavigatePane();
		setFunctionPane();
		setRootPane();
		
		Scene scene = new Scene(root, 1500, 850);
		 
        primaryStage.setTitle("Covid-19");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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
        this.navigate.setStyle("-fx-background-color: #26ad48;");
        
        for (int i=0; i< 10; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100.0/10);
        	this.navigate.getRowConstraints().add(rowConst);
        }

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0);
        this.navigate.getColumnConstraints().add(colConst);
        
        this.person = new Label("Person");
        this.health = new Label("Health");
        this.isolation = new Label("Isolation");
        this.movement = new Label("Movement");
        this.test = new Label("Test");
        this.view = new Label("View");
        
        this.navigate.add(setLabel(this.person), 0, 1, 1, 1);
        this.navigate.add(setLabel(this.movement), 0, 2, 1, 1);
        this.navigate.add(setLabel(this.health), 0, 3, 1, 1);
        this.navigate.add(setLabel(this.isolation), 0, 4, 1, 1);
        this.navigate.add(setLabel(this.test), 0, 5, 1, 1);
        this.navigate.add(setLabel(this.view), 0, 6, 1, 1);
        //this.navigate.add(child, columnIndex, rowIndex);
        
	}
	
	// design function pane
	private void setFunctionPane()
	{
		this.function = new GridPane();
		
        RowConstraints rowFunction = new RowConstraints();
        rowFunction.setPercentHeight(100.0);
        this.function.getRowConstraints().add(rowFunction);
        
        ColumnConstraints colFunction = new ColumnConstraints();
        colFunction.setPercentWidth(100.0);
        this.function.getColumnConstraints().add(colFunction);
        
        //this.personForm = new PersonForm();
        //this.function.add(this.personForm, 0, 0, 1, 1);
        
        this.movementForm = new MovementForm();
        this.function.add(this.movementForm, 0, 0, 1, 1);
	}
	
	private Label setLabel(Label label)
	{
		label.setFont(new Font("Aria", 25));
		label.setStyle("-fx-background-color: #26ad48;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		label.setAlignment(Pos.CENTER);

		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		
		handleHoverLabel(label);
		
		return label;
	}
	
	private void handleHoverLabel(Label label) 
	{
		EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) { 
				   label.setStyle("-fx-background-color: #66e889;"
				   		+ "-fx-text-fill: white;"
				   		+ "-fx-font-weight: bold;");
			   } 
			};
		label.addEventFilter(MouseEvent.MOUSE_ENTERED, eventHandler1);
		
		EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) { 
				   label.setStyle("-fx-background-color: #26ad48;"
				   		+ "-fx-text-fill: white;"
				   		+ "-fx-font-weight: bold;");
			   } 
			};
		label.addEventFilter(MouseEvent.MOUSE_EXITED, eventHandler2);
	}
}