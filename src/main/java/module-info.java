module co.edu.uniquindio.estr.jokify.application {
    requires javafx.controls;
    requires java.desktop;
    requires java.logging;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires org.testng;
    requires org.junit.jupiter.api;


    opens co.edu.uniquindio.estr.jokify.application to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.estr.jokify.model to javafx.base;
    opens co.edu.uniquindio.estr.jokify.controllers to javafx.fxml;
    exports co.edu.uniquindio.estr.jokify.model;
    exports co.edu.uniquindio.estr.jokify.structures;
    exports co.edu.uniquindio.estr.jokify.test to org.testng, org.junit.jupiter.api;
}