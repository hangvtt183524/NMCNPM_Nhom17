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
	private HealthForm healthForm;
	private MovementForm movementForm;
	private ResultTestForm resultTestForm;
	private ViewForm viewForm;
	private IsolationForm isolationForm;
	
	private EventHandler<MouseEvent> eventHandler1;
	private EventHandler<MouseEvent> eventHandler2;
	private EventHandler<MouseEvent> eventHandler3;
	
	private Label pre;
	
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
		 
        primaryStage.setTitle("QL_Covid19_NMCNPM_Nhom17");
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
        this.navigate.setStyle("-fx-background-color: #7579e7;");
        
        for (int i=0; i< 10; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100.0/10);
        	this.navigate.getRowConstraints().add(rowConst);
        }

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0);
        this.navigate.getColumnConstraints().add(colConst);
        
        this.navigate.add(setLabel(this.person), 0, 1, 1, 1);
        this.navigate.add(setLabel(this.movement), 0, 2, 1, 1);
        this.navigate.add(setLabel(this.health), 0, 3, 1, 1);
        this.navigate.add(setLabel(this.test), 0, 4, 1, 1);
        this.navigate.add(setLabel(this.isolation), 0, 5, 1, 1);
        this.navigate.add(setLabel(this.view), 0, 6, 1, 1);
        
        setEventHandle();
        this.person.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
        this.person.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
        this.person.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler3);
        
        this.movement.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
        this.movement.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
        this.movement.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler3);
        
        this.health.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
        this.health.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
        this.health.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler3);
        
        this.test.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
        this.test.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
        this.test.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler3);
        
        this.view.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
        this.view.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
        this.view.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler3);
        
        this.isolation.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
        this.isolation.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
        this.isolation.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler3);
        
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
        
      //this.resultTestForm = new ResultTestForm();
        //this.function.add(this.resultTestForm, 0,  0, 1, 1);
        //getViewForm();
	}
	private void getPersonForm()
	{
		this.personForm = new PersonForm();
        this.function.add(this.personForm, 0, 0, 1, 1);
	}
	
	private void getHealthForm()
	{
		this.healthForm = new HealthForm();
        this.function.add(this.healthForm, 0, 0, 1, 1);
	}
	
	private void getMovementForm()
	{
        this.movementForm = new MovementForm();
        this.function.add(this.movementForm, 0,  0, 1, 1);
	}
	
	private void getResultTestForm()
	{
		this.resultTestForm = new ResultTestForm();
        this.function.add(this.resultTestForm, 0,  0, 1, 1);
	}
	
	private void getViewForm()
	{
		this.viewForm = new ViewForm();
		this.function.add(this.viewForm, 0, 0, 1, 1);
	}
	private void getIsolationForm()
	{
		this.isolationForm = new IsolationForm();
		this.function.add(this.isolationForm, 0, 0, 1, 1);
	}
	private Label setLabel(Label label)
	{
		label.setFont(new Font("Aria", 25));
		label.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
		label.setAlignment(Pos.CENTER);

		label.setMaxSize(390.0, 150.0);
		label.setPrefSize(100.0, 75.0);
		
		handleHoverLabel(label);
		
		return label;
	}
	
	private void setEventHandle()
	{
		this.eventHandler2 = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   Label node = (Label) e.getSource();
				   node.setStyle("-fx-background-color: #7579e7;"
				   		+ "-fx-text-fill: white;"
				   		+ "-fx-font-weight: bold;");
			   } 
			};
		
		this.eventHandler1 = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   Label node = (Label) e.getSource();
				   node.setStyle("-fx-background-color: #9ab3f5;"
				   		+ "-fx-text-fill: white;"
				   		+ "-fx-font-weight: bold;");
			   } 
			};
		
		this.eventHandler3 = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   Label node = (Label) e.getSource();
				   
				   if (node != person) {
					   person.setStyle("-fx-background-color: #7579e7;"
						   		+ "-fx-text-fill: white;"
						   		+ "-fx-font-weight: bold;");
					   person.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
					   person.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
					   
				   }
				   else getPersonForm();
				   
				   if (node != movement) {
					   movement.setStyle("-fx-background-color: #7579e7;"
						   		+ "-fx-text-fill: white;"
						   		+ "-fx-font-weight: bold;");
					   movement.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
					   movement.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
					   
				   }
				   else getMovementForm();
				   
				   if (node != health) {
					   health.setStyle("-fx-background-color: #7579e7;"
						   		+ "-fx-text-fill: white;"
						   		+ "-fx-font-weight: bold;");
					   health.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
					   health.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
					   
				   }
				   
				   else getHealthForm();
				   
				   if (node != isolation) {
					   isolation.setStyle("-fx-background-color: #7579e7;"
						   		+ "-fx-text-fill: white;"
						   		+ "-fx-font-weight: bold;");
					   isolation.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
					   isolation.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
				   }
				   
				   else getIsolationForm();
				   
				   if (node != test) {
					   test.setStyle("-fx-background-color: #7579e7;"
						   		+ "-fx-text-fill: white;"
						   		+ "-fx-font-weight: bold;");
					   test.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
					   test.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
				   }
				   else getResultTestForm();
				   
				   if (node != view) {
					   view.setStyle("-fx-background-color: #7579e7;"
						   		+ "-fx-text-fill: white;"
						   		+ "-fx-font-weight: bold;");
					   view.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
					   view.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
				   }
				   else getViewForm();
				   
				   node.removeEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler1);
				   node.removeEventHandler(MouseEvent.MOUSE_EXITED, eventHandler2);
				   
				   node.setStyle("-fx-background-color: #9ab3f5;"
				   		+ "-fx-text-fill: white;"
				   		+ "-fx-font-weight: bold;");
			   } 
			};
	}
}