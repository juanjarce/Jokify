package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.SongsException;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.enums.Genre;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowAddSongController implements Initializable {

    //Attributes used to manage the data of the songs in the table and succesfully add songs to the Store.
    private final ObservableList<Song> listSongsData = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();
    Song selectedSong;
    String coverImageDirection;

    @FXML
    private TextField txtNameSong;

    @FXML
    private TextField txtAlbumSong;

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
    private ImageView coverImageView;

    /**
     * Method to add a new song to the store. Validates the data entered by the administrator and if it is correct, it adds the song to the store.
     */
    @FXML
    void addSong() {
        if(this.coverImageDirection != null){
            String name = txtNameSong.getText();
            String album = txtAlbumSong.getText();
            String cover = this.coverImageDirection;
            String year = txtYearSong.getText();
            String duration = txtDurationSong.getText();
            String youtubeURL = txtURLSong.getText();
            String genre = comboBoxGenreSong.getValue();
            String artistName = comboArtistNameSong.getSelectionModel().getSelectedItem();

            if(validateData(name, album, cover, year, duration, youtubeURL, genre, artistName)){
                try {
                    Song newSong = new Song(name, album, cover, Integer.parseInt(year), Integer.parseInt(duration), 0, youtubeURL, Genre.valueOf(genre), artistName);
                    store.createSong(newSong);
                    showMessage("Éxito", "Canción agregada.", "La canción fue creada exitosamente.", Alert.AlertType.INFORMATION);

                    // Saves the Store content after adding a song.
                    SaveBinaryResource t1 = new SaveBinaryResource();
                    t1.start();
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // Set the actual artist list.
                    this.listSongsData.setAll(store.getSongList().toObservableList());
                    this.tableViewSong.setItems(this.listSongsData);

                    //Clear the input fields.
                    clearSongFields();
                } catch (Exception e) {
                    showMessage("Error", "Error añadiendo la canción.\n", e.getMessage(), Alert.AlertType.WARNING);
                }

            }
        }
        else{
            showMessage("Error", "Error añadiendo la canción.", "Se debe seleccionar una imagen de carátula.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Method to delete a song from the store. Validates that a song is selected and if it is, it deletes it from the store.
     */
    @FXML
    void deleteSong() {
        if (selectedSong != null) {
            try {
                store.deleteSong(selectedSong.getCode());
                showMessage("Éxito", "Canción eliminada.", "La canción fue eliminada.", Alert.AlertType.INFORMATION);

                // Save the Store content after deleting a song.
                SaveBinaryResource t1 = new SaveBinaryResource();
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Set the actual artist list.
                this.listSongsData.setAll(store.getSongList().toObservableList());
                this.tableViewSong.setItems(this.listSongsData);

                //Clear the input fields.
                clearSongFields();

                //Deselect the artist after deleting it.
                this.selectedSong = null;
            } catch (SongsException e) {
                showMessage("Error", "Error eliminando la canción.\n", e.getMessage(), Alert.AlertType.WARNING);

            }
        }
    }

    /**
     * Method to get the cover image of the song. It opens a dialog to select the image file and if it is an image, it sets it as the cover of the song.
     * If the file is not an image, it shows a warning message.
     * This method should throw an Exception, I hope someone does it!
     */
    @FXML
    void getCoverEvent() {
        // The FileChooser class is ued to arrange the type of images Jokify will accept, these are restricted to PNG, JPG, BMP and GIF.
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("Archivo PNG", "*.png"),
                new FileChooser.ExtensionFilter("Archivo BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("Archivo GIF", "*.gif"));

        // A popup is shown to select the image file.
        File file = fileChooser.showOpenDialog(null);

        // If the file is an image, it is set as the cover of the song.
        if (file.isFile() && (file.getName().contains(".jpg") || file.getName().contains(".png") ||
                file.getName().contains(".bmp") || file.getName().contains(".gif"))) {

            try {
                String imageURL = file.toURI().toURL().toString();

                // The image direction is saved in the attribute coverImageDirection.
                this.coverImageDirection = imageURL;
                showMessage("Mensaje Informativo", "Imagen de Carátula", "Imagen seleccionada exitosamente.", Alert.AlertType.INFORMATION);

                // An image is created with the direction of the file, and it is set in the ImageView. This is a preview of the cover.
                Image imgLoad = new Image(imageURL);
                coverImageView.setImage(imgLoad);

            } catch (MalformedURLException e) {
                showMessage("Mensaje Informativo", "Imagen de Carátula", "Error al cargar la imagen.\n"+ e.getMessage(), Alert.AlertType.WARNING);
            }
        } else {
            showMessage("Mensaje Informativo", "Imagen de Carátula", "El formato del archivo no es una imagen aceptada por Jokify.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Method to clear the input fields of the song.
     */
    @FXML
    void newSong() {
        clearSongFields();
    }

    /**
     * Method to select a song from the table. It sets the selected song in the attribute selectedSong and sets the data of the song in the input fields.
     */
    @FXML
    void selectSongEvent() {
        this.selectedSong = this.tableViewSong.getSelectionModel().getSelectedItem();

        if(this.selectedSong!=null) {
            comboArtistNameSong.getSelectionModel().select(this.selectedSong.getArtistName());
            txtNameSong.setText(this.selectedSong.getName());
            this.coverImageDirection = this.selectedSong.getCover();
            Image imgLoad = new Image(this.coverImageDirection);
            coverImageView.setImage(imgLoad);
            txtDurationSong.setText(String.valueOf(this.selectedSong.getDurationOnSeconds()));
            txtAlbumSong.setText(this.selectedSong.getAlbum());
            txtYearSong.setText(String.valueOf(this.selectedSong.getYear()));
            txtURLSong.setText(this.selectedSong.getYoutubeURL());
            comboBoxGenreSong.getSelectionModel().select(this.selectedSong.getGenre().toString());
        }
    }

    /**
     * Method to update the song. Validates the data entered by the administrator and if it is correct, it updates the song in the store.
     */
    @FXML
    void updateSong() {
        if (this.selectedSong != null) {
            String name = txtNameSong.getText();
            String album = txtAlbumSong.getText();
            String cover = this.coverImageDirection;
            String year = txtYearSong.getText();
            String duration = txtDurationSong.getText();
            String youtubeURL = txtURLSong.getText();
            String genre = comboBoxGenreSong.getValue();
            String artistName = comboArtistNameSong.getSelectionModel().getSelectedItem();

            if(validateData(name, album, cover, year, duration, youtubeURL, genre, artistName)){
                try {
                    store.updateSong(selectedSong.getCode(), name, album, cover, Integer.parseInt(year), Integer.parseInt(duration), youtubeURL, Genre.valueOf(genre), artistName);
                    showMessage("Éxito", "Canción actualizada.", "La canción fue actualizada.", Alert.AlertType.INFORMATION);

                    // Saves the Store content after updating a song.
                    SaveBinaryResource t1 = new SaveBinaryResource();
                    t1.start();
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // Set the actual song list.
                    this.listSongsData.setAll(store.getSongList().toObservableList());
                    this.tableViewSong.setItems(this.listSongsData);
                    // Refresh table to see the new changes.
                    this.tableViewSong.refresh();

                    //Deselect artist after updating it.
                    this.selectedSong = null;
                } catch (SongsException e) {
                    showMessage("Error", "Error actualizando la canción.\n", e.getMessage(), Alert.AlertType.WARNING);
                }

            }
        }
    }

    /**
     * Method to clear the input fields of the song.
     */
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

    /**
     * Method to show a message in a dialog.
     * @param title Title of the dialog.
     * @param header Header of the dialog.
     * @param content Content of the dialog.
     * @param alertType Type of the dialog.
     */
    public void showMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Method to validate the data entered by the administrator when adding or updating a song.
     * @param name Name of the song.
     * @param album Album of the song.
     * @param cover Cover of the song.
     * @param year Year of the song.
     * @param duration Duration of the song.
     * @param youtubeURL YouTube URL of the song.
     * @param genre Genre of the song.
     * @param artistName Artist of the song.
     * @return True if the data is correct, false otherwise.
     */
    private boolean validateData(String name, String album, String cover, String year, String duration, String youtubeURL, String genre, String artistName) {
        StringBuilder mensaje = new StringBuilder();

        if (name == null || name.isEmpty())
            mensaje.append("El nombre de la canción es inválido.\n");

        if (album == null || album.isEmpty())
            mensaje.append("El nombre del álbum es inválido.\n");

        if (cover == null || cover.isEmpty())
            mensaje.append("La dirección de la carátula es inválida.\n");

        if (isNumber(year))
            mensaje.append("El año debe ser un número válido.\n");

        if (isNumber(duration))
            mensaje.append("La duración debe ser un número válido.\n");

        if (youtubeURL == null || youtubeURL.isEmpty())
            mensaje.append("La URL de YouTube es inválida.\n");

        if (genre == null)
            mensaje.append("Debe seleccionar un género para la canción.\n");

        if (artistName == null)
            mensaje.append("Debe seleccionar un artista para la canción.\n");

        if (mensaje.isEmpty()) {
            return true;
        } else {
            showMessage("Canción", "Datos inválidos.\n", mensaje.toString(), Alert.AlertType.WARNING);
            return false;
        }
    }


    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Method to initialize the data of the table and the combo boxes.
     * @param url URL of the FXML file.
     * @param resourceBundle ResourceBundle of the FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Defining columns.
        this.columnCodeSong.setCellValueFactory(new PropertyValueFactory<>("code"));
        this.columnNameSong.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnArtistSong.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        this.columnAlbumSong.setCellValueFactory(new PropertyValueFactory<>("album"));
        this.columnYearSong.setCellValueFactory(new PropertyValueFactory<>("year"));
        this.columnDurationSong.setCellValueFactory(new PropertyValueFactory<>("durationOnSeconds"));

        // Populate table with artist data.
        tableViewSong.setItems(store.getSongList().toObservableList());

        //initilizate the combo for Genre on Songs.
        ObservableList<String> genres = FXCollections.observableArrayList("ROCK", "POP", "PUNK", "REGGEATON", "ELECTRONIC", "TECHNO");
        comboBoxGenreSong.setItems(genres);

        //initilizate the combo for Genre on Songs.
        ObservableList<String> artistNames = store.getAllArtistNames();
        comboArtistNameSong.setItems(artistNames);
    }
}


