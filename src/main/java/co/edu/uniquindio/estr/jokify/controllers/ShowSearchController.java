package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.SearchException;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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

    /**
     * Search the content that's in the search textField
     * @param event
     */
    @FXML
    void search(ActionEvent event) {
        try {
            String search = txtSearch.getText();
            //Creates all the content that can be showed
            List<Song> songs = store.getSongsSearch(search);
            showSongs(songs);
        } catch (SearchException e) {
            showMessage("Jokify", "Busqueda", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void showSongs(List<Song> songs) {
        if (songs.size() != 0) {
            for (int i = 0; i < songs.size(); i++) {
                if (i == 0 && songs.get(i) != null) {
                    lblRS1.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS1.setImage(aux);
                    imageRS1.setFitWidth(200);
                    imageRS1.setFitHeight(150);
                    imageRS1.setTranslateY(10);
                    animateFadeIn(imageRS1);
                } else if (i == 1 && songs.get(i) != null) {
                    lblRS2.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS2.setImage(aux);
                    imageRS2.setFitWidth(200);
                    imageRS2.setFitHeight(150);
                    imageRS2.setTranslateY(10);
                    animateFadeIn(imageRS2);
                } else if (i == 2 && songs.get(i) != null) {
                    lblRS3.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS3.setImage(aux);
                    imageRS3.setFitWidth(200);
                    imageRS3.setFitHeight(150);
                    imageRS3.setTranslateY(10);
                    animateFadeIn(imageRS3);
                } else if (i == 3 && songs.get(i) != null) {
                    lblRS4.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS4.setImage(aux);
                    imageRS4.setFitWidth(200);
                    imageRS4.setFitHeight(150);
                    imageRS4.setTranslateY(10);
                    animateFadeIn(imageRS4);
                } else if (i == 4 && songs.get(i) != null) {
                    lblRS5.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS5.setImage(aux);
                    imageRS5.setFitWidth(200);
                    imageRS5.setFitHeight(150);
                    imageRS5.setTranslateY(10);
                    animateFadeIn(imageRS5);
                }
            }
        }
    }

    /**
     * Applies a fade in animation to the specified node
     * @param node
     */
    private void animateFadeIn(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    void showMoreAtists(ActionEvent event) {

    }

    @FXML
    void showMoreSongs(ActionEvent event) {

    }

    /**
     * show a message for the user
     * @param title
     * @param header
     * @param content
     * @param alertType
     */
    private void showMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
