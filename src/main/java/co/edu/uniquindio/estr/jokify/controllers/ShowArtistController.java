package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ShowArtistController implements Initializable {

    @FXML
    private TableView<Song> tableViewSong;

    @FXML
    private TableColumn<Song, String> columnNameSong;

    @FXML
    private TableColumn<Song, String> columnAlbumSong;

    @FXML
    private TableColumn<Song, Integer> columnYearSong;

    @FXML
    private TableColumn<Song, Integer> columnDurationSong;

    @FXML
    private ImageView imageViewCoverSong;

    @FXML
    private Button btnShowSong;

    @FXML
    private Label lblArtistName;

    //Aux variables
    private User currentUser;
    private Artist selectedArtist;
    private Song selectedSong;
    private ObservableList<Song> songObservableList = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();

    /**
     * Init content for the controller
     * @param currentUser
     * @param selectedArtist
     */
    public void init(User currentUser, Artist selectedArtist) {
        this.currentUser = currentUser;
        this.selectedArtist = selectedArtist;
        //Artist's songs
        tableViewSong.getItems().clear();
        tableViewSong.setItems(getArtistSongs(selectedArtist));
    }

    /**
     * Get's the songs of an artist
     * @param selectedArtist
     * @return
     */
    private ObservableList<Song> getArtistSongs(Artist selectedArtist) {
        songObservableList.clear();
        ArrayList<Song> songs = new ArrayList<>();
        songObservableList.addAll(selectedArtist.getSongs().toList());
        return songObservableList;
    }

    /**
     * Initialize content for the artis controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Data in the tableView
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<Song, Integer>("durationOnSeconds"));
        //Selection of a song on the table
        tableViewSong.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSong = newSelection;
                selectedSong = tableViewSong.getSelectionModel().getSelectedItem();
                // Show the cover of the song
                Image coverImage = new Image(selectedSong.getCover());
                imageViewCoverSong.setImage(coverImage);
            }
        });
    }

    @FXML
    void showSong(ActionEvent event) {

    }

}
