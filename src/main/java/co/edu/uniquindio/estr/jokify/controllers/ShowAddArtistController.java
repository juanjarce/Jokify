package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.ArtistsException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAddArtistController implements Initializable {

    //Elements fot the manage of artist
    private ObservableList<Artist> listArtistData = FXCollections.observableArrayList();
    private final Store store = Store.getInstance();
    Artist selectedArtist;

    @FXML
    private TextField txtNameArtist;

    @FXML
    private CheckBox checkBoxIsGroup;

    @FXML
    private TextField txtNationalityArtist;

    @FXML
    private Button btnAddArtist;

    @FXML
    private Button btnNewArtist;

    @FXML
    private TableView<Artist> tableViewArtist;

    @FXML
    private TableColumn<Artist, String> columnCodeArtist;

    @FXML
    private TableColumn<Artist, String> columnNameArtist;

    @FXML
    private TableColumn<Artist, String> columnNationalityArtist;

    @FXML
    private Button btnDeleteArtist;

    @FXML
    private Button btnUpdateArtist;

    //METHODS for Artists managment ------------------------------------------------------------------------------------
    // Method to update table view with current artist list

    // Method to clear input fields
    private void clearArtistFields() {
        txtNameArtist.clear();
        txtNationalityArtist.clear();
        checkBoxIsGroup.setSelected(false);
    }

    /**
     * Adds an artist
     * @param event
     */
    @FXML
    void addArtist(ActionEvent event) {
        String name = txtNameArtist.getText();
        String nationality = txtNationalityArtist.getText();
        boolean isGroup = checkBoxIsGroup.isSelected();
        try {
            Artist newArtist = new Artist(name, nationality, isGroup);
            store.createArtist(newArtist);
            mostrarMensaje("PROCESO EXITOSO", "Artista agregado", "El artista fue creado exitosamente", Alert.AlertType.INFORMATION);

            // Set the actual artist list
            this.listArtistData.setAll(store.getArtistList().toObservableList());
            this.tableViewArtist.setItems(this.listArtistData);

            //Clear the fields
            clearArtistFields();
        } catch (ArtistsException e) {
            mostrarMensaje("ERROR", "Error agregando al artista", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    /**
     * Deletes the artist that is selected
     * @param event
     */
    @FXML
    void deleteArtist(ActionEvent event) {
        if (selectedArtist != null) {
            try {
                store.deleteArtist(selectedArtist.getCode());
                mostrarMensaje("PROCESO EXITOSO", "Artista Eliminado", "El artista fue eliminado exitosamente", Alert.AlertType.INFORMATION);

                // Set the actual artist list
                this.listArtistData.setAll(store.getArtistList().toObservableList());
                this.tableViewArtist.setItems(this.listArtistData);

                //Clear the fields
                clearArtistFields();

                //Deselect the artist
                this.selectedArtist = null;
            } catch (ArtistsException e) {
                mostrarMensaje("ERROR", "Error eliminando al artista", e.getMessage(), Alert.AlertType.WARNING);

            }
        }
    }

    /**
     * Clear the fields of the artist
     * @param event
     */
    @FXML
    void newArtist(ActionEvent event) {
        clearArtistFields();
    }

    /**
     * Uptade the info of an artist
     * @param event
     */
    @FXML
    void updateArtist(ActionEvent event) {
        if (this.selectedArtist != null) {
            String name = txtNameArtist.getText();
            String nationality = txtNationalityArtist.getText();
            boolean isGroup = checkBoxIsGroup.isSelected();
            try {
                Artist updatedArtist = new Artist(selectedArtist.getCode(), name, nationality, isGroup);
                store.updateArtist(updatedArtist);
                mostrarMensaje("PROCESO EXITOSO", "Artista Actualizado", "El artista fue actualizado exitosamente", Alert.AlertType.INFORMATION);

                // Set the actual artist list
                this.listArtistData.setAll(store.getArtistList().toObservableList());
                this.tableViewArtist.setItems(this.listArtistData);

                //Deselect artist
                this.selectedArtist = null;
            } catch (ArtistsException e) {
                mostrarMensaje("ERROR", "Error actualizando al artista", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void selectArtistEvent(MouseEvent event) {
        this.selectedArtist = this.tableViewArtist.getSelectionModel().getSelectedItem();

        if(this.selectedArtist!=null) {
            txtNameArtist.setText(this.selectedArtist.getName());
            txtNationalityArtist.setText(this.selectedArtist.getNationality());
            checkBoxIsGroup.setSelected(this.selectedArtist.isPartOfBand());
        }
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
    }

}

