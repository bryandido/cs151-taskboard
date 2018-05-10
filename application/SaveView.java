package application;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveView {

	private SaveViewModel saveViewModel = new SaveViewModel();
	private FileChooser fileChooser = new FileChooser();
	private File file;
	
	public SaveView(/*TaskBoardModel boardModel, Scene prevScene*/Stage stage){
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
		fileChooser.getExtensionFilters().add(extFilter);
		file = fileChooser.showSaveDialog(stage);
	}
	
	public void saveToFile(TaskBoardModel boardModel) {
		saveViewModel.save(boardModel, this.file);
	}
}
