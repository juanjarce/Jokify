package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowStatisticsController implements Initializable {

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtArtist;

    private final Store store = Store.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.txtGenre.setDisable(true);
        this.txtArtist.setDisable(true);
        this.txtGenre.setText(store.findMostRepeatedGenre().toString());

    }
}
