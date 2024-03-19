package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.ArtistsException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController {

    //Elements for the manage of the dynamic content

    @FXML
    private Button btnShowDownloadInfo;

    @FXML
    private Button btnShowAddSong;

    @FXML
    private Button btnShowAddArtist;

    @FXML
    private Button btnCloseSesion;

    @FXML
    private BorderPane contentPane;

    //Aux variables
    private Stage stage;
    private LoginController loginController;
    private final Store store = Store.getInstance();

    /**
     * Sets the curren stage
     * @param primaryStage
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Show the current stage
     */
    public void show() {
        stage.show();
    }

    /**
     * Init the stage and the loginController
     * @param stage
     * @param loginController
     */
    public void init(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
    }

    /**
     * Close the sesion of the admin
     * @param event
     */
    @FXML
    void closeSesion(ActionEvent event) {
        loginController.show();
        this.stage.close();
    }

    /**
     * Shows the dynamic content for the addArtist functionality
     * @param event
     */
    @FXML
    void showAddArtist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowAddArtist.fxml"));
        Parent newContent = loader.load();

        // Obtener el controlador del FXML cargado
        ShowAddArtistController controller = loader.getController();
        // Aplicar animaciones para el contenido
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Establecer el controlador en el contentPane
        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the addSong functionality
     * @param event
     */
    @FXML
    void showAddSong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowAddSong.fxml"));
        Parent newContent = loader.load();

        // Obtener el controlador del FXML cargado
        ShowAddSongController controller = loader.getController();
        // Aplicar animaciones para el contenido
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Establecer el controlador en el contentPane
        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the downloadInfo functionality
     * @param event
     */
    @FXML
    void showDownloadInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowDownloadInfo.fxml"));
        Parent newContent = loader.load();

        // Obtener el controlador del FXML cargado
        ShowDownloadInfoController controller = loader.getController();
        // Aplicar animaciones para el contenido
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Establecer el controlador en el contentPane
        contentPane.setCenter(newContent);
    }

}
