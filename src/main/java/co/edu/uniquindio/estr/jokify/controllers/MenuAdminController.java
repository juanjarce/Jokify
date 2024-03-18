package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.ArtistsException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController implements Initializable {

    //Elements fot the manage of artist
    private ObservableList<Artist> listArtistData = FXCollections.observableArrayList();;
    private ObservableList<Song> listSongData = FXCollections.observableArrayList();;

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
    private TableView<Artist> tableViewArtist;

    @FXML
    private TableColumn<Artist, String> columnCodeArtist;

    @FXML
    private TableColumn<Artist, String> columnNameArtist;

    @FXML
    private TableColumn<Artist, String> columnNationalityArtist;

    @FXML
    private TableColumn<Artist, Boolean> columnIsGroup;

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
    private TableView<Song> tableViewSong;

    @FXML
    private TableColumn<Song, String> columnCodeSong;

    @FXML
    private TableColumn<Song, String> columnNameSong;

    @FXML
    private TableColumn<Song, String> columnArtistSong;

    @FXML
    private TableColumn<Song, String> columnAlbumSong;

    @FXML
    private TableColumn<Song, Integer> columnYearSong;

    @FXML
    private TableColumn<Song, Integer> columnDurationSong;

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

        //Initialize Artists ---------------------------------------------------------------------------------------------
        //Defining columns
        this.columnCodeArtist.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.columnNameArtist.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnNationalityArtist.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
        this.columnIsGroup.setCellValueFactory(new PropertyValueFactory<>("isPartOfBand"));

        // Populate table with artist data
        tableViewArtist.setItems(store.getArtistList().toObservableList());
        //----------------------------------------------------------------------------------------------------------------


        //Initialize Songs ---------------------------------------------------------------------------------------------
        //Defining columns
//        this.columnCodeSong.setCellValueFactory(new PropertyValueFactory<>("code"));
//        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
//        this.columnArtistSong.setCellValueFactory(new PropertyValueFactory<>("artistName"));
//        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<>("album"));
//        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<>("year"));
//        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<>("durationOnSeconds"));
        //----------------------------------------------------------------------------------------------------------------

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

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    //METHODS for Artists managment ------------------------------------------------------------------------------------
    // Method to update table view with current artist list
    private void updateArtistTable() {
        tableViewArtist.setItems(store.getArtistList().toObservableList());
    }

    // Method to clear input fields
    private void clearArtistFields() {
        txtNameArtist.clear();
        txtNationalityArtist.clear();
        checkBoxIsGroup.setSelected(false);
    }

    /**
     * Adds an artist
     * @param event
     */
    @FXML
    void addArtist(ActionEvent event) {
        String name = txtNameArtist.getText();
        String nationality = txtNationalityArtist.getText();
        boolean isGroup = checkBoxIsGroup.isSelected();
        try {
            Artist newArtist = new Artist(name, nationality, isGroup);
            store.createArtist(newArtist);
            updateArtistTable();
            clearArtistFields();
        } catch (ArtistsException e) {
            mostrarMensaje("ERROR", "Error agregando al artista", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    /**
     * Deletes the artist that is selected
     * @param event
     */
    @FXML
    void deleteArtist(ActionEvent event) {
        Artist selectedArtist = tableViewArtist.getSelectionModel().getSelectedItem();
        if (selectedArtist != null) {
            try {
                store.deleteArtist(selectedArtist.getCode());
                updateArtistTable();
                clearArtistFields();
            } catch (ArtistsException e) {
                mostrarMensaje("ERROR", "Error eliminando al artista", e.getMessage(), Alert.AlertType.WARNING);

            }
        }
    }

    /**
     * Clear the fields of the artist
     * @param event
     */
    @FXML
    void newArtist(ActionEvent event) {
        clearArtistFields();
    }

    /**
     * Uptade the info of an artist
     * @param event
     */
    @FXML
    void updateArtist(ActionEvent event) {
        Artist selectedArtist = tableViewArtist.getSelectionModel().getSelectedItem();
        if (selectedArtist != null) {
            String name = txtNameArtist.getText();
            String nationality = txtNationalityArtist.getText();
            boolean isGroup = checkBoxIsGroup.isSelected();
            try {
                Artist updatedArtist = new Artist(selectedArtist.getCode(), name, nationality, isGroup);
                store.updateArtist(updatedArtist);
                updateArtistTable();
            } catch (ArtistsException e) {
                mostrarMensaje("ERROR", "Error actualizando al artista", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

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
