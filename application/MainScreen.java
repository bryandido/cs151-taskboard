package application;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainScreen {
	
	private Scene scene;

	private Label selectLabel = new Label("Select Project");
	private ComboBox<String> dropDownMenu = new ComboBox();
	private ObservableList<String>  data = FXCollections.observableArrayList();
	
	private Button editBtn = new Button("Edit");
	private Button saveBtn = new Button("Save");
	private Button deleteBtn = new Button("Delete");
	private Button loadBtn = new Button("Load...   ");
	private Button createBtn = new Button("Create New");
	private Button logOutBtn = new Button("Logout");
	private int projectSize = 0;
	private TaskBoardModel boardModel = new TaskBoardModel("boo");
	private GridPane topGrid = new GridPane();
	private GridPane bottomGrid = new GridPane();
	private int bottomGridCount = 0;
	private Button refreshBtn = new Button("CLICK TO REFRESH");
	private int refreshCount = 0;
	private int dropDownMenuIndex;
	private int prevDropDownMenuIndex;
	//ArrayList<ArrayList<Integer>> dropDownMenuIndexArray = new ArrayList<ArrayList<Integer>>();


	
	//Summary
	
	//I combined the views and controllers
	// Not quite sure if I did the controllers correctly, looking at her lab it seemed like the idea
	// of "controllers" meant adding actionEvent handlers to the interface objects
	// Therefore I did as such
	
	// MainScreen is a taskBoardModel view,
	// taskBoardModel view contains, a LIST of PROJECTMODELS, i.e. a list containing models of a project
	// EACH PROJECTMODEL contains a  LIST of STRINGS called COLUMNS, these strings are the title of our COLUMNS
	// EACH PROJECTMODEL also contains a LIST of TASKS
	
	// THEREFORE, referencing would be something like
	
	// TaskBoardModel -> ProjectModel -> ProjectList -> Columns 
	// taskBoardModel.getProjectList().get(INDEX OF WHICH PROJECT).getColumns().get(INDEX OF WHICH COLUMN)
	// This would return an individual column, of a specific project
	
	// taskBoardModel.getProjectList().get(INDEX OF WHICH PROJECT)
	// This would return a specific project
	
	// Since for this assignment we need,
	// - Creation/Deletion/Edit/Save/Load/Selection of different and unique projects
	// - Creation/Deletion/Edit/Save/Load/Selection of different and unique columns
	// - Creation/Deletion/Edit/Save/Load/Selection of different and unique tasks

	// Understanding the referencing and getters of this is important
	
	// What is done so far
	// As of April 24
	
	// Creation/Selection of different and unique projects
	// Creation/Selection of different and unique columns
	// ^^ Really the hard part, everything else shouldn't be too hard, therefore I think 
	// we're halfway done
	
	// ISSUES
	// Deletion of different and unique projects
	
	// NOTES
	// We can use the same projectView to edit the project/columns


	
	
	
	public MainScreen() {
		
		VBox root = new VBox();
		this.topGrid.setAlignment(Pos.TOP_LEFT);
		this.topGrid.setHgap(10);
		this.topGrid.setVgap(10);
		this.topGrid.setPadding(new Insets(25, 25, 25, 25));		
		this.topGrid.add(this.selectLabel, 0, 1);
		this.topGrid.add(this.dropDownMenu, 1, 1);
		this.topGrid.add(this.editBtn, 2, 1);
		this.topGrid.add(this.saveBtn, 3, 1);
		this.topGrid.add(this.deleteBtn, 4, 1);
		this.topGrid.add(this.loadBtn, 5, 1);
		this.topGrid.add(this.createBtn, 6, 1);
		this.topGrid.add(this.logOutBtn, 7, 1);
		this.topGrid.add(this.refreshBtn, 8, 1);
		
		//addProjectList("asdsdsdsdsdsaa");
    	//addProjectList("Empty");

		this.scene = new Scene(root, 900, 700);

		///////////////////////////////////////////////
		//Implement task columns
		
		
		
		this.bottomGrid.setAlignment(Pos.TOP_LEFT);
		this.bottomGrid.setHgap(10);
		this.bottomGrid.setVgap(10);
		this.bottomGrid.setPadding(new Insets(25, 25, 25, 25));

		root.getChildren().addAll(this.topGrid,this.bottomGrid);
		
		
		///Controller
		
		//dropDownMenu.getSelectionModel().select(1);
		//dropDownMenu.getSelectedIndex()

		dropDownMenu.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    	dropDownMenuIndex = dropDownMenu.getSelectionModel().getSelectedIndex();
		    	

		    	
		    	int index = boardModel.getProjectList().get(prevDropDownMenuIndex).getColumns().size();
		    	System.out.println(index);
		    	System.out.println(boardModel.getProjectList().get(prevDropDownMenuIndex).getColumns().get(0));

		    	for (int i = 0; i < index; i++){
		    		
		    		bottomGrid.getChildren().remove(0);
		    		bottomGridCount--;
		    		refreshCount = 0;
		    	}
		    	
		    	prevDropDownMenuIndex = dropDownMenuIndex;
		    	
		    	//int currentSize = boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size();
		    	while (refreshCount < boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size()){
	        		createColumn(boardModel.getProjectList().get(dropDownMenuIndex).getColumns().get(refreshCount));
	        		refreshCount++;
		    	}
		    }
		});
		
		createBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    		
		      
		        	Stage stageTheLabelBelongs = (Stage) createBtn.getScene().getWindow();
		        	
		        	ProjectView secondPane = new ProjectView(boardModel, getScene());

		        	stageTheLabelBelongs.setScene(secondPane.getScene());
		        	stageTheLabelBelongs.setX(0);
		        	stageTheLabelBelongs.setY(0);
		       
		        	
		        	
		        	
		    }
		});
		
		
		refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
  	
		    		addProjectList("temp");
	        	
		        	while (refreshCount < boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size()){
		        		createColumn(boardModel.getProjectList().get(dropDownMenuIndex).getColumns().get(refreshCount));
		        		refreshCount++;
		        	}
		    }
		    
		});
		
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    	int index = boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size();
		    	System.out.println("Index of " + dropDownMenuIndex + "is " + index);
		    	for (int i = 0; i < index; i++){
		    		System.out.println(boardModel.getProjectList().get(dropDownMenuIndex).getColumns().get(0));
		    		boardModel.getProjectList().get(dropDownMenuIndex).getColumns().remove(0);
		    		bottomGrid.getChildren().remove(0);
		    		System.out.println("ok");
		    	}
		    	
		    boardModel.getProjectList().remove(dropDownMenuIndex);	
		    data.remove(dropDownMenuIndex);
		    projectSize--;
		    dropDownMenu.getItems().remove(dropDownMenuIndex);
		    }
		});
		
		
	}
	
	
	
	public void addProjectList(String projectName){
		
			this.data.add(this.boardModel.getProjectList().get(projectSize).getProjectName());
			this.projectSize += 1;
			this.dropDownMenu.setItems(data);
		
	}
	
	public void createColumn(String columnName){
		
		
		SplitPane columnTasks = new SplitPane();
		columnTasks.setOrientation(Orientation.VERTICAL);
		columnTasks.setPrefSize(200, 400);
		Label columnLabel = new Label(columnName);
        Button r = new Button("Right Button");
        columnTasks.getItems().addAll(columnLabel, r);
		this.bottomGrid.add(columnTasks, this.bottomGridCount, 1);
		this.bottomGridCount++;


	}
	public Scene getScene(){
		return this.scene;
	}
}
