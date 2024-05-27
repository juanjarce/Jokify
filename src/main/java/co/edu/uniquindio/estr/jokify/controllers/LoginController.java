package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.UserException;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LoginController class.
 * Login interface controller.
 * Initializable class is implemented.
 * @version 1.0
 */

public class LoginController implements Initializable {

    //FXML injected components
    @FXML
    private TextField textFieldUser;

    @FXML
    private TextField textFieldPassword;


    //Auxiliary attributes of the class
    private Stage stage;
    private final Store store = Store.getInstance();

    /**
     * Should initialize content on the controller class if necessary.
     * @param url parameter that is not used but is necessary for the implementation of the method.
     * @param resourceBundle parameter that is not used but is necessary for the implementation of the method.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Sets the current stage to the controller.
     * @param primaryStage the current stage that is given as a parameter.
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Shows the current stage, the login interface in this case.
     */
    public void show() {
        stage.show();
    }

    /**
     * Directs the interface to the Create Account interface.
     * @throws IOException if the interface is not found or the path is incorrect.
     */
    @FXML
    void createAcount() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SingInView.fxml"));
        Parent root = loader.load();
        SingInController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        // Close the current stage when the new stage is shown.
        this.stage.close();
    }

    /**
     * If the information provided by the user is correct, goes to the Jokify Menu interface.
     * Can also be used by an administrator to go to the Admin Menu interface.
     */
    @FXML
    void login() {
        try {
            User currentUser = store.loginUser(textFieldUser.getText(), textFieldPassword.getText());
            showMessage("Inicio de sesión realizado con éxito.", Alert.AlertType.INFORMATION);
            if (currentUser.getUsername().equals("admin")) {
                showAdminMenu();
            } else {
                showMenu(currentUser);
            }
            cleanFields();
        } catch (UserException e) {
            showMessage(e.getMessage(), Alert.AlertType.WARNING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Shows the Menu Interface for the user that is logged in.
     * @throws IOException if the interface is not found or the path is incorrect.
     */
    private void showMenu(User currentUser) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MenuView.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this, currentUser);
        stage.show();
        this.stage.close();
    }

    /**
     * Shows the menu Interface for the administrator that is logged in.
     * @throws IOException if the interface is not found or the path is incorrect.
     */
    private void showAdminMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MenuAdminView.fxml"));
        Parent root = loader.load();
        MenuAdminController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    /**
     * Cleans the fields of the interface.
     */
    private void cleanFields() {
        textFieldUser.setText("");
        textFieldPassword.setText("");
    }

    /**
     * Shows an information popup for the user related to the login.
     *
     * @param content   content of the popup.
     * @param alertType type of the popup, can be information, warning, error, etc.
     */
    private void showMessage(String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Jokify");
        alert.setHeaderText("Acceso");
        alert.setContentText(content);
        alert.showAndWait();
    }

}

