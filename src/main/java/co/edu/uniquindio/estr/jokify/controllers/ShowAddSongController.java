package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.SongsException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.enums.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowAddSongController implements Initializable {

    //Elements fot the manage of artist
    private ObservableList<Song> listSongsData = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();
    Song selectedSong;
    String coverImageDirection;

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
    private TextField txtYearSong;

    @FXML
    private TextField txtDurationSong;

    @FXML
    private TextField txtURLSong;

    @FXML
    private ComboBox<String> comboBoxGenreSong;

    @FXML
    private ComboBox<String> comboArtistNameSong;

    @FXML
    private Button btnCoverSong;

    @FXML
    private ImageView coverImageView;

    @FXML
    void addSong(ActionEvent event) {
        if(this.coverImageDirection != null){
            String name = txtNameSong.getText();
            String album = txtAlbumSong.getText();
            String cover = this.coverImageDirection;
            int year = Integer.parseInt(txtYearSong.getText());
            int duration = Integer.parseInt(txtDurationSong.getText());
            String youtubeURL = txtURLSong.getText();
            String genre = comboBoxGenreSong.getValue();
            String artistName = comboArtistNameSong.getSelectionModel().getSelectedItem();

            try {
                Song newSong = new Song(name, album, cover, year, duration, youtubeURL, Genre.valueOf(genre), artistName);
                store.createSong(newSong);
                mostrarMensaje("PROCESO EXITOSO", "Canción agregada", "La canción fue creada exitosamente", Alert.AlertType.INFORMATION);

                // Set the actual artist list
                this.listSongsData.setAll(store.getSongList().toObservableList());
                this.tableViewSong.setItems(this.listSongsData);

                //Clear the fields
                clearSongFields();
            } catch (Exception e) {
                mostrarMensaje("ERROR", "Error eliminando la canción", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
        else{
            mostrarMensaje("ERROR", "Error creando la canción", "Se debe seleccionar una imagen de carátula", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void deleteSong(ActionEvent event) {
        if (selectedSong != null) {
            try {
                store.deleteSong(selectedSong.getCode());
                mostrarMensaje("PROCESO EXITOSO", "Canción eliminada", "La canción fue eliminada exitosamente", Alert.AlertType.INFORMATION);

                // Set the actual artist list
                this.listSongsData.setAll(store.getSongList().toObservableList());
                this.tableViewSong.setItems(this.listSongsData);

                //Clear the fields
                clearSongFields();

                //Deselect the artist
                this.selectedSong = null;
            } catch (SongsException e) {
                mostrarMensaje("ERROR", "Error eliminando la canción", e.getMessage(), Alert.AlertType.WARNING);

            }
        }
    }

    @FXML
    void getCoverEvent(ActionEvent event) {
        //Se crea el dialogo para seleccionar los archivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo PNG", "*.png"),
                new FileChooser.ExtensionFilter("Archivo JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("Archivo BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("Archivo GIF", "*.gif"));

        //Se abre el dialogo para seleccionar los archivos
        File file = fileChooser.showOpenDialog(null);

        //Se verifica que el archivo seleccionado sea una imagen
        if (file.isFile() && (file.getName().contains(".jpg") || file.getName().contains(".png") ||
                file.getName().contains(".bmp") || file.getName().contains(".gif"))) {

            try {
                String imageURL = file.toURI().toURL().toString();

                //Se agrega la imagen a las imagenes del vehiculo
                this.coverImageDirection = imageURL;
                mostrarMensaje("Mensaje Informativo", "Imagen de Carátula", "Imagen seleccionada exitosamente", Alert.AlertType.INFORMATION);

                //Se carga la vista previa de la imagen
                Image imgLoad = new Image(imageURL);
                coverImageView.setImage(imgLoad);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            mostrarMensaje("Mensaje Informativo", "Imagen de Carátula", "El formato del archivo no es una imagen", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void newSong(ActionEvent event) {
        clearSongFields();
    }

    @FXML
    void selectSongEvent(MouseEvent event) {
        this.selectedSong = this.tableViewSong.getSelectionModel().getSelectedItem();

        if(this.selectedSong!=null) {
            comboArtistNameSong.getSelectionModel().select(this.selectedSong.getArtistName());
            txtNameSong.setText(this.selectedSong.getName());
            this.coverImageDirection = this.selectedSong.getCover();
            //Show cover image -------------------------
            Image imgLoad = new Image(this.coverImageDirection);
            coverImageView.setImage(imgLoad);
            //------------------------------------------
            txtDurationSong.setText(String.valueOf(this.selectedSong.getDurationOnSeconds()));
            txtAlbumSong.setText(this.selectedSong.getAlbum());
            txtYearSong.setText(String.valueOf(this.selectedSong.getYear()));
            txtURLSong.setText(this.selectedSong.getYoutubeURL());
            comboBoxGenreSong.getSelectionModel().select(this.selectedSong.getGenre().toString());
        }
    }

    @FXML
    void updateSong(ActionEvent event) {
        if (this.selectedSong != null) {
            String name = txtNameSong.getText();
            String album = txtAlbumSong.getText();
            String cover = this.coverImageDirection;
            int year = Integer.parseInt(txtYearSong.getText());
            int duration = Integer.parseInt(txtDurationSong.getText());
            String youtubeURL = txtURLSong.getText();
            String genre = comboBoxGenreSong.getValue();
            String artistName = comboArtistNameSong.getSelectionModel().getSelectedItem();

            try {
                store.updateSong(selectedSong.getCode(), name, album, cover, year, duration, youtubeURL, Genre.valueOf(genre), artistName);
                mostrarMensaje("PROCESO EXITOSO", "Canción actualizada", "La canción fue actualizada exitosamente", Alert.AlertType.INFORMATION);

                // Set the actual artist list
                this.listSongsData.setAll(store.getSongList().toObservableList());
                this.tableViewSong.setItems(this.listSongsData);
                // Refresh table por vosializationg changes
                this.tableViewSong.refresh();

                //Deselect artist
                this.selectedSong = null;
            } catch (SongsException e) {
                mostrarMensaje("ERROR", "Error actualizando la canción", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    // Method to clear input fields
    private void clearSongFields() {
        txtNameSong.clear();
        txtAlbumSong.clear();
        this.coverImageDirection = null;
        coverImageView.setImage(null);
        txtYearSong.clear();
        txtDurationSong.clear();
        txtURLSong.clear();
        comboArtistNameSong.getSelectionModel().clearSelection();
        comboBoxGenreSong.getSelectionModel().clearSelection();
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

        //initilizate the combo for Genre on Songs
        ObservableList<String> genres = FXCollections.observableArrayList("ROCK", "POP", "PUNK", "REGGEATON", "ELECTRONIC", "TECHNO");
        comboBoxGenreSong.setItems(genres);

        //initilizate the combo for Genre on Songs
        ObservableList<String> artistNames = store.getAllArtistNames();
        comboArtistNameSong.setItems(artistNames);
        //----------------------------------------------------------------------------------------------------------------
    }
}


