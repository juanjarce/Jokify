package co.edu.uniquindio.estr.jokify.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private Stage stage;

    @FXML
    private TextField inputUsuario;

    @FXML
    private PasswordField inputContrasenia;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    void ingresarAction(ActionEvent event) {

    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void show() {
        stage.show();
    }

}

