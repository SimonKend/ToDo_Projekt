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
import java.util.ResourceBundle;

public class Controller_addUser implements Initializable {
    String datei_users = "users.txt";

    private DataHandler dataHandler;
    private FileHandler fileHandler;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblStatus;

    @FXML
    private PasswordField pfNewPassword;

    @FXML
    private TextField tfNewUsername;

    @FXML
    void btnSavePressed(ActionEvent event) {
        String username = tfNewUsername.getText().trim();
        String password = pfNewPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            lblStatus.setText("Fields cannot be empty!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean wurdeHinzugefuegt = dataHandler.addUserWithCheck(username, password);
        if (wurdeHinzugefuegt) {
            fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());

            lblStatus.setText("User added successfully!");
            lblStatus.setStyle("-fx-text-fill: green;");

            tfNewUsername.clear();
            pfNewPassword.clear();
        } else {
            lblStatus.setText("Error: Username already exists!");
            lblStatus.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    void btnBackPressed(ActionEvent event) throws IOException {
        main.Main.loadScene("/viewctrl/view_login.fxml");
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataHandler = main.Main.getDataHandler();
        fileHandler = new FileHandler();
        lblStatus.setText("");
    }
}