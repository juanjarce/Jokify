package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.SearchException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.animation.FadeTransition;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ShowSearchController implements Initializable {

    @FXML
    private TextField txtSearch;

    @FXML
    private StackPane stackRS1;

    @FXML
    private ImageView imageRS1;

    @FXML
    private Label lblRS1;

    @FXML
    private StackPane stackRS2;

    @FXML
    private ImageView imageRS2;

    @FXML
    private Label lblRS2;

    @FXML
    private StackPane stackRS3;

    @FXML
    private ImageView imageRS3;

    @FXML
    private Label lblRS3;

    @FXML
    private StackPane stackRS4;

    @FXML
    private ImageView imageRS4;

    @FXML
    private Label lblRS4;

    @FXML
    private StackPane stackRS5;

    @FXML
    private ImageView imageRS5;

    @FXML
    private Label lblRS5;

    @FXML
    private TableView<Artist> tableViewArtist;

    @FXML
    private TableColumn<Artist, String> columnNameArtist;

    @FXML
    private TableColumn<Artist, String> columnNationalityArtist;

    // Auxiliary variables for the class to use.
    private MenuController menuController;
    private Artist selectedArtist;
    private List<Song> songs;
    private final ObservableList<Artist> artistObservableList = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();

    /**
     * Get some content of the menuController and init content.
     */
    public void init(MenuController menuController) {
        this.menuController = menuController;
    }

    /**
     * Initialize content for the search interface.
     * @param url obligatory parameter.
     * @param resourceBundle obligatory parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Data in the tableView.
        this.columnNameArtist.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getName()));
        this.columnNationalityArtist.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNationality()));
        // Selection of artist on the table.
        tableViewArtist.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedArtist = newSelection;
                selectedArtist = tableViewArtist.getSelectionModel().getSelectedItem();
            }
        });
    }

    /**
     * Search the content that's in the search text field.
     */
    @FXML
    void search() {
        try {
            String search = txtSearch.getText();
            // Creates all the content that can be showed.
            songs = store.getSongsSearch(search);
            showSongs(songs);
            List<Artist> artists = store.getArtistsSearch(songs);
            showAtists(artists);
        } catch (SearchException e) {
            showMessage("Busqueda:\n", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    /**
     * Shows the artists related to the search.
     * @param artists list of artists.
     */
    private void showAtists(List<Artist> artists) {
        tableViewArtist.getItems().clear();
        tableViewArtist.setItems(getArtistObservable(artists));
    }

    /**
     * ObservableList of the table of artists.
     * @param artists list of artists.
     * @return An observable list of the artists.
     */
    private ObservableList<Artist> getArtistObservable(List<Artist> artists) {
        artistObservableList.clear();
        artistObservableList.addAll(artists);
        return artistObservableList;
    }

    /**
     * Shows the firs 5 songs in the list
     * @param songs list of songs to show.
     */
    private void showSongs(List<Song> songs) {
        if (!songs.isEmpty()) {
            for (int i = 0; i < songs.size(); i++) {
                if (i == 0 && songs.get(i) != null) {
                    lblRS1.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS1.setImage(aux);
                    imageRS1.setFitWidth(200);
                    imageRS1.setFitHeight(150);
                    imageRS1.setTranslateY(10);
                    animateFadeIn(imageRS1);
                    Song currentSong = songs.get(i);
                    // Add functionality to the stack.
                    stackRS1.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 1 && songs.get(i) != null) {
                    lblRS2.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS2.setImage(aux);
                    imageRS2.setFitWidth(200);
                    imageRS2.setFitHeight(150);
                    imageRS2.setTranslateY(10);
                    animateFadeIn(imageRS2);
                    Song currentSong = songs.get(i);
                    // Add functionality to the stack.
                    stackRS2.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 2 && songs.get(i) != null) {
                    lblRS3.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS3.setImage(aux);
                    imageRS3.setFitWidth(200);
                    imageRS3.setFitHeight(150);
                    imageRS3.setTranslateY(10);
                    animateFadeIn(imageRS3);
                    Song currentSong = songs.get(i);
                    // Add functionality to the stack.
                    stackRS3.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 3 && songs.get(i) != null) {
                    lblRS4.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS4.setImage(aux);
                    imageRS4.setFitWidth(200);
                    imageRS4.setFitHeight(150);
                    imageRS4.setTranslateY(10);
                    animateFadeIn(imageRS4);
                    Song currentSong = songs.get(i);
                    // Add functionality to the stack.
                    stackRS4.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                } else if (i == 4 && songs.get(i) != null) {
                    lblRS5.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageRS5.setImage(aux);
                    imageRS5.setFitWidth(200);
                    imageRS5.setFitHeight(150);
                    imageRS5.setTranslateY(10);
                    animateFadeIn(imageRS5);
                    Song currentSong = songs.get(i);
                    // Add functionality to the stack.
                    stackRS5.setOnMouseClicked(mouseEvent -> menuController.setCurrentSong(currentSong));
                }
            }
        }
    }

    /**
     * Applies a fade in animation to the specified node
     * @param node specified node.
     */
    private void animateFadeIn(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    /**
     * Shows the information of an artist.
     * @throws IOException if the artist is not selected.
     */
    @FXML
    void showAtist() throws IOException {
        if (selectedArtist != null) {
            menuController.showArtist(selectedArtist);
        } else {
            showMessage("Artistas.", "Por favor selecciona un artista de la tabla.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Shows a link of all the songs that are related to the search.
     */
    @FXML
    void showMoreSongs() throws IOException {
        menuController.showSongs((ArrayList<Song>) songs);
    }

    /**
     * show a message for the user.
     * @param header    header of the message.
     * @param content   content of the message.
     * @param alertType type of the message.
     */
    private void showMessage(String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Jokify");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
