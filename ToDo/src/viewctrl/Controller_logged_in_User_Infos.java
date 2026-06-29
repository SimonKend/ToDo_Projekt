package viewctrl;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.Main;
import model.DataHandler;
import model.FileHandler;
import model.ToDo;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller_logged_in_User_Infos implements Initializable {
    String datei_users = "users.txt";
    DataHandler dataHandler;
    FileHandler fileHandler;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Label labelDoneToDos;

    @FXML
    private Label labelNotDoneToDos;

    @FXML
    private Menu infosMenu;

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu toDoMenu;

    @FXML
    private Menu userMenu;

    @FXML
    private Button btnLogout;

    @FXML
    private Label labelWelcomeMessage;

    @FXML
    void btnLogoutPressed(ActionEvent event) throws IOException {
        dataHandler.setLoggedInUser("Username");
        //Scene wechsel
        main.Main.loadScene("/viewctrl/view_login.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");
    }

    @FXML
    void menuToDosShowed(Event event) throws IOException {
        System.out.println("ToDo's wurde geöffnet.");
        main.Main.loadScene("/viewctrl/view_logged_in_ToDos.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: " + dataHandler.getLoggedInUser() + ": ToDo's");

    }

    @FXML
    void menuGeneralInfosShowed(Event event) throws IOException {
        System.out.println("General Infos wurde geöffnet.");

        main.Main.loadScene("/viewctrl/view_logged_in_General_Infos.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: " + dataHandler.getLoggedInUser() + ": General Infos");
    }

    @FXML
    void btnDeleteUserPressed(ActionEvent event) throws IOException {
        System.out.println("delete User");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to delete this user?");
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        // Überprüfen, welche Schaltfläche der Benutzer gewählt hat
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dataHandler.deleteUser(dataHandler.getLoggedInUser());

            //speichern
            fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());

            //logout
            dataHandler.setLoggedInUser("Username");
            //Scene wechsel
            main.Main.loadScene("/viewctrl/view_login.fxml");

            //Title ändern
            main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = main.Main.getDataHandler();
        fileHandler = new FileHandler();
        btnDeleteUser.setText("delete " + dataHandler.getLoggedInUser());

        String aktuellerUser = dataHandler.getLoggedInUser();
        labelWelcomeMessage.setText("Hello, " + aktuellerUser + "! This are your personal infos:");
    }
}
