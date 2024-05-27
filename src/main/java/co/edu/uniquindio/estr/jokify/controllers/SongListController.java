package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SongListController implements Initializable {
    @FXML
    private ImageView imageViewCoverSong;

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

    private MenuController menuController;
    private Song selectedSong;
    private Artist selectedArtist;
    private ArrayList<Song> songs;
    private final ObservableList<Song> songObservableList = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();

    /**
     * Initializes content for the controller.
     */
    public void init(MenuController menuController, ArrayList<Song> songs) {
        // Auxiliary variables.
        this.menuController = menuController;
        this.songs = songs;
        // Songs tableView
        tableViewSong.getItems().clear();
        tableViewSong.setItems(getSongs());
    }

    /**
     * Gets the songs to show in the interface.
     * @return the songs to show in the interface.
     */
    private ObservableList<Song> getSongs() {
        songObservableList.clear();
        songObservableList.addAll(songs);
        return songObservableList;
    }

    /**
     * Initializes content for the controller.
     * @param url obligatory parameter.
     * @param resourceBundle obligatory parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Data in the tableView.
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
     * Shows the information of an artist.
     */
    @FXML
    void showArtist() throws IOException {
        if (selectedArtist != null) {
            menuController.showArtist(selectedArtist);
        } else {
            showMessage();
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
     * Show a message for the user.
     * Seems like this method did not have to use the parameters...
     */
    private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jokify");
        alert.setHeaderText("Artistas.");
        alert.setContentText("Por favor selecciona una canción de la tabla.");
        alert.showAndWait();
    }
}
