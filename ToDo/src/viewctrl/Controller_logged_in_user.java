package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.DataHandler;
import model.FileHandler;
import model.ToDo;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_logged_in_user implements Initializable {
    DataHandler dataHandler;
    FileHandler fileHandler;

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
    void infosMenuPressed(ActionEvent event) {
        System.out.println("infosMenuPressed");
    }

    @FXML
    void toDoMenuPressed(ActionEvent event) {
        System.out.println("toDoMenuPressed");
    }

    @FXML
    void userMenuPressed(ActionEvent event) {
        System.out.println("userMenuPressed");
    }

    @FXML
    void btnLogoutPressed(ActionEvent event) throws IOException {
        dataHandler.setLoggedInUser("Username");
        //Scene wechsel
        main.Main.loadScene("/viewctrl/view_login.fxml");

        //Title ändern
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");
    }
    private void updateVBox() {
        vBox.getChildren().clear();

        Button btnAddTodo = new Button("ToDo hinzufügen");
        btnAddTodo.setPrefWidth(150);

        btnAddTodo.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();

            dialog.setTitle("Neues ToDo");
            dialog.setHeaderText(null);
            dialog.setContentText("ToDo:");

            dialog.showAndWait().ifPresent(text -> {

                dataHandler.getUser(dataHandler.getLoggedInUser())
                        .getUserToDos()
                        .add(new ToDo(text));

                updateVBox();
            });
        });

        vBox.getChildren().add(btnAddTodo);

        for (ToDo x : dataHandler.getUser(dataHandler.getLoggedInUser()).getUserToDos()) {

            HBox hBox = new HBox(10);
            Label label = new Label(x.getName());

            Button button = new Button("Löschen");
            button.setPrefWidth(80);

            hBox.getChildren().addAll(label, button);

            button.setOnAction(e -> {

                dataHandler.getUser(dataHandler.getLoggedInUser())
                        .getUserToDos()
                        .remove(x);

                updateVBox();
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


        //das hier ist nur zum Testen
        dataHandler.getUser("Max").getUserToDos().add(new ToDo("MaxToDo1"));
        dataHandler.getUser("Max").getUserToDos().add(new ToDo("MaxToDo2"));
        dataHandler.getUser("Max").getUserToDos().add(new ToDo("MaxToDo3"));
        dataHandler.getUser("Max").getUserToDos().add(new ToDo("MaxToDo4"));
        dataHandler.getUser("simon").getUserToDos().add(new ToDo("simonToDo1"));

        //Das muss eigentlich bei einem jeden refresh aufgerufen werden
        updateVBox();
    }
}
