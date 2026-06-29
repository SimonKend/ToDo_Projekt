package viewctrl;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.DataHandler;
import model.FileHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller_logged_in_General_Infos implements Initializable {
    String datei_users = "users.txt";
    DataHandler dataHandler;
    FileHandler fileHandler;

    @FXML
    private Menu infosMenu;

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu toDoMenu;

    @FXML
    private Menu userMenu;

    @FXML
    private Button btnCloseProgram;

    @FXML
    void btnCloseProgramPressed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to close the program?");
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        // Überprüfen, welche Schaltfläche der Benutzer gewählt hat
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Main.getPrimaryStage().close();
        }
    }

    @FXML
    void menuToDosShowed(Event event) throws IOException {
        System.out.println("ToDo's wurde geöffnet.");
        main.Main.loadScene("/viewctrl/view_logged_in_ToDos.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: " + dataHandler.getLoggedInUser() + ": ToDo's");
    }

    @FXML
    void menuUserInfosShowed(Event event) throws IOException {
        System.out.println("User Infos wurde geöffnet.");
        //Scene wechsel
        main.Main.loadScene("/viewctrl/view_logged_in_User_Infos.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: " + dataHandler.getLoggedInUser() + ": User Infos");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = main.Main.getDataHandler();
        fileHandler = new FileHandler();
    }
}
