package co.edu.uniquindio.estr.jokify.controllers;

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
