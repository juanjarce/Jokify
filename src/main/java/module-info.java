module co.edu.uniquindio.estr.jokify.application {
    requires javafx.controls;
    requires java.desktop;
    requires java.logging;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens co.edu.uniquindio.estr.jokify.application to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.estr.jokify.model to javafx.base;
    opens co.edu.uniquindio.estr.jokify.controllers to javafx.fxml;
}