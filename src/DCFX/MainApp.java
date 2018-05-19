package DCFX;

import java.io.IOException;

import DCFX.model.Path;
import DCFX.view.OperateFieldController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Path> pathData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
    	pathData.add(new Path("原始1", "正常"));
    	pathData.add(new Path("原始2", "正常"));
    	pathData.add(new Path("原始3", "正常"));
    	pathData.add(new Path("原始4", "正常"));
    	pathData.add(new Path("格记1", "正常"));
    	pathData.add(new Path("格记2", "正常"));
    	pathData.add(new Path("格记3", "正常"));
    	pathData.add(new Path("格记4", "正常"));
    	pathData.add(new Path("快视1", "正常"));
    	pathData.add(new Path("块视2", "正常"));
    	pathData.add(new Path("快视3", "正常"));
    	pathData.add(new Path("快视4", "正常"));

    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Path> getPathData() {
        return pathData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("磁盘管理");

        initRootLayout();

        showOperateField();
    }

    /**
     * 初始化根视图
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showOperateField() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OperateField.fxml"));
            AnchorPane OperateField = (AnchorPane) loader.load();

            // 显示操作区
            rootLayout.setCenter(OperateField);

         // 控制器连接主程序
            OperateFieldController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}