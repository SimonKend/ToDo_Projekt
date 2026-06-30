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
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class Controller_logged_in_user implements Initializable {
    String datei_users = "users.txt";
    DataHandler dataHandler;
    FileHandler fileHandler;

    private String aktuelleKategorie = "Schule";

    @FXML private Button btnAddToDo;
    @FXML private Button btnLogout;
    @FXML private Button btnSchule;
    @FXML private Button btnArbeit;
    @FXML private Button btnZuHause;
    @FXML private Label lblAktuelleKategorie;
    @FXML private VBox vBox;
    @FXML private Label labelWelcomeMessage;
    @FXML private ListView<String> listViewUsernames;
    @FXML private Label lblAdminHeader;
    @FXML private MenuBar menuBar;
    @FXML private Menu toDoMenu;
    @FXML private Menu userMenu;
    @FXML private Menu infosMenu;

    @FXML
    void btnLogoutPressed(ActionEvent event) throws IOException {
        dataHandler.setLoggedInUser("Username");
        main.Main.loadScene("/viewctrl/view_login.fxml");
        main.Main.getPrimaryStage().setTitle("ToDo-Project: Login");
    }

    // Deine originalen Menu-Methoden
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

    // Kategorie-Wechsel Buttons
    @FXML
    void btnSchulePressed(ActionEvent event) {
        aktuelleKategorie = "Schule";
        lblAktuelleKategorie.setText("Schule");
        normalisiereButtonFarben();
        btnSchule.setStyle("-fx-background-color: #add8e6;");
        updateVBox();
    }

    @FXML
    void btnArbeitPressed(ActionEvent event) {
        aktuelleKategorie = "Arbeit";
        lblAktuelleKategorie.setText("Arbeit");
        normalisiereButtonFarben();
        btnArbeit.setStyle("-fx-background-color: #add8e6;");
        updateVBox();
    }

    @FXML
    void btnZuHausePressed(ActionEvent event) {
        aktuelleKategorie = "Zu Hause";
        lblAktuelleKategorie.setText("Zu Hause");
        normalisiereButtonFarben();
        btnZuHause.setStyle("-fx-background-color: #add8e6;");
        updateVBox();
    }

    private void normalisiereButtonFarben() {
        btnSchule.setStyle("");
        btnArbeit.setStyle("");
        btnZuHause.setStyle("");
    }

    @FXML
    void btnAddToDoPressed(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Neues ToDo");
        dialog.setHeaderText(null);
        dialog.setContentText("ToDo für " + aktuelleKategorie + ":");

        dialog.showAndWait().ifPresent(text -> {
            if(!text.trim().isEmpty()) {
                User u = dataHandler.getUser(dataHandler.getLoggedInUser());

                if (aktuelleKategorie.equals("Schule")) u.getSchuleOffen().add(new ToDo(text));
                else if (aktuelleKategorie.equals("Arbeit")) u.getArbeitOffen().add(new ToDo(text));
                else if (aktuelleKategorie.equals("Zu Hause")) u.getZuHauseOffen().add(new ToDo(text));

                updateVBox();
                fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());
            }
        });
    }

    private void updateVBox() {
        vBox.getChildren().clear();
        User u = dataHandler.getUser(dataHandler.getLoggedInUser());

        LinkedHashSet<ToDo> temporaerOffen = null;
        LinkedHashSet<ToDo> temporaerErledigt = null;

        if (aktuelleKategorie.equals("Schule")) {
            temporaerOffen = u.getSchuleOffen();
            temporaerErledigt = u.getSchuleErledigt();
        } else if (aktuelleKategorie.equals("Arbeit")) {
            temporaerOffen = u.getArbeitOffen();
            temporaerErledigt = u.getArbeitErledigt();
        } else if (aktuelleKategorie.equals("Zu Hause")) {
            temporaerOffen = u.getZuHauseOffen();
            temporaerErledigt = u.getZuHauseErledigt();
        }

        final LinkedHashSet<ToDo> offen = temporaerOffen;
        final LinkedHashSet<ToDo> erledigt = temporaerErledigt;

        // 1. OFFENE TODOS ZEICHNEN
        Label lblOffenHeader = new Label("Offen:");
        lblOffenHeader.setStyle("-fx-font-weight: bold;");
        vBox.getChildren().add(lblOffenHeader);

        for (ToDo x : offen) {
            HBox hBox = erstelleToDoZeile(x, "Abhaken", Color.LIGHTBLUE, e -> {
                offen.remove(x);
                erledigt.add(x); // In erledigt Liste verschieben
                updateVBox();
                fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());
            });
            vBox.getChildren().add(hBox);
        }

        vBox.getChildren().add(new Separator());

        // 2. ERLEDIGTE TODOS ZEICHNEN (Stehen darunter)
        Label lblErledigtHeader = new Label("Erledigt:");
        lblErledigtHeader.setStyle("-fx-font-weight: bold; -fx-text-fill: gray;");
        vBox.getChildren().add(lblErledigtHeader);

        for (ToDo x : erledigt) {
            HBox hBox = erstelleToDoZeile(x, "Löschen", Color.LIGHTGREEN, e -> {
                erledigt.remove(x); // Hier wird das erledigte ToDo permanent gelöscht
                updateVBox();
                fileHandler.dateiSchreiben(datei_users, dataHandler.getUsersSet());
            });
            Label l = (Label) hBox.getChildren().get(0);
            l.setStyle("-fx-text-fill: gray; -fx-strikethrough: true;");
            vBox.getChildren().add(hBox);
        }
    }

    private HBox erstelleToDoZeile(ToDo todo, String buttonText, Color farbe, javafx.event.EventHandler<ActionEvent> event) {
        HBox hBox = new HBox(10);
        hBox.setPrefHeight(40);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 15, 5, 15));
        hBox.setBackground(new Background(new BackgroundFill(farbe, new CornerRadii(8), Insets.EMPTY)));

        Label label = new Label(todo.getName());
        label.setPrefWidth(150);

        Button btn = new Button(buttonText);
        btn.setPrefWidth(80);
        btn.setOnAction(event);

        hBox.getChildren().addAll(label, btn);
        return hBox;
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
            listViewUsernames.getItems().clear();
            for (User u : dataHandler.getUsersSet()) {
                listViewUsernames.getItems().add(u.getUsername());
            }
        }
        updateVBox();
    }
}
