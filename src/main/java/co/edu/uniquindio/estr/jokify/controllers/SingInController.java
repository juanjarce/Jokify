package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.AttributesException;
import co.edu.uniquindio.estr.jokify.exceptions.UserException;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.serialization.threads.SaveBinaryResource;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SingInController {

    @FXML
    private TextField textFieldUser;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldEmail;

    //Auxiliary variables for the class to use.
    private Stage stage;
    private LoginController loginController;
    private final Store store = Store.getInstance();

    /**
     * Shows the current stage.
     */
    public void show() {
        stage.show();
    }

    /**
     * Initializes the stage and the Login Controller.
     * @param stage stage to be shown.
     * @param loginController controller that is ligated to the stage.
     */
    public void init(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
    }

    /**
     * Get the information in the text field of a user.
     */
    @FXML
    void createAcount() {
        try {
            String username = textFieldUser.getText();
            String password = textFieldPassword.getText();
            String email = textFieldEmail.getText();
            store.createUser(username, password, email);
            cleanFields();
            showMessage("Registrarse.", "Tú registro ha sido realizado.", Alert.AlertType.INFORMATION);

            // Save the Store content after registering a new user.
            SaveBinaryResource t1 = new SaveBinaryResource();
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (UserException | AttributesException e) {
            showMessage("Registro:\n", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    /**
     * Returns to the login interface.
     */
    @FXML
    void login() {
        loginController.show();
        this.stage.close();
    }

    /**
     * Cleans the input text fields.
     */
    private void cleanFields() {
        textFieldUser.setText("");
        textFieldPassword.setText("");
        textFieldEmail.setText("");
    }

    /**
     * Shows a message to the user.
     * @param header header of the message.
     * @param content content of the message.
     * @param alertType type of the message.
     */
    private void showMessage(String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Jokify");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
