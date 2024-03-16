package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.exceptions.UserException;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField textFieldUser;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hpCreateAcount;

    //Aux variables
    private Stage stage;
    private User currentUser;
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
     * Initialize some content for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String user = textFieldUser.getText();
        String password = textFieldPassword.getText();

    }

    /**
     * Goes to the createAcount interfaces
     * @param event
     * @throws IOException
     */
    @FXML
    void createAcount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SingInView.fxml"));
        Parent root = loader.load();
        SingInController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    /**
     * If the info of the user is correct, goes to the menu interface
     * If the user is the Admin, shows the adminMenu
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void login(ActionEvent event)  {
        try {
            currentUser = store.loginUser(textFieldUser.getText(), textFieldPassword.getText());
            showMessage("Jokify", "Login", "Inicio de sesión realizado con éxito", Alert.AlertType.INFORMATION);
            if (currentUser.getUsername().equals("admin")) {
                showAdminMenu();
            } else {
                showMenu();
            }
        } catch (UserException e) {
            showMessage("Jokify", "Login", e.getMessage(), Alert.AlertType.WARNING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Shows the menu Interface for an user
     * @throws IOException
     */
    private void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MenuView.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    /**
     * Shows the menu Interface for the admin
     * @throws IOException
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

