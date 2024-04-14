package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.ArtistsException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAddArtistController implements Initializable {

    //Attributes used to manage an artist.
    private final ObservableList<Artist> listArtistData = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();
    Artist selectedArtist;

    @FXML
    private TextField txtNameArtist;

    @FXML
    private CheckBox checkBoxIsGroup;

    @FXML
    private TextField txtNationalityArtist;

    @FXML
    private TableView<Artist> tableViewArtist;

    @FXML
    private TableColumn<Artist, String> columnCodeArtist;

    @FXML
    private TableColumn<Artist, String> columnNameArtist;

    @FXML
    private TableColumn<Artist, String> columnNationalityArtist;

    // -------------------------------------Methods used to manage artists.---------------------------------------------

    /**
     * Clear the input fields of the artist.
     */
    private void clearArtistFields() {
        txtNameArtist.clear();
        txtNationalityArtist.clear();
        checkBoxIsGroup.setSelected(false);
    }

    /**
     * Adds an artist to the store.
     */
    @FXML
    void addArtist() {
        // Get the data from the fields.
        String name = txtNameArtist.getText();
        String nationality = txtNationalityArtist.getText();
        boolean isGroup = checkBoxIsGroup.isSelected();

        if(validateData(name, nationality)){
            try {
                Artist newArtist = new Artist(name, nationality, isGroup);
                store.createArtist(newArtist);
                showMessage("Éxito", "Artista agregado.", "El artista fue creado exitosamente.", Alert.AlertType.INFORMATION);

                // Saves the store content after adding an artist using a thread.
                SaveBinaryResource t1 = new SaveBinaryResource();
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Set the actual artist list.
                this.listArtistData.setAll(store.getArtistList().toObservableList());
                this.tableViewArtist.setItems(this.listArtistData);

                //Clear the input fields.
                clearArtistFields();
            } catch (ArtistsException e) {
                showMessage("Error", "Error agregando al artista.\n", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    /**
     * Deletes the artist that is selected in the table.
     */
    @FXML
    void deleteArtist() {
        if (selectedArtist != null) {
            try {
                store.deleteArtist(selectedArtist.getCode());
                showMessage("Éxito", "Artista Eliminado.", "El artista fue eliminado exitosamente.", Alert.AlertType.INFORMATION);

                // Saves the store content after deleting an artist using a thread.
                SaveBinaryResource t1 = new SaveBinaryResource();
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Set the actual artist list.
                this.listArtistData.setAll(store.getArtistList().toObservableList());
                this.tableViewArtist.setItems(this.listArtistData);

                //Clears the input fields.
                clearArtistFields();

                //Deselect the artist after deleting it.
                this.selectedArtist = null;
            } catch (ArtistsException e) {
                showMessage("Error", "Error eliminando al artista.\n", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    /**
     * Clear the input fields of the artist.
     */
    @FXML
    void newArtist() {
        clearArtistFields();
    }

    /**
     * Update the information of an artist.
     */
    @FXML
    void updateArtist() {
        // Checking if there is an artist selected in the interface, if so, the information is extracted and updated.
        if (this.selectedArtist != null) {
            String name = txtNameArtist.getText();
            String nationality = txtNationalityArtist.getText();
            boolean isGroup = checkBoxIsGroup.isSelected();

            if(validateData(name, nationality)){
                try {
                    Artist updatedArtist = new Artist(selectedArtist.getCode(), name, nationality, isGroup);
                    store.updateArtist(updatedArtist);
                    showMessage("Éxito", "Artista Actualizado.", "El artista fue actualizado exitosamente.", Alert.AlertType.INFORMATION);

                    // Save the store content after updating an artist using a thread.
                    SaveBinaryResource t1 = new SaveBinaryResource();
                    t1.start();
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // Set the actual artist list.
                    this.listArtistData.setAll(store.getArtistList().toObservableList());
                    this.tableViewArtist.setItems(this.listArtistData);
                    // Refresh the table with the updated data.
                    this.tableViewArtist.refresh();
                    //Deselects the artist after updating it.
                    this.selectedArtist = null;
                } catch (ArtistsException e) {
                    showMessage("Error", "Error actualizando al artista.\n", e.getMessage(), Alert.AlertType.WARNING);
                }
            }
        }
    }

    /**
     * Method to show a message.
     * @param titulo Title of the message.
     * @param header Header of the message.
     * @param contenido Content of the message.
     * @param alertType Type of the message.
     */
    public void showMessage(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    /**
     * Method to select an artist from the table shown on the interface.
     */
    @FXML
    void selectArtistEvent() {
        this.selectedArtist = this.tableViewArtist.getSelectionModel().getSelectedItem();

        if(this.selectedArtist!=null) {
            txtNameArtist.setText(this.selectedArtist.getName());
            txtNationalityArtist.setText(this.selectedArtist.getNationality());
            checkBoxIsGroup.setSelected(this.selectedArtist.isPartOfBand());
        }
    }

    /**
     * Method to validate the input data of the fields.
     * @param name Name of the artist
     * @param nationality Nationality of the artist.
     */
    private boolean validateData(String name, String nationality) {
        StringBuilder mensaje = new StringBuilder();

        if (name == null || name.isEmpty())
            mensaje.append("El nombre del artista es inválido.\n");

        if (nationality == null || nationality.isEmpty())
            mensaje.append("La nacionalidad del artista es inválida.\n");

        if (mensaje.isEmpty()) {
            return true;
        } else {
            showMessage("Información de Artista", "Datos inválidos", mensaje.toString(), Alert.AlertType.WARNING);
            return false;
        }
    }

    /**
     * Initializes the interface with the data stored in the Store class.
     * @param url required by the method but not used.
     * @param resourceBundle required by the method but not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Defining columns
        this.columnCodeArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("code"));
        this.columnNameArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
        this.columnNationalityArtist.setCellValueFactory(new PropertyValueFactory<Artist, String>("Nationality"));

        // Populate table with artist data
        tableViewArtist.setItems(store.getArtistList().toObservableList());
    }
}

