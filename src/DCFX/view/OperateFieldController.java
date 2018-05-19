package DCFX.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import DCFX.MainApp;
import DCFX.model.Path;

public class OperateFieldController {
	 @FXML
	 private TableView<Path> pathTable;
	 @FXML
	 private TableColumn<Path, String> nameColumn;
	 @FXML
	 private TableColumn<Path, String> ipColumn;

	 @FXML
	 private Label name;
	 @FXML
	 private Label path;
	 @FXML
	 private Label pattern;
	 @FXML
	 private Label emptyRate;
	 @FXML
	 private Label usedRate;
	 @FXML
	 private Label days;

	 // Reference to the main application.
	 private MainApp mainApp;

	 /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	 public OperateFieldController() {
	 }

	 /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
	        ipColumn.setCellValueFactory(cellData -> cellData.getValue().ipProperty());
	    }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     *
	     * @param mainApp
	     */
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        pathTable.setItems(mainApp.getPathData());
	    }

}
