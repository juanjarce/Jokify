package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * MenuAdminController class that shows after an admin logs in.
 * MenuAdmin interface controller.
 * @version 1.0
 */

public class MenuAdminController {

    //FXML injected component.
    @FXML
    private BorderPane contentPane;

    //Auxiliary attributes of the class.
    private Stage stage;
    private LoginController loginController;
    static { Store.getInstance(); }

    /**
     * Shows the current stage, the menuAdmin interface in this case.
     */
    public void show() {
        stage.show();
    }

    /**
     * Init the stage and the loginController
     * @param stage the stage to initialize.
     * @param loginController the loginController to initialize.
     */
    public void init(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
    }

    /**
     * Close the session of the administrator to then display the login interface.
     */
    @FXML
    void closeSesion() {
        loginController.show();
        this.stage.close();
    }

    /**
     * Shows the dynamic content for the addArtist functionality
     */
    @FXML
    void showAddArtist() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowAddArtist.fxml"));
        Parent newContent = loader.load();

        // Few animations for the content to be displayed.
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Set the controller in the contentPane.
        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content to the adding songs feature.
     */
    @FXML
    void showAddSong() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowAddSong.fxml"));
        Parent newContent = loader.load();

        // Few animations for the content to be displayed.
        animationDisplay(newContent);

        // Set the controller in the content pane.
        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content to the download information feature.
     */
    @FXML
    void showDownloadInfo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowDownloadInfo.fxml"));
        Parent newContent = loader.load();

        // Few animations for the content to be displayed.
        animationDisplay(newContent);

        // Set the controller in the content pane.
        contentPane.setCenter(newContent);
    }

    /**
     * Shows the dynamic content for the statistics functionality.
     */
    @FXML
    void showStatistics() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ShowStatistics.fxml"));
        Parent newContent = loader.load();

        // Few animations for the content to be displayed.
        animationDisplay(newContent);

        // Set the cotnroller in the content pane.
        contentPane.setCenter(newContent);
    }

    /**
     * Displays a little animation when changing the content of the interface.
     * @param newContent the new content to be displayed.
     */
    private void animationDisplay(Parent newContent) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newContent);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
