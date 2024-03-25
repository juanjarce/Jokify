package co.edu.uniquindio.estr.jokify.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ShowLibraryController {
    @FXML
    private TableView<?> tableViewSong;

    @FXML
    private TableColumn<?, ?> columnNameSong;

    @FXML
    private TableColumn<?, ?> columnAlbumSong;

    @FXML
    private TableColumn<?, ?> columnArtistSong;

    @FXML
    private TableColumn<?, ?> columnYearSong;

    @FXML
    private TableColumn<?, ?> columnDurationSong;

    @FXML
    private ImageView imageViewCoverSong;

    @FXML
    private Button btnShowSong;

    @FXML
    private Button btnShowArtist;

    @FXML
    private Button btnRemoveFavorites;

    @FXML
    void removeFavorites(ActionEvent event) {

    }

    @FXML
    void showArtist(ActionEvent event) {

    }

    @FXML
    void showSong(ActionEvent event) {

    }
}
