package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowJokifyController implements Initializable {


    @FXML
    private StackPane stackFS1;

    @FXML
    private ImageView imageFS1;

    @FXML
    private Label lblFS1;

    @FXML
    private StackPane stackFS2;

    @FXML
    private ImageView imageFS2;

    @FXML
    private Label lblFS2;

    @FXML
    private StackPane stackFS3;

    @FXML
    private ImageView imageFS3;

    @FXML
    private Label lblFS3;

    @FXML
    private StackPane stackFS4;

    @FXML
    private ImageView imageFS4;

    @FXML
    private Label lblFS4;

    @FXML
    private StackPane stackFS5;

    @FXML
    private ImageView imageFS5;

    @FXML
    private Label lblFS5;

    @FXML
    private StackPane stackNS1;

    @FXML
    private ImageView imageNS1;

    @FXML
    private Label lblNS1;

    @FXML
    private StackPane stackNS2;

    @FXML
    private ImageView imageNS2;

    @FXML
    private Label lblNS2;

    @FXML
    private StackPane stackNS3;

    @FXML
    private ImageView imageNS3;

    @FXML
    private Label lblNS3;

    @FXML
    private StackPane stackNS4;

    @FXML
    private ImageView imageNS4;

    @FXML
    private Label lblNS4;

    @FXML
    private StackPane stackNS5;

    @FXML
    private ImageView imageNS5;

    @FXML
    private Label lblNS5;

    // Attributes used in the class, mainly auxiliar.
    private MenuController menuController;
    private final Store store = Store.getInstance();

    /**
     * Get some content of the Menu Controller and initialize its content.
     * @param currentUser user that logged in.
     */
    public void init(User currentUser, MenuController menuController) {
        this.menuController = menuController;
        ArrayList<Song> songs2 = store.getFiveFavoriteSongs(currentUser);
        showFavoriteSongs(songs2);
        ArrayList<Song> songs1 = store.getFiveSongs();
        showRecomendedSongs(songs1);
    }

    /**
     * Shows the recomended songs in the Flow Pane.
     * @param songs list of songs that the Store has save of.
     */
    private void showRecomendedSongs(ArrayList<Song> songs) {
        if (!songs.isEmpty()) {
            for (int i = 0; i < songs.size(); i++) {
                if (i == 0 && songs.get(i) != null) {
                    lblNS1.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS1.setImage(aux);
                    imageNS1.setFitWidth(200);
                    imageNS1.setFitHeight(150);
                    imageNS1.setTranslateY(10);
                    animateFadeIn(imageNS1);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackNS1.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 1 && songs.get(i) != null) {
                    lblNS2.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS2.setImage(aux);
                    imageNS2.setFitWidth(200);
                    imageNS2.setFitHeight(150);
                    imageNS2.setTranslateY(10);
                    animateFadeIn(imageNS2);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackNS2.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 2 && songs.get(i) != null) {
                    lblNS3.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS3.setImage(aux);
                    imageNS3.setFitWidth(200);
                    imageNS3.setFitHeight(150);
                    imageNS3.setTranslateY(10);
                    animateFadeIn(imageNS3);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack
                    stackNS3.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 3 && songs.get(i) != null) {
                    lblNS4.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS4.setImage(aux);
                    imageNS4.setFitWidth(200);
                    imageNS4.setFitHeight(150);
                    imageNS4.setTranslateY(10);
                    animateFadeIn(imageNS4);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackNS4.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 4 && songs.get(i) != null) {
                    lblNS5.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS5.setImage(aux);
                    imageNS5.setFitWidth(200);
                    imageNS5.setFitHeight(150);
                    imageNS5.setTranslateY(10);
                    animateFadeIn(imageNS5);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack
                    stackNS5.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                }
            }
        }
    }

    /**
     * Shows the favorite songs of the user in the Flow Pane.
     * @param songs favorite songs of the user to be displayed.
     */
    private void showFavoriteSongs(ArrayList<Song> songs) {
        if (!songs.isEmpty()) {
            for (int i = 0; i < songs.size(); i++) {
                if (i == 0 && songs.get(i) != null) {
                    lblFS1.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageFS1.setImage(aux);
                    imageFS1.setFitWidth(200);
                    imageFS1.setFitHeight(150);
                    imageFS1.setTranslateY(10);
                    animateFadeIn(imageFS1);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackFS1.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 1 && songs.get(i) != null) {
                    lblFS2.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageFS2.setImage(aux);
                    imageFS2.setFitWidth(200);
                    imageFS2.setFitHeight(150);
                    imageFS2.setTranslateY(10);
                    animateFadeIn(imageFS2);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackFS2.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 2 && songs.get(i) != null) {
                    lblFS3.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageFS3.setImage(aux);
                    imageFS3.setFitWidth(200);
                    imageFS3.setFitHeight(150);
                    imageFS3.setTranslateY(10);
                    animateFadeIn(imageFS3);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackFS3.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 3 && songs.get(i) != null) {
                    lblFS4.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageFS4.setImage(aux);
                    imageFS4.setFitWidth(200);
                    imageFS4.setFitHeight(150);
                    imageFS4.setTranslateY(10);
                    animateFadeIn(imageFS4);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackFS4.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 4 && songs.get(i) != null) {
                    lblFS5.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageFS5.setImage(aux);
                    imageFS5.setFitWidth(200);
                    imageFS5.setFitHeight(150);
                    imageFS5.setTranslateY(10);
                    animateFadeIn(imageFS5);
                    Song currentSong = songs.get(i);
                    //Add functionality to the stack.
                    stackFS5.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                }
            }
        }
    }

    /**
     * Applies a fade in animation to the specified node.
     * @param node node which is animated.
     */
    private void animateFadeIn(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    /**
     * Shows the songs in the app for a user.
     */
    @FXML
    void discoverMore() throws IOException {
        menuController.showSongs(store.getSongList().toList());
    }

    /**
     * Shows the favorite songs of a user.
     */
    @FXML
    void showMore() throws IOException {
        menuController.showLibraryUser();
    }

    /**
     * Necessary to implement this method for the class to work.
     * Should this really implement Initializable?
     * @param url no comment.
     * @param resourceBundle no comment.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
