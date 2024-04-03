package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import co.edu.uniquindio.estr.jokify.utils.FileLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private ObservableList<Artist> listArtistData = FXCollections.observableArrayList();
    private ObservableList<Song> listSongsData = FXCollections.observableArrayList();
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

    @FXML
    private Button btnLoadFromFile;

    public String chooseFile(){
        // Create a FileChooser object
        FileChooser fileChooser = new FileChooser();

        // Set the dialog title
        fileChooser.setTitle("Select Text File");

        // Set the extension filter to show only text files
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Get the primary stage
        Stage stage = new Stage();

        // Show the file selection dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected
        if (selectedFile != null) {
            // Return the absolute path of the selected file
            return selectedFile.getAbsolutePath();
        } else {
            // If no file was selected, return an empty string
            return "";
        }
    }

    @FXML
    void loadFromFile(ActionEvent event) {
        String path = chooseFile();
        if(!Objects.equals(path, "")){
            FileLoader.loadArtistsAndSongs(store, path);

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

            //Show on tables
            listArtistData.setAll(store.getArtistList().toObservableList());
            listSongsData.setAll(store.getSongList().toObservableList());

            tableViewArtist.setItems(listArtistData);
            tableViewSong.setItems(listSongsData);

        }else{
            mostrarMensaje("ERROR", "Error de ruta archivo", "No se ha seleccionado correctamente el archivo", Alert.AlertType.WARNING);
        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize Artists ---------------------------------------------------------------------------------------------
        //Defining columns
        this.columnCodeArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("code"));
        this.columnNameArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
        this.columnNationalityArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("Nationality"));

        // Populate table with artist data
        tableViewArtist.setItems(store.getArtistList().toObservableList());
        //----------------------------------------------------------------------------------------------------------------
        //Initialize Songs ---------------------------------------------------------------------------------------------
        //Defining columns
        this.columnCodeSong.setCellValueFactory(new PropertyValueFactory<Song, String>("code"));
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
        this.columnArtistSong.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<Song, Integer>("durationOnSeconds"));

        // Populate table with artist data
        tableViewSong.setItems(store.getSongList().toObservableList());
        //----------------------------------------------------------------------------------------------------------------
    }
}

