package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.DataHandler;
import model.FileHandler;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller1 implements Initializable {
    String datei_users = "D:\\SIMON\\3BHIT\\SEW\\Üben\\4. PLF\\Ueben_1\\src\\datei.txt";

    DataHandler dataHandler;
    FileHandler fileHandler;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btnCalc;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnSave;


    @FXML
    void btnLoadPressed(ActionEvent event) {
        String str;
        dataHandler.setListEingelesen(fileHandler.dateiEinlesen(datei_users));
        str = dataHandler.getListEingelesen().toString();
        textArea.setText(dataHandler.printList());
    }

    @FXML
    void btnCalcPressed(ActionEvent event) throws IOException {
        main.Main.loadScene("/viewctrl/view2.fxml");

    }


    @FXML
    void btnSavePressed(ActionEvent event) {

//        fileHandler.dateiSchreiben(dateiZiel, dataHandler.getListBerechnet());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String hallo = "aölkjsdf";
        System.out.println(hallo.matches("[n]"));
        dataHandler = new DataHandler();
        fileHandler = new FileHandler();
    }
}
