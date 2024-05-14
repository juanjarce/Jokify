package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
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
    private ComboBox<String> cbSortby;

    //Auxiliary variables.
    private User currentUser;
    private MenuController menuController;
    private Song selectedSong;
    private Artist selectedArtist;
    private final ObservableList<Song> songObservableList = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();

    /**
     * Initialize some content for the controller to use.
     * @param currentUser The current user logged in.
     */
    public void init(User currentUser, MenuController menuController) {
        this.currentUser = currentUser;
        this.menuController = menuController;
        //User's songs
        tableViewSong.getItems().clear();
        tableViewSong.setItems(getUserSongs(currentUser));
    }

    /**
     * Gets the favorite songs of the current user.
     * @param currentUser The current user logged in.
     * @return An observable list of the user's favorite songs.
     */
    private ObservableList<Song> getUserSongs(User currentUser) {
        songObservableList.clear();
        songObservableList.addAll(currentUser.getLinkedSong().toList());
        return songObservableList;
    }

    /**
     * Initialize the content for the controller to use.
     * @param url obligatory parameter.
     * @param resourceBundle obligatory parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize the sorting values in the Combo Box.
        cbSortby.getItems().addAll("Nombre", "Album", "Año","Duración", "Artista");

        // Action event listener for the Combo Box.
        initializeSortByComboBox();

        // Data in the table.
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<>("album"));
        this.columnArtistSong.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getArtistName()));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<>("durationOnSeconds"));
        // Selection of a song on the table.
        tableViewSong.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSong = newSelection;
                selectedSong = tableViewSong.getSelectionModel().getSelectedItem();
                selectedArtist = store.getArtistByName(selectedSong.getArtistName());
                // Show the cover of the song.
                Image coverImage = new Image(selectedSong.getCover());
                imageViewCoverSong.setImage(coverImage);
            }
        });
    }

    /**
     * Remove a song from the favorites list.
     */
    @FXML
    void removeFavorites() {
        if (selectedSong != null) {
            store.removeSongFromFavorites(currentUser, selectedSong);
            showMessage("Libreria", "Canción eliminada de favoritos.");

            // Save the Store content after removing the song from the favorites.
            SaveBinaryResource t1 = new SaveBinaryResource();
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Update the table and image.
            tableViewSong.getItems().clear();
            tableViewSong.setItems(getUserSongs(currentUser));
            imageViewCoverSong.setImage(null);
            selectedSong = null;
        } else {
            showMessage("Libreria", "Por favor selecciona una canción de la tabla.");
        }
    }

    /**
     * Shows the information of an artist that is selected in the table.
     */
    @FXML
    void showArtist() throws IOException {
        if (selectedArtist != null) {
            menuController.showArtist(selectedArtist);
        } else {
            showMessage("Artistas", "Por favor selecciona una canción en la tabla.");
        }
    }

    /**
     * Shows the song to the user.
     */
    @FXML
    void showSong() {
        menuController.setCurrentSong(selectedSong);
    }

    /**
     * Shows a message for the user to see.
     * Alert type is always information in this class.
     * @param header  The header of the message.
     * @param content The content of the message.
     */
    private void showMessage(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jokify");
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

    /**
     * Function to update the table view of the liked songs.
     * Used to show immediate changes to the user when redoing or undoing in the Library interface.
     */
    public void updateTableView() {
        tableViewSong.setItems(null);
        tableViewSong.layout();
        tableViewSong.setItems(getUserSongs(currentUser));
    }

}
