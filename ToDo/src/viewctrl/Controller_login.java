package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.DataHandler;
import model.FileHandler;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_login implements Initializable {
    String datei_users = "D:\\SIMON\\3BHIT\\SEW\\Üben\\4. PLF\\Ueben_1\\src\\datei.txt";

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
        //Scene wechsel
        main.Main.loadScene("/viewctrl/logged_in_user.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: logged in as Username");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = new DataHandler();
        fileHandler = new FileHandler();
    }
}
