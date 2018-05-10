package application;

import java.io.File; 
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadView {


	private File selectedFile;
	private FileChooser fileChooser = new FileChooser();
	private LoadViewModel loadViewModel = new LoadViewModel();
	
	public LoadView(Stage stage) {
		this.selectedFile = this.fileChooser.showOpenDialog(stage);
	}
	
	public TaskBoardModel LoadFromFile() {
		if (this.selectedFile==null) {
			System.out.println("LOAD OPERATION CANCELLED!");
			return null;
		} else {
			System.out.println("SUCCESSFULLY LOADED!");
			return loadViewModel.load(this.selectedFile);
		}
	}
}
