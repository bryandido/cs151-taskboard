package application;

import java.io.File; 
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadView {


	private File selectedFile;
	private FileChooser fileChooser = new FileChooser();
	private LoadViewModel loadViewModel = new LoadViewModel();
	
	public LoadView(Stage stage) {
		selectedFile = this.fileChooser.showOpenDialog(stage);
	}
	
	public TaskBoardModel LoadFromFile() {
		return loadViewModel.load(selectedFile);
	}
}
