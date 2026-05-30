package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DataHandler;
import model.FileHandler;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_login implements Initializable {
    String datei_users = "../ToDo/users.txt";

    DataHandler dataHandler;
    FileHandler fileHandler;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnLoginPressed(ActionEvent event) throws IOException {
        String message;
        message = dataHandler.checkUser(tfUsername.getText(), passwordField.getText()); //checkUser hat als Rückgabewert einen String

        if (message.equals("login successful.")) {
            //Scene wechsel
            main.Main.loadScene("/viewctrl/logged_in_user.fxml");

            //Title ändern
            main.Main.getPrimaryStage().setTitle("ToDo-Project: logged in as Username");
        } else {
            //alert!!!!
            System.out.println("alert: " + message);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = new DataHandler();
        fileHandler = new FileHandler();

        //Es wird beim Programmstart die init-Methode aufgerufen
        String dateiInhalt;
        dateiInhalt = fileHandler.dateiEinlesen(datei_users);
        dataHandler.init(dateiInhalt);    //Als Parameter der init-Methode wird der Dateiinhalt übergeben

        //Zur Überprüfung
        dataHandler.printSet();
        System.out.println("Init durchgeführt.");
    }
}
