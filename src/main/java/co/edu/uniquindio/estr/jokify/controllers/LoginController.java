package co.edu.uniquindio.estr.jokify.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField textFieldUser;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hpCreateAcount;

    private Stage stage;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void show() {
        stage.show();
    }

    @FXML
    void createAcount(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {

    }

}

