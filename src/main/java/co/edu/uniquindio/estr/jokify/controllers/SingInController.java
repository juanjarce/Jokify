package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class SingInController {

    @FXML
    private TextField textFieldUser;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button btnCreateAcount;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private Hyperlink hpLogin;

    //Aux variables
    private Stage stage;
    private LoginController loginController;
    private final Store store = Store.getInstance();

    /**
     * Sets the current stage
     * @param primaryStage
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Shows the current stage
     */
    public void show() {
        stage.show();
    }

    /**
     * Init the stage and loginCOntroller
     * @param stage
     * @param loginController
     */
    public void init(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
    }

    @FXML
    void createAcount(ActionEvent event) {

    }

    /**
     * Returns to the login interface
     * @param event
     */
    @FXML
    void login(ActionEvent event) {
        loginController.show();
        this.stage.close();
    }

}
