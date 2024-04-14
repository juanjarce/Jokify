package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
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
    private Label lblArtistName;

    // Attributes of the class.
    private Song selectedSong;
    private MenuController menuController;
    private final ObservableList<Song> songObservableList = FXCollections.observableArrayList();

    /**
     * Initialize content for the controller of the interface.
     * @param selectedArtist Artist selected by the user.
     */
    public void init(Artist selectedArtist, MenuController menuController) {
        //Auxiliary attributes of the class.
        this.menuController = menuController;
        //Artist's songs.
        tableViewSong.getItems().clear();
        tableViewSong.setItems(getArtistSongs(selectedArtist));
        lblArtistName.setText(selectedArtist.getName());
    }

    /**
     * Get the songs of an artist.
     * @param selectedArtist Artist selected by the user, to get the songs.
     * @return List of songs of the artist.
     */
    private ObservableList<Song> getArtistSongs(Artist selectedArtist) {
        songObservableList.clear();
        songObservableList.addAll(selectedArtist.getSongs().toList());
        return songObservableList;
    }

    /**
     * Initialize content for the artis controller.
     * @param url obligatory parameter.
     * @param resourceBundle obligatory parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Data in the tableView.
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<>("album"));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<>("durationOnSeconds"));
        //Selection of a song on the table.
        tableViewSong.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSong = newSelection;
                selectedSong = tableViewSong.getSelectionModel().getSelectedItem();
                // Show the cover of the song.
                Image coverImage = new Image(selectedSong.getCover());
                imageViewCoverSong.setImage(coverImage);
            }
        });
    }

    /**
     * Shows the song to the user.
     */
    @FXML
    void showSong() {
        menuController.setCurrentSong(selectedSong);
    }

}
