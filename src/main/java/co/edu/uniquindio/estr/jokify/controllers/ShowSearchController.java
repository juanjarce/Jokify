package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class ShowSearchController implements Initializable {

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private FlowPane flowPaneRelatedSongs;

    @FXML
    private StackPane stackRS1;

    @FXML
    private ImageView imageRS1;

    @FXML
    private Label lblRS1;

    @FXML
    private StackPane stackRS2;

    @FXML
    private ImageView imageRS2;

    @FXML
    private Label lblRS2;

    @FXML
    private StackPane stackRS3;

    @FXML
    private ImageView imageRS3;

    @FXML
    private Label lblRS3;

    @FXML
    private StackPane stackRS4;

    @FXML
    private ImageView imageRS4;

    @FXML
    private Label lblRS4;

    @FXML
    private StackPane stackRS5;

    @FXML
    private ImageView imageRS5;

    @FXML
    private Label lblRS5;

    @FXML
    private FlowPane flowPaneRelatedArtist;

    @FXML
    private StackPane stackRA1;

    @FXML
    private ImageView imageRA1;

    @FXML
    private Label lblRA1;

    @FXML
    private StackPane stackRA2;

    @FXML
    private ImageView imageRA2;

    @FXML
    private Label lblRA2;

    @FXML
    private StackPane stackFS31;

    @FXML
    private ImageView imageRA3;

    @FXML
    private Label lblRA3;

    @FXML
    private StackPane stackRA4;

    @FXML
    private ImageView imageRA4;

    @FXML
    private Label lblRA4;

    @FXML
    private StackPane stackRA5;

    @FXML
    private ImageView imageRA5;

    @FXML
    private Label lblRA5;

    @FXML
    private Button btnShowMoreSongs;

    @FXML
    private Button btnShowMoreArtist;

    //Aux variables
    private User currentUser;
    private final Store store = Store.getInstance();

    /**
     * Get some content of the menuController and init content
     * @param currentUser
     */
    public void init(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Initialize content for the search interface
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void showMoreAtists(ActionEvent event) {

    }

    @FXML
    void showMoreSongs(ActionEvent event) {

    }
}
