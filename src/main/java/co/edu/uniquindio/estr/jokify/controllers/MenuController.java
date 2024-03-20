package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    private Button btnCloseSesion;

    @FXML
    private BorderPane contentPane;

    //Aux variables
    private Stage stage;
    private LoginController loginController;
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
     * Init the stage and the loginController
     * @param stage
     * @param loginController
     * @param currrentUser
     */
    public void init(Stage stage, LoginController loginController, User currrentUser) {
        this.stage = stage;
        this.loginController = loginController;
        this.currentUser = currrentUser;
    }

    /**
     * Intizize conten for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Close the sesion of the current user
     * @param event
     */
    @FXML
    void closeSesion(ActionEvent event) {
        loginController.show();
        this.stage.close();
    }

    /**
     * Shows the dynamic content for the Jokify app
     * @param event
     */
    @FXML
    void showJokify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowJokify.fxml"));
        Parent newContent = loader.load();

        //Get the controller of the FXML file
        ShowJokifyController controller = loader.getController();
        //Move the user to the controller
        controller.init(currentUser);
        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the library of the user
     * @param event
     */
    @FXML
    void showLibrary(ActionEvent event) throws IOException {
        Node newContent = FXMLLoader.load(getClass().getResource("/views/ShowLibrary.fxml"));

        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the search property of the app
     * @param event
     */
    @FXML
    void showSearch(ActionEvent event) throws IOException {
        Node newContent = FXMLLoader.load(getClass().getResource("/views/ShowSearch.fxml"));

        //Apply animations for the content
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.setCenter(newContent);
    }

}
