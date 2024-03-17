package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController implements Initializable {

    //Elements fot the manage of artist

    @FXML
    private TextField txtNameArtist;

    @FXML
    private CheckBox checkBoxIsGroup;

    @FXML
    private TextField txtNationalityArtist;

    @FXML
    private Button btnAddArtist;

    @FXML
    private Button btnNewArtist;

    @FXML
    private TableView<?> tableViewArtist;

    @FXML
    private TableColumn<?, ?> columnCodeArtist;

    @FXML
    private TableColumn<?, ?> columnNameArtist;

    @FXML
    private TableColumn<?, ?> columnNationalityArtist;

    @FXML
    private TableColumn<?, ?> columnIsGroup;

    @FXML
    private Button btnDeleteArtist;

    @FXML
    private Button btnUpdateArtist;

    //Element for the manage of a song

    @FXML
    private TextField txtNameSong;

    @FXML
    private TextField txtAlbumSong;

    @FXML
    private Button btnAddSong;

    @FXML
    private Button btnNewSong;

    @FXML
    private TableView<?> tableViewSong;

    @FXML
    private TableColumn<?, ?> columnCodeSong;

    @FXML
    private TableColumn<?, ?> columnNameSong;

    @FXML
    private TableColumn<?, ?> columnArtistSong;

    @FXML
    private Button btnDeleteSong;

    @FXML
    private Button btnUpdateSong;

    @FXML
    private TextField txtCoverSong;

    @FXML
    private TextField txtYearSong;

    @FXML
    private TextField txtDurationSong;

    @FXML
    private TextField txtURLSong;

    @FXML
    private ComboBox<?> comboBoxGenreSong;

    @FXML
    private TextField txtArtistNameSong;

    //Elements for the manage of the dynamic content

    @FXML
    private Button btnShowDownloadInfo;

    @FXML
    private Button btnShowAddSong;

    @FXML
    private Button btnShowAddArtist;

    @FXML
    private Button btnCloseSesion;

    @FXML
    private BorderPane contentPane;

    //Aux variables
    private Stage stage;
    private LoginController loginController;
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
     */
    public void init(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
    }

    /**
     * Initialize some content for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Close the sesion of the admin
     * @param event
     */
    @FXML
    void closeSesion(ActionEvent event) {
        loginController.show();
        this.stage.close();
    }

    /**
     * Shows the dynamic content for the addArtist functionality
     * @param event
     */
    @FXML
    void showAddArtist(ActionEvent event) throws IOException {
        Node newContent = FXMLLoader.load(getClass().getResource("/views/ShowAddArtist.fxml"));

        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the addSong functionality
     * @param event
     */
    @FXML
    void showAddSong(ActionEvent event) throws IOException {
        Node newContent = FXMLLoader.load(getClass().getResource("/views/ShowAddSong.fxml"));

        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the downloadInfo functionality
     * @param event
     */
    @FXML
    void showDownloadInfo(ActionEvent event) throws IOException {
        Node newContent = FXMLLoader.load(getClass().getResource("/views/ShowDownloadInfo.fxml"));

        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Adds an artist
     * @param event
     */
    @FXML
    void addArtist(ActionEvent event) {

    }

    /**
     * Deletes the artist that is selected
     * @param event
     */
    @FXML
    void deleteArtist(ActionEvent event) {

    }

    /**
     * Clear the fields of the artist
     * @param event
     */
    @FXML
    void newArtist(ActionEvent event) {

    }

    /**
     * Uptade the info of an artist
     * @param event
     */
    @FXML
    void updateArtist(ActionEvent event) {

    }

    /**
     * Adds a song
     * @param event
     */
    @FXML
    void addSong(ActionEvent event) {

    }

    /**
     * Deletes the selected song
     * @param event
     */
    @FXML
    void deleteSong(ActionEvent event) {

    }

    /**
     * Clear the fields of the song
     * @param event
     */
    @FXML
    void newSong(ActionEvent event) {

    }

    /**
     * Update the info of a song
     * @param event
     */
    @FXML
    void updateSong(ActionEvent event) {

    }

}
