package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main_GUI extends Application{
	
	private Scene scene;
	private GridPane root;
	private GridPane navigate;
	private GridPane function;
	private GridPane login;
	private Button login_btn;
	private GridPane getin;
	
	private Label person;
	private Label health;
	private Label movement;
	private Label isolation;
	private Label test;
	private Label view;
	
	private TextField username;
	private PasswordField password;
	
	private PersonForm personForm;
	private HealthForm healthForm;
	private MovementForm movementForm;
	private ResultTestForm resultTestForm;
	private ViewForm viewForm;
	private IsolationForm isolationForm;
	
	private EventHandler<MouseEvent> eventHandler1;
	private EventHandler<MouseEvent> eventHandler2;
	private EventHandler<MouseEvent> eventHandler3;
	
	private Label wrong;
	
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
		this.login = new GridPane();
		Scene login_form = new Scene(login, 1000, 500);
		
		this.login_btn = new Button("Đăng nhập");
		
		this.wrong = new Label("Tên đăng nhập hoặc mật khẩu không đúng!");
		this.wrong.setAlignment(Pos.CENTER);
		this.wrong.setStyle("-fx-background-color: #c9cbff;"
				+ "-fx-font-weight: bold;");
		this.wrong.setMaxSize(420, 200);
		setLoginPane();
		this.login_btn.setOnAction(e -> {
			if (username.getText() != null && username.getText().equals("NMCNPM") && password.getText() != null && password.getText().equals("Nhom15"))
				{
					primaryStage.setScene(scene);
				}
			else {
				getin.add(wrong, 1, 1, 1, 1);
			}
		}); 
		 
        primaryStage.setTitle("QL_Covid19_NMCNPM_Nhom17");
        primaryStage.setScene(login_form);
        primaryStage.setResizable(false);
        primaryStage.setX(200);
        primaryStage.setY(50);
        primaryStage.show();
		
	}
	
	private void setLoginPane()
	{
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(40.0);
		this.login.getColumnConstraints().add(column1);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(60.0);
		this.login.getColumnConstraints().add(column2);
		
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(60.0);
		this.login.getRowConstraints().add(row1);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(40.0);
		this.login.getRowConstraints().add(row2);
		
		this.login.setStyle("-fx-background-color: #7579e7;");
		//this.login.setGridLinesVisible(true);
		Label label_title = new Label("Quản lý Covid 19");
		label_title.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;"
				+ "-fx-font-size: 40;");
		label_title.setAlignment(Pos.TOP_CENTER);
		label_title.setMaxSize(500, 500);
		
		Image img = new Image(getClass().getResourceAsStream("/gui/note.gif"));
	    Circle cir2 = new Circle(250,200,80); 
	    cir2.setStroke(Color.SEAGREEN); 
	    cir2.setFill(new ImagePattern(img, 150, 120, 195, 150, false));
	    cir2.setTranslateX(110);
	      
	    this.getin = new GridPane();
	    this.getin.setStyle("-fx-background-color: white;");
	    this.login.add(this.getin, 1, 0, 1, 2);
	    
	    ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(15);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(70.0);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(15);
		this.getin.getColumnConstraints().addAll(column3, column4, column5);
		
		for (int i=0; i< 10; i++) {
        	RowConstraints rowConst = new RowConstraints();
        	rowConst.setPercentHeight(100/10);
        	this.getin.getRowConstraints().add(rowConst);
        }
	    //this.getin.setGridLinesVisible(true);
	    this.getin.setHgap(5);
	    this.getin.setVgap(5);
	    this.getin.setAlignment(Pos.CENTER);
	    
	    this.username = new TextField("Tên đăng nhập");
	    this.username.setMaxSize(420, 200);
	    this.username.setStyle("-fx-background-color: #a9a9a9 , #7579e7 , white;"
	    		+ "    -fx-background-insets: 0 -1 -1 -1, -1 -1 -1 -1, 0 -1 3 -1;");
	    this.password = new PasswordField();
	    this.password.setMaxSize(420, 200);
	    this.password.setStyle("-fx-background-color: #a9a9a9 , #7579e7 , white;"
	    		+ "    -fx-background-insets: 0 -1 -1 -1, -1 -1 -1 -1, 0 -1 3 -1;");
	    this.getin.add(this.username, 1, 3, 1, 1);
	    this.getin.add(this.password, 1, 5, 1, 1);
	    
	    this.login_btn.setMaxSize(420, 200);
	    this.login_btn.setStyle("-fx-background-color: #7579e7;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
	    this.getin.add(this.login_btn, 1, 7, 1, 1);
	    this.login.setAlignment(Pos.BASELINE_CENTER);
	      
	    this.login.add(cir2, 0, 0, 1, 1);
	     
		this.login.add(label_title, 0, 1, 1, 1);
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
        
        this.person = new Label("Dân cư");
        this.health = new Label("Sức khỏe");
        this.movement = new Label("Di chuyển");
        this.test = new Label("Xét nghiệm");
        this.view = new Label("Thống kê");
        this.isolation = new Label("Cách ly");
        
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
