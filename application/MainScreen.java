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
import javafx.scene.Node;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class MainScreen {
	
	private Scene scene;

	private Label selectLabel = new Label("Select Project");
	private ComboBox<String> dropDownMenu = new ComboBox();
	private ObservableList<String>  data = FXCollections.observableArrayList();
	
	private Button editBtn = new Button("Edit");
	private Button saveBtn = new Button("Save");
	private Button loadBtn = new Button("Load...   ");
	private Button createBtn = new Button("Create New");
	private Button logOutBtn = new Button("Logout");
	private int projectSize = 0;
	private TaskBoardModel boardModel = new TaskBoardModel("boo");
	private GridPane topGrid = new GridPane();
	private GridPane bottomGrid = new GridPane();
	private int bottomGridCount = 0;
	
	private Button taskRefreshBtn = new Button("After task");
	private Button createRefreshBtn = new Button("After create");
	private Button editRefreshBtn = new Button("After editing");
	private int dropDownMenuIndex;
	private int currentTaskPassByValue = 0;

	//To get a specific project
	//taskBoardModel.getProjectList().get(INDEX OF WHICH PROJECT)
	
	//To get a specific column under a specific project
	//taskBoardModel.getProjectList().get(INDEX OF WHICH PROJECT).getColumns().get(INDEX OF WHICH COLUMN)
	
	//To get a specific task under a specific project
	//taskBoardModel.getProjectList().get(INDEX OF WHICH PROJECT).getTasks().get(INDEX OF WHICH TASKS)
	
	
	
	//Summary
	
	//I combined the views and controllers
	// Not quite sure if I did the controllers correctly, looking at her lab it seemed like the idea
	// of "controllers" meant adding actionEvent handlers to the interface objects
	// Therefore I did as such
	
	// MainScreen is a taskBoardModel view,
	// taskBoardModel view contains, a LIST of PROJECTMODELS, i.e. a list containing models of a project
	// EACH PROJECTMODEL contains a  LIST of STRINGS called COLUMNS, these strings are the title of our COLUMNS
	// EACH PROJECTMODEL also contains a LIST of TASKS

	
	// Since for this assignment we need,
	// - Creation/Edit/Save/Load/Selection of different and unique projects
	// - Creation/Edit/Save/Load/Selection of different and unique columns
	// - Creation/Edit/Save/Load/Selection of different and unique tasks

	// Understanding the referencing and getters of this is important
	
	// WHAT IS DONE SO FAR

	
	// Creation/Edit/Selection of different and unique projects
	// Creation/Edit/Selection of different and unique columns
	// Creation/Edit/Selection of different and unique tasks
	
	// WHAT NEEDS TO BE FINISHED
	
	// Saving and loading projects/columns/tasks
	// and refresh listeners for each creation, edit, and task
	// Resolve the mismatch click in refreshTask()
	
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
		this.topGrid.add(this.loadBtn, 4, 1);
		this.topGrid.add(this.createBtn, 5, 1);
		this.topGrid.add(this.logOutBtn, 6, 1);
		this.topGrid.add(this.createRefreshBtn, 7, 1);
		this.topGrid.add(this.editRefreshBtn, 8, 1);
		this.topGrid.add(this.taskRefreshBtn, 9, 1);
		this.scene = new Scene(root, 1000, 700);

		///////////////////////////////////////////////
		//Implement task columns

		this.bottomGrid.setAlignment(Pos.TOP_LEFT);
		this.bottomGrid.setHgap(10);
		this.bottomGrid.setVgap(10);
		this.bottomGrid.setPadding(new Insets(25, 25, 25, 25));

		root.getChildren().addAll(this.topGrid,this.bottomGrid);

		///Controller//////////////////////////////////////////////////////////////////////////////////////////////

		logOutBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	Stage stageTheLabelBelongs = (Stage) createBtn.getScene().getWindow();	        	
	        	LoginView secondPane = new LoginView();
	        	stageTheLabelBelongs.setScene(secondPane.getScene());
	        	stageTheLabelBelongs.setX(400);
	        	stageTheLabelBelongs.setY(200);  
		    }  
		});

		dropDownMenu.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
	
		    	while (bottomGrid.getChildren().size() != 0){
		    		bottomGrid.getChildren().remove(0);
		    		bottomGridCount--;
		    	}

		    	dropDownMenuIndex = dropDownMenu.getSelectionModel().getSelectedIndex();

		    	for (int i = 0; i < boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size(); i++){
	        		createColumn(boardModel.getProjectList().get(dropDownMenuIndex).getColumns().get(i));
		    	}	
		    	
		    	refreshTask();
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
		
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {

		        	Stage stageTheLabelBelongs = (Stage) createBtn.getScene().getWindow();
		        	ProjectView secondPane = new ProjectView(boardModel, getScene(), dropDownMenuIndex);
		        	stageTheLabelBelongs.setScene(secondPane.getScene());
		        	stageTheLabelBelongs.setX(0);
		        	stageTheLabelBelongs.setY(0);  	
		    }
		});
		
		//TODO: THE createBtn, creates a new project, but requires this button to be clicked afterwards to appear
		// Create something, maybe a listener so that you don't have to always click this
		createRefreshBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    		addProjectList("temp");	
		    		refreshPage();
		    }  
		});
		
		//TODO: THE editRefreshBtn, creates a new project, but requires this button to be clicked afterwards to appear
		// Create something, maybe a listener so that you don't have to always click this
		editRefreshBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	refreshPage();   	
		    }   
		});
		
		//TODO: THE button in createColumn, creates a new task, but requires this button to be clicked afterwards to appear
		// Create something, maybe a listener so that you don't have to always click this
		
		//ALSO, the taskGrid, edits a new task, but requires this button to be clicked afterwards to appear
		
		//Without said listener, we'd have to constantly click this, which recreates the same tasks
		//But if you switch projects and switch back, the number of tasks appearing is correct
		//But we need some kind of listener to do this automatically
		taskRefreshBtn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {	
		    	refreshTask();
		    }   
		});
		
		
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	
	public void refreshTask(){
		
		if (boardModel.getProjectList().get(dropDownMenuIndex).getTasks().size() != 0){
	        for (int i = 0; i < boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size(); i++){
	        	for (int j = 0; j < boardModel.getProjectList().get(dropDownMenuIndex).getTasks().size();j++){
	                if (boardModel.getProjectList().get(dropDownMenuIndex).getTasks().get(j).getStatus()
	                		.equals(boardModel.getProjectList().get(dropDownMenuIndex).getColumns().get(i))){
	            		
	                	Label taskName = new Label();
	            		Text taskDescription = new Text();
	            		Label taskStatus = new Label();
	            		Label taskDueDate = new Label();
	                	
	            		taskName.setText("Name: " + boardModel.getProjectList()
	            						.get(dropDownMenuIndex).getTasks().get(j).getTaskName());
	            		taskDescription.setText("Description: " + boardModel.getProjectList()
	            						.get(dropDownMenuIndex).getTasks().get(j).getDescription());
	                	taskStatus.setText("Status: " + boardModel.getProjectList()
	                					.get(dropDownMenuIndex).getTasks().get(j).getStatus());
	                	taskDueDate.setText("Due Date: " + boardModel.getProjectList()
	                					.get(dropDownMenuIndex).getTasks().get(j).getDueDate());
	                	 		
	                	GridPane taskGrid = new GridPane();
	                    RowConstraints rowConstraint1 = new RowConstraints(0);
	                    RowConstraints rowConstraint2 = new RowConstraints(25);
	                    RowConstraints rowConstraint3 = new RowConstraints(100);
	                    ColumnConstraints columnConstraint1 = new ColumnConstraints(0);
	                    ColumnConstraints columnConstraint2 = new ColumnConstraints(200);

	                	taskGrid.setGridLinesVisible(true);
	                	taskGrid.setPrefSize(100, 200);
	                	taskGrid.setMaxWidth(200);
	                	taskGrid.setMaxHeight(300);
	                	taskDescription.wrappingWidthProperty().bind(taskGrid.widthProperty());
	                	
	                	taskGrid.add(taskName, 1, 1);
	                	taskGrid.add(taskDescription, 1, 2);
	                	taskGrid.getRowConstraints().addAll(rowConstraint1, rowConstraint2, rowConstraint3);
	                	taskGrid.getColumnConstraints().addAll(columnConstraint1, columnConstraint2);

	                	taskGrid.add(taskDueDate, 1, 3);
	                	
	                	//TODO: DELETE WHEN DONE VV
	                	taskGrid.add(taskStatus, 1, 4);
	                	
	                	bottomGrid.addColumn(i , taskGrid);  	
	         
	                	bottomGridCount++;
	               
	                	//TODO: Having issues when clicking on the task, and switching them to 
	                	//different columns. Clicking on one then switching,
	                	// then trying to click results in mismatch clicks
	                	
	                	//currentTaskPassByValue = j;
	                	taskGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	                        @Override
	                        public void handle(MouseEvent mouseEvent) {
	                        	
	                        	Node source = (Node)mouseEvent.getSource() ;
	                            Integer colIndex = GridPane.getColumnIndex(source);
	                            //Integer rowIndex = GridPane.getColumnIndex(source);
	                            
	                            Stage stageTheLabelBelongs = (Stage) createBtn.getScene().getWindow();
	        		        	TaskView secondPane = new TaskView(boardModel, getScene(), dropDownMenuIndex, colIndex);
	        		        	//TaskView secondPane = new TaskView(boardModel, getScene(), dropDownMenuIndex, currentTaskPassByValue);
	        		        	stageTheLabelBelongs.setScene(secondPane.getScene());
	        		        	stageTheLabelBelongs.setX(0);
	        		        	stageTheLabelBelongs.setY(0); 
	                            
	                        }
	                    });
	                }
	        	}
	        }
	    }
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	public void refreshPage(){
		while (bottomGrid.getChildren().size() != 0){
    		bottomGrid.getChildren().remove(0);
    		bottomGridCount--;
    	}

    	for (int i = 0; i < boardModel.getProjectList().get(dropDownMenuIndex).getColumns().size(); i++){
    		createColumn(boardModel.getProjectList().get(dropDownMenuIndex).getColumns().get(i));  
    	}

    	data.set(dropDownMenuIndex, boardModel.getProjectList().get(dropDownMenuIndex).getProjectName());
    	dropDownMenu.getItems().set(dropDownMenuIndex, data.get(dropDownMenuIndex));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	public void addProjectList(String projectName){
		
			this.data.add(this.boardModel.getProjectList().get(projectSize).getProjectName());
			this.projectSize += 1;
			this.dropDownMenu.setItems(data);
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	public void createColumn(String columnName){

		SplitPane columnTasks = new SplitPane();
		columnTasks.setOrientation(Orientation.VERTICAL);
		columnTasks.setPrefSize(200, 50);
		Label columnLabel = new Label(columnName);
        Button newTaskBtn = new Button("+");
        
        
        columnTasks.getItems().addAll(columnLabel, newTaskBtn);
		this.bottomGrid.add(columnTasks, this.bottomGridCount, 1);
		this.bottomGridCount++;
		

		newTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	Stage stageTheLabelBelongs = (Stage) newTaskBtn.getScene().getWindow();	        	
	        	TaskView secondPane = new TaskView(boardModel, getScene(), dropDownMenuIndex);
	        	stageTheLabelBelongs.setScene(secondPane.getScene());
	        	stageTheLabelBelongs.setX(0);
	        	stageTheLabelBelongs.setY(0); 

		    }
		});


	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	public Scene getScene(){
		return this.scene;
	}
	
}
