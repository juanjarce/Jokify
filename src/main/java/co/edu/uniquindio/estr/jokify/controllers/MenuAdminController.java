package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController implements Initializable {

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
     * Initialize some content for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Close the sesion of the admin
     * @param event
     */
    @FXML
    void closeSesion(ActionEvent event) {

    }

    /**
     * Shows the dynamic content for the addArtist functionality
     * @param event
     */
    @FXML
    void showAddArtist(ActionEvent event) {

    }

    /**
     * Shows the dynamic content for the addSong functionality
     * @param event
     */
    @FXML
    void showAddSong(ActionEvent event) {

    }

    /**
     * Shows the dynamic content for the downloadInfo functionality
     * @param event
     */
    @FXML
    void showDownloadInfo(ActionEvent event) {

    }

}
