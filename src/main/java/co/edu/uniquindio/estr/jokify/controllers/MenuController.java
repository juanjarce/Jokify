package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button btnJokify;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnLibrary;

    @FXML
    private Button btnCloseSesion;

    @FXML
    private ImageView imgViewCover;

    @FXML
    private Label lblArtistName;

    @FXML
    private Label lblSongName;

    @FXML
    private Button btnAddFavoriteSong;

    @FXML
    private Button btnRemoveFavoriteSong;

    @FXML
    private Button btnPlaySong;

    @FXML
    private BorderPane contentPane;


    //Aux variables
    private Stage stage;
    private LoginController loginController;
    private User currentUser;
    private Song currentSong;
    private final Store store = Store.getInstance();

    /**
     * Sets the curren stage
     * @param primaryStage
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Show the current stage
     */
    public void show() {
        stage.show();
    }

    /**
     * Init the stage and the loginController
     * @param stage
     * @param loginController
     * @param currrentUser
     */
    public void init(Stage stage, LoginController loginController, User currrentUser) {
        this.stage = stage;
        this.loginController = loginController;
        this.currentUser = currrentUser;
    }

    /**
     * Intizize conten for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAddFavoriteSong.setVisible(false);
        btnRemoveFavoriteSong.setVisible(false);
        btnPlaySong.setVisible(false);
    }

    /**
     * Close the sesion of the current user
     * @param event
     */
    @FXML
    void closeSesion(ActionEvent event) {
        loginController.show();
        this.stage.close();
    }

    /**
     * Shows the dynamic content for the Jokify app
     * @param event
     */
    @FXML
    void showJokify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowJokify.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        ShowJokifyController controller = loader.getController();
        //Move the user to the controller
        controller.init(currentUser, this);
        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the library of the user
     * @param event
     */
    @FXML
    void showLibrary(ActionEvent event) throws IOException {
        showLibraryUser();
    }

    /**
     * Shows the dynamic content for the library of the user
     * @throws IOException
     */
    public void showLibraryUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowLibrary.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        ShowLibraryController controller = loader.getController();
        //Move the user to the controller
        controller.init(currentUser, this);
        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows the list of songs of the app
     * @param songs
     * @throws IOException
     */
    public void showSongs(ArrayList<Song> songs) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SongList.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        SongListController controller = loader.getController();
        //Move the user to the controller
        controller.init(currentUser, this, songs);
        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }


    /**
     * Shows the dynamic content for the search property of the app
     * @param event
     */
    @FXML
    void showSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowSearch.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        ShowSearchController controller = loader.getController();
        //Move the user to the controller
        controller.init(currentUser, this);
        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows an interface that contains info of an artist
     * @param selectedArtist
     * @throws IOException
     */
    public void showAtist(Artist selectedArtist) throws IOException {
        if (selectedArtist != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowArtist.fxml"));
            Parent newContent = loader.load();

            //Get the controller of the FXML file
            ShowArtistController controller = loader.getController();
            //Move the user to the controller
            controller.init(currentUser, selectedArtist, this);
            //Apply animations for the content
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

            contentPane.setCenter(newContent);
        } else {
            showMessage("Jokify", "Artistas", "Por favor selecciona un artista en la tabla", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void addFavoriteSong(ActionEvent event) {

    }

    @FXML
    void removeFavoriteSong(ActionEvent event) {

    }

    @FXML
    void playSong(ActionEvent event) {

    }

    /**
     * Sets the dynamic menu interface to show the user information of a selected song
     */
    public void setCurrentSong(Song currentSong) {
        if (currentSong != null) {
            this.currentSong = currentSong;
            //Shows the song information on the interface
            Image coverImage = new Image(currentSong.getCover());
            lblArtistName.setText(currentSong.getArtistName());
            lblSongName.setText(currentSong.getName());
            imgViewCover.setImage(coverImage);
            btnAddFavoriteSong.setVisible(true);
            btnRemoveFavoriteSong.setVisible(true);
            btnPlaySong.setVisible(true);
        }
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
