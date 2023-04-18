package View.Gui;

import View.Gui.programList.ListController;
import View.Gui.programRun.RunController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(getClass().getResource("programList/programList.fxml"));
        AnchorPane root = (AnchorPane) listLoader.load();
        ListController listController = listLoader.getController();

        primaryStage.setTitle("Select a program");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();   
        
        FXMLLoader runLoader = new FXMLLoader();
        runLoader.setLocation(getClass().getResource("programRun/programRun.fxml"));
        AnchorPane root2 = (AnchorPane) runLoader.load();
        RunController runControl = runLoader.getController();
        listController.setRunController(runControl);

        Stage runStage = new Stage();
        runStage.setTitle("Running of program");
        runStage.setScene(new Scene(root2));
        runStage.show();
    }

    public static void main(String[] args) {
        launch(args);    
    }
}
