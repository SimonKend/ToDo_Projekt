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
import model.DataHandler;
import model.FileHandler;
import model.ToDo;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_logged_in_UserInfos implements Initializable {
    String datei_users = "users.txt";
    DataHandler dataHandler;
    FileHandler fileHandler;

    @FXML
    private Button btnAddToDo;

    @FXML
    private VBox vBox;
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
    private ListView<String> listViewUsernames;

    @FXML
    private Label lblAdminHeader;


    @FXML
    void btnLogoutPressed(ActionEvent event) throws IOException {
        dataHandler.setLoggedInUser("Username");
        //Scene wechsel
        main.Main.loadScene("/viewctrl/view_login.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");
    }
    @FXML
    void menuToDosShowed(Event event) {
        System.out.println("ToDo's wurde geöffnet.");
    }
    @FXML
    void menuUserInfosShowed(Event event) {
        System.out.println("User Infos wurde geöffnet.");
    }
    @FXML
    void menuGeneralInfosShowed(Event event) {
        System.out.println("General Infos wurde geöffnet.");
    }
    @FXML
    void btnAddToDoPressed(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Neues ToDo");
        dialog.setHeaderText(null);
        dialog.setContentText("ToDo:");

        dialog.showAndWait().ifPresent(text -> {

            dataHandler.getUser(dataHandler.getLoggedInUser()).getUserToDos().add(new ToDo(text));

            updateVBox();

            //schreiben
            fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());
        });
    }

    private void updateVBox() {
        vBox.getChildren().clear();

        for (ToDo x : dataHandler.getUser(dataHandler.getLoggedInUser()).getUserToDos()) {

            HBox hBox = new HBox(10);
            hBox.setPrefHeight(40);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5   , 15, 5, 15));

            //                                                                                                              Insets ist der Abstand vom Hintergrund zur HBox
            hBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(8), Insets.EMPTY)));


            Label label = new Label(x.getName());
            label.setPrefWidth(150);

            Button button = new Button("Löschen");
            button.setPrefWidth(80);

            hBox.getChildren().addAll(label, button);

            button.setOnAction(e -> {

                dataHandler.getUser(dataHandler.getLoggedInUser())
                        .getUserToDos()
                        .remove(x);

                updateVBox();

                //schreiben
                fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());
            });

            vBox.getChildren().add(hBox);
        }

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

        //Das muss eigentlich bei einem jeden refresh aufgerufen werden
        updateVBox();
    }
}
