package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowStatisticsController implements Initializable {

    /*
    José David Amaya, qué es esta clase de mierda!? No tiene sentido, no hace nada, no tiene lógica.
    No tiene sentido que exista, no tiene sentido que se haya creado.
    ( Lo siento, no pude evitarlo, me salió del alma. )
    ( No me odies, por favor. )
     */

    // Injected components.
    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtArtist;

    // Auxiliary variables.
    private final Store store = Store.getInstance();

    /**
     * Initialize some content for the controller to use.
     * @param url obligatory variable.
     * @param resourceBundle obligatory variable.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.txtGenre.setDisable(true);
        this.txtArtist.setDisable(true);
        this.txtGenre.setText(store.findMostRepeatedGenre().toString());
        this.txtArtist.setText(store.findMostLikedSong().getArtistName());
    }
}
