package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DataHandler;
import model.FileHandler;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller_login implements Initializable {
    String datei_users = "users.txt";


    DataHandler dataHandler;
    FileHandler fileHandler;

    @FXML
    private Label lblStatus;
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnLoginPressed(ActionEvent event) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        Path pfad = Paths.get("users.txt");
        System.out.println(pfad);


        String message;

        message = dataHandler.checkUser(tfUsername.getText(), passwordField.getText()); //checkUser hat als Rückgabewert einen String


        if (message.equals("login successful.")) {
            String loggedInUsername = tfUsername.getText();

            dataHandler.setLoggedInUser(loggedInUsername);
            //Scene wechsel
            main.Main.loadScene("/viewctrl/view_logged_in_ToDos.fxml");

            //Title ändern
            main.Main.getPrimaryStage().setTitle("ToDo-Project: logged in as " + loggedInUsername);
        } else {
            //alertBox!
            lblStatus.setText(message);
        }
    }

    @FXML
    void btnGoToAddUserPressed(ActionEvent event) throws IOException {
        main.Main.loadScene("/viewctrl/view_addUser.fxml");
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Add User");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = main.Main.getDataHandler();
        fileHandler = new FileHandler();
        lblStatus.setText("");

        //Es wird beim Programmstart die init-Methode aufgerufen
        String dateiInhalt;
        dateiInhalt = fileHandler.dateiEinlesen(datei_users);
        dataHandler.init(dateiInhalt);    //Als Parameter der init-Methode wird der Dateiinhalt übergeben

    }
}
