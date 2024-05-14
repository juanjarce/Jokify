package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import co.edu.uniquindio.estr.jokify.utils.FileLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShowDownloadInfoController implements Initializable {

    //Elements fot the manage of artist & songs
    private final ObservableList<Artist> listArtistData = FXCollections.observableArrayList();
    private final ObservableList<Song> listSongsData = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();

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
    private TableView<Artist> tableViewArtist;

    @FXML
    private TableColumn<Artist, String> columnCodeArtist;

    @FXML
    private TableColumn<Artist, String> columnNameArtist;

    @FXML
    private TableColumn<Artist, String> columnNationalityArtist;

    /**
     * Method to choose a file from the file system so that the administrator can load the artists and songs.
     * @return The path of the file selected by the administrator.
     */
    public String chooseFile(){
        // Create a FileChooser object.
        FileChooser fileChooser = new FileChooser();

        // Set the dialog title.
        fileChooser.setTitle("Select a text file with the artists and songs information.");

        // Set the extension filter to show only text files.
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Get the primary stage.
        Stage stage = new Stage();

        // Show the file selection dialog and get the selected file.
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected.
        if (selectedFile != null) {
            // Return the absolute path of the selected file.
            return selectedFile.getAbsolutePath();
        } else {
            // If no file was selected, return an empty string.
            return "";
        }
    }

    /**
     * Load the artists and songs from a file.
     */
    @FXML
    void loadFromFile() {
        String path = chooseFile();
        if(!Objects.equals(path, "")){
            FileLoader.loadArtistsAndSongs(store, path);

            // Save the Store content
            SaveBinaryResource t1 = new SaveBinaryResource();
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //Show the loaded data on the table.
            listArtistData.setAll(store.getArtistList().toObservableList());
            listSongsData.setAll(store.getSongList().toObservableList());
            tableViewArtist.setItems(listArtistData);
            tableViewSong.setItems(listSongsData);

        }else{
            showMessage("Error", "Error de ruta archivo.", "No se ha seleccionado el archivo.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Method to show a message on the screen.
     * @param title Title of the message.
     * @param header Header of the message.
     * @param content Content of the message.
     * @param alertType Type of the message.
     */
    public void showMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Initializes information to the interface.
     * @param url obligatory parameter, not really used.
     * @param resourceBundle obligatory parameter, not really used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // -------------------------Initialize Artist Information------------------------------------------------------
        //Defining columns.
        this.columnCodeArtist.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.columnNameArtist.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnNationalityArtist.setCellValueFactory(new PropertyValueFactory<>("Nationality"));

        // Populate table with artist data.
        tableViewArtist.setItems(store.getArtistList().toObservableList());
        //--------------------------Initialize Songs of the Artist-----------------------------------------------------
        //Defining columns
        this.columnCodeSong.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnArtistSong.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<>("album"));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<>("durationOnSeconds"));

        // Populate table with artist data
        tableViewSong.setItems(store.getSongList().toObservableList());
    }
}

