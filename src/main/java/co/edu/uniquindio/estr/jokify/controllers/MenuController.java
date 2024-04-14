package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

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


    //Auxiliary attributes of the class.
    private Stage stage;
    private LoginController loginController;
    private User currentUser;
    private Song currentSong;
    private final Store store = Store.getInstance();

    /**
     * Show the current stage
     */
    public void show() {
        stage.show();
    }

    /**
     * Initialize the stage and the login interface controller.
     * @param stage current stage that is shown and given as a parameter.
     * @param loginController login interface controller that is given as a parameter.
     * @param currrentUser current user that is given as a parameter. Used to keep track of the current user and not lose the session.
     */
    public void init(Stage stage, LoginController loginController, User currrentUser) {
        this.stage = stage;
        this.loginController = loginController;
        this.currentUser = currrentUser;
    }

    /**
     * Initialize the content that is used in the controller.
     * @param url parameter that is not used but is necessary for the implementation of the method.
     * @param resourceBundle parameter that is not used but is necessary for the implementation of the method.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAddFavoriteSong.setVisible(false);
        btnRemoveFavoriteSong.setVisible(false);
        btnPlaySong.setVisible(false);
    }

    /**
     * Close the sesion of the current user.
     */
    @FXML
    void closeSesion() {
        loginController.show();
        this.stage.close();
    }

    /**
     * Shows the dynamic content of the Jokify app.
     */
    @FXML
    void showJokify() throws IOException {
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
     * Shows the dynamic content for the library of the user.
     */
    @FXML
    void showLibrary() throws IOException {
        showLibraryUser();
    }

    /**
     * Shows the dynamic content for the library of the user
     * @throws IOException if the interface is not found or the path is incorrect.
     */
    public void showLibraryUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowLibrary.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        ShowLibraryController controller = loader.getController();
        //Move the user to the controller
        controller.init(currentUser, this);
        //Apply animations for the content
        animationDisplay(newContent);
        // Sets the content in the content pane.
        contentPane.setCenter(newContent);
    }

    /**
     * Shows the list of songs of the application.
     * @param songs list of songs to be shown.
     * @throws IOException if the interface is not found or the path is incorrect.
     */
    public void showSongs(ArrayList<Song> songs) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SongList.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        SongListController controller = loader.getController();
        //Move the user to the controller
        controller.init(this, songs);
        //Apply animations for the content
        animationDisplay(newContent);
        // Sets the content in the content pane.
        contentPane.setCenter(newContent);
    }


    /**
     * Shows the dynamic content for the search property of the app.
     */
    @FXML
    void showSearch() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowSearch.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        ShowSearchController controller = loader.getController();
        //Move the user to the controller
        controller.init(this);
        //Apply animations for the content
        animationDisplay(newContent);
        // Sets the content in the content pane.
        contentPane.setCenter(newContent);
    }

    /**
     * Shows an interface that contains infomartion about an artist.
     * @param selectedArtist the artist that is selected by the user.
     * @throws IOException if the interface is not found or the path is incorrect.
     */
    public void showArtist(Artist selectedArtist) throws IOException {
        if (selectedArtist != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowArtist.fxml"));
            Parent newContent = loader.load();

            //Get the controller of the FXML file
            ShowArtistController controller = loader.getController();
            //Move the user to the controller
            controller.init(selectedArtist, this);
            //Apply animations for the content
            animationDisplay(newContent);
            // Sets the content in the content pane.
            contentPane.setCenter(newContent);
        } else {
            showMessage("Artistas", "Por favor selecciona un artista en la tabla.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Adds a song to favorites.
     */
    @FXML
    void addFavoriteSong() {
        if (currentSong != null) {
            store.addSongToFavorites(currentUser, currentSong);
            showMessage("Canciones", "Cancion agregada a favoritos.", Alert.AlertType.INFORMATION);
            // Saves the favorites songs of the user in the store using a thread.
            SaveBinaryResource t1 = new SaveBinaryResource();
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            showMessage("Canciones", "Ocurrio un error agregando la canción a favoritos.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Removes a song from favorites.
     */
    @FXML
    void removeFavoriteSong() {
        if (currentSong != null) {
            store.removeSongFromFavorites(currentUser, currentSong);
            showMessage("Canciones", "Cancion eliminada de favoritos.", Alert.AlertType.INFORMATION);
        } else {
            showMessage("Canciones", "Ocurrio un error eliminando la canción de favoritos.", Alert.AlertType.INFORMATION);
        }
        // Saves the removed favorite song from the user so that the changes are saved in the store using a thread.
        SaveBinaryResource t1 = new SaveBinaryResource();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Plays the song that is selected by showing an emergent window.
     * Some songs can't be played due to YouTube politics: <a href="https://support.google.com/youtube/answer/6301625?hl=en">...</a>
     */
    @FXML
    void playSong() {

        // Check if there is a selected song.
        if(currentSong != null) {

            // Obtaining the "embed" link of the YouTube link.
            String youtubeEmbed = convertToEmbedUrl(currentSong.getYoutubeURL());
            try {
                // Create a new Stage for the video.
                Stage stage = new Stage();

                // Create and load the video.
                WebView webView = new WebView();
                webView.getEngine().load(youtubeEmbed);

                // Create a Scene containing the video while indicating its width and height.
                Scene scene = new Scene(webView, 640, 390);

                // Show the Stage with the Scene and a custom title.
                String title = currentSong.getName() + " - " + currentSong.getArtistName();
                stage.setTitle(title);
                stage.setScene(scene);
                stage.show();

            } catch(Exception e) {
                // If the video can't be played, show an error message.
                showMessage("Canciones", "No se puede reproducir la canción seleccionada: \n" + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Function to convert a YouTube video link into its embed correspondence.
     * @param youtubeUrl the YouTube video link to be converted.
     * @return the embed link of the YouTube video or null if the input is not valid or an exception is thrown.
     */
    public static String convertToEmbedUrl(String youtubeUrl) {
        // Check if the input URL is a valid YouTube watch URL
        if (youtubeUrl.startsWith("https://www.youtube.com/watch?v=")) {
            // Extract the video ID from the URL
            String videoId = youtubeUrl.substring("https://www.youtube.com/watch?v=".length());
            // Construct the embed URL using the video ID
            return "https://www.youtube.com/embed/" + videoId;
        } else {
            // If the input URL is not a valid YouTube watch URL, return null or throw an exception
            return null;
        }
    }

    /**
     * Sets the dynamic menu interface to show the user information of a selected song.
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
     * @param header   the header of the message.
     * @param content   the content of the message.
     * @param alertType the type of the message.
     */
    private void showMessage(String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Jokify");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays a little animation when changing the content of the interface.
     * @param newContent the new content to be displayed.
     */
    private void animationDisplay(Parent newContent) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
