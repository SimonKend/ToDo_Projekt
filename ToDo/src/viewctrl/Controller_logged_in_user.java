package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.DataHandler;
import model.FileHandler;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_logged_in_user implements Initializable {
    DataHandler dataHandler;
    FileHandler fileHandler;

    @FXML
    private Button btnLogout;

    @FXML
    private Label labelWelcomeMessage;

    @FXML
    private ListView<String> listViewUsernames;

    @FXML
    private Label lblAdminHeader;

    @FXML
    void btnLogoutPressed(ActionEvent event) throws IOException {
        dataHandler.setLoggedInUser("Username");
        //Scene wechsel
        main.Main.loadScene("/viewctrl/login.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = main.Main.getDataHandler();
        fileHandler = new FileHandler();

        String aktuellerUser = dataHandler.getLoggedInUser();
        labelWelcomeMessage.setText("Hello, " + aktuellerUser + "! You are logged in!");

        if (aktuellerUser.equalsIgnoreCase("admin")) {
            lblAdminHeader.setVisible(true);
            listViewUsernames.setVisible(true);
            for (User u : dataHandler.getUsersSet()) {
                listViewUsernames.getItems().add(u.getUsername());
            }
        }
    }
}
