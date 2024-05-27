package co.edu.uniquindio.estr.jokify.application;

import co.edu.uniquindio.estr.jokify.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**s
 * Main class of the application. In charge of starting the application.
 * Extends from Application, a JavaFX class.
 * @version 1.0
 */

public class App extends Application {

    /**
     * Method that initializes the application.
     * @param primaryStage Main stage of the application.
     * @throws IOException If the FXML file is not found or there is an error while the user logs in.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Jokify");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/LoginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        LoginController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    /**
     * Main method of the application.
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
