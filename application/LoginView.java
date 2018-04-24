package application;

import java.awt.event.ActionListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView {

	private Scene scene;
	private Text scenetitle = new Text("Welcome");
	private Label userName = new Label("User Name:");
	private TextField userTextField = new TextField();
	private Label pw = new Label("Password:");
	private PasswordField pwBox = new PasswordField();
	private Button signInBtn = new Button("Sign in");


	public LoginView() {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		this.scene = new Scene(grid, 300, 275);
		
		this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		grid.add(this.userName, 0, 1);
		grid.add(this.userTextField, 1, 1);
		grid.add(this.pw, 0, 2);
		grid.add(this.pwBox, 1, 2);
		
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(this.signInBtn);
		grid.add(hbBtn, 1, 4);
		
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
		
		signInBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    
		        actiontarget.setFill(Color.FIREBRICK);
		        actiontarget.setText("Sign in button pressed");
		        
		        //if (userTextField.getText().equals("admin") && pwBox.getText().equals("password")){
		        	Stage stageTheLabelBelongs = (Stage) actiontarget.getScene().getWindow();
		        	//BorderPane secondPane = new BorderPane();

		        	MainScreen secondPane = new MainScreen();
		        	stageTheLabelBelongs.setScene(secondPane.getScene());
		        	stageTheLabelBelongs.setX(0);
		        	stageTheLabelBelongs.setY(0);
		        //}
		    }
		});
		
	}
	
	public Scene getScene(){
		return this.scene;
	}
	/*
	public String getUserInput() {
	        return userTextField.getText();
	    }
	
	public String getPWInput() {
		return pwBox.getText();
	}

	
	public void addBtnListener(ActionListener al){
        signInBtn.setOnAction(al);

	}*/

	
	

}
