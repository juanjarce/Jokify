package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.AttributesException;
import co.edu.uniquindio.estr.jokify.exceptions.UserException;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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
     * Init the stage and loginController
     * @param stage
     * @param loginController
     */
    public void init(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
    }

    /**
     * Get the information in the textfields and create an user
     * @param event
     */
    @FXML
    void createAcount(ActionEvent event) {
        try {
            String username = textFieldUser.getText();
            String password = textFieldPassword.getText();
            String email = textFieldEmail.getText();
            store.createUser(username, password, email);
            cleanFields();
            showMessage("Jokify", "Registrarse", "Tú registro ha sido realizado de manera exitosa", Alert.AlertType.INFORMATION);

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
        } catch (UserException | AttributesException e) {
            showMessage("Jokify", "Registrarse", e.getMessage(), Alert.AlertType.WARNING);
        }
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

    /**
     * Cleans the text fields
     */
    private void cleanFields() {
        textFieldUser.setText("");
        textFieldPassword.setText("");
        textFieldEmail.setText("");
    }

    /**
     * show a message for the user
     * @param title
     * @param header
     * @param content
     * @param alertType
     */
    private void showMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
