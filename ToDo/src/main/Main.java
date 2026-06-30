package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataHandler;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    // Zentraler DataHandler für alle Controller
    private static DataHandler dataHandler = new DataHandler();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadScene("/viewctrl/view_login.fxml");
        primaryStage.setTitle("ToDo-Project: Login");
        primaryStage.show();
    }
    public static void loadScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        primaryStage.setScene(new Scene(root));
    }

    //Getter
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    // Getter für die Controller
    public static DataHandler getDataHandler() {
        return dataHandler;
    }
}
