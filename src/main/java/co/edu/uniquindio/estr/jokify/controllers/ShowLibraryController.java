package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ShowLibraryController implements Initializable {

    @FXML
    private TableView<Song> tableViewSong;

    @FXML
    private TableColumn<Song, String> columnNameSong;

    @FXML
    private TableColumn<Song, String> columnAlbumSong;

    @FXML
    private TableColumn<Song, String> columnArtistSong;

    @FXML
    private TableColumn<Song, Integer> columnYearSong;

    @FXML
    private TableColumn<Song, Integer> columnDurationSong;

    @FXML
    private ImageView imageViewCoverSong;

    @FXML
    private Button btnShowSong;

    @FXML
    private Button btnShowArtist;

    @FXML
    private Button btnRemoveFavorites;

    @FXML
    private ComboBox<String> cbSortby;

    //Aux variables
    private User currentUser;
    private MenuController menuController;
    private Song selectedSong;
    private Artist selectedArtist;
    private ObservableList<Song> songObservableList = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();

    /**
     * Init content for the controller
     * @param currentUser
     */
    public void init(User currentUser, MenuController menuController) {
        this.currentUser = currentUser;
        this.menuController = menuController;
        //User's songs
        tableViewSong.getItems().clear();
        tableViewSong.setItems(getUserSongs(currentUser));
    }

    /**
     * Gets the favorite songs of the currentUser
     * @param currentUser
     * @return
     */
    private ObservableList<Song> getUserSongs(User currentUser) {
        songObservableList.clear();
        songObservableList.addAll(currentUser.getLinkedSong().toList());
        return songObservableList;
    }

    /**
     * Init content for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize the sorting values in the combobox
        cbSortby.getItems().addAll("Nombre", "Album", "Año","Duración", "Artista");

        // Action event listener for the combobox
        initializeSortByComboBox();

        //Data in the tableView
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        this.columnArtistSong.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getArtistName()));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<Song, Integer>("durationOnSeconds"));
        //Selection of a song on the table
        tableViewSong.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSong = newSelection;
                selectedSong = tableViewSong.getSelectionModel().getSelectedItem();
                selectedArtist = store.getArtistByName(selectedSong.getArtistName());
                // Show the cover of the song
                Image coverImage = new Image(selectedSong.getCover());
                imageViewCoverSong.setImage(coverImage);
            }
        });
    }

    /**
     * Remove a song from the favorites list
     * @param event
     */
    @FXML
    void removeFavorites(ActionEvent event) {
        if (selectedSong != null) {
            store.removeSongFromFavorites(currentUser, selectedSong);
            showMessage("Jokify", "Libreria", "Canción eliminada de favoritos", Alert.AlertType.INFORMATION);

            //------------------------------------------------------------------------------------------------------------------------------------------------
            // Save the Store content
            //BinaryResorce()
            SaveBinaryResource t1 = new SaveBinaryResource();
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //------------------------------------------------------------------------------------------------------------------------------------------------

            //Update the tableView and image
            tableViewSong.getItems().clear();
            tableViewSong.setItems(getUserSongs(currentUser));
            imageViewCoverSong.setImage(null);
            selectedSong = null;
        } else {
            showMessage("Jokify", "Libreria", "Por favor selecciona una cancioón en la tabla", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Shows the information of an artist
     * @param event
     */
    @FXML
    void showArtist(ActionEvent event) throws IOException {
        if (selectedArtist != null) {
            menuController.showAtist(selectedArtist);
        } else {
            showMessage("Jokify", "Artistas", "Por favor selecciona una canción en la tabla", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Shows the song to the user
     * @param event
     */
    @FXML
    void showSong(ActionEvent event) {
        menuController.setCurrentSong(selectedSong);
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

    /**
     *  Function to check if a sort option has been selected.
     */
    private void initializeSortByComboBox() {
        cbSortby.setOnAction(event -> {
            String selectedOption = cbSortby.getSelectionModel().getSelectedItem();
            if(selectedOption!=null) {
                sortSongsBy(selectedOption);
            }
        });
    }

    /**
     * Function to sort the liked songs table of the user by a selected sort option.
     * For String, it will sort it alphabetically.
     * For Integer, it will sort it from smallest to largest.
     */
    private void sortSongsBy(String sortOption) {

        // Obtaining the liked songs observablelist from the user.
        ObservableList<Song> likedSongsList = getUserSongs(currentUser);

        // Swith case to go all over the combobox options
        // Props to the Comparator class. Made this a million times easier.
        switch (sortOption) {

            case "Nombre":
                likedSongsList.sort(Comparator.comparing(Song::getName));
                break;
            case "Album":
                likedSongsList.sort(Comparator.comparing(Song::getAlbum));
                break;
            case "Año":
                likedSongsList.sort(Comparator.comparing(Song::getYear));
                break;
            case "Duración":
                likedSongsList.sort(Comparator.comparing(Song::getDurationOnSeconds));
                break;
            case "Artista":
                likedSongsList.sort(Comparator.comparing(Song::getArtistName));
                break;
        }
        tableViewSong.setItems(likedSongsList);
    }

}
