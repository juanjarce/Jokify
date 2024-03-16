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

public class MenuController implements Initializable {

    @FXML
    private Button btnJokify;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnLibrary;

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
     * Intizize conten for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void showJokify(ActionEvent event) {

    }

    @FXML
    void showLibrary(ActionEvent event) {

    }

    @FXML
    void showSearch(ActionEvent event) {

    }

}
