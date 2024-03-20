package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Store;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowJokifyController implements Initializable {

    @FXML
    private FlowPane flowPaneFavoriteSongs;

    @FXML
    private StackPane stackFS1;

    @FXML
    private ImageView imageFS1;

    @FXML
    private Label lblFS1;

    @FXML
    private StackPane stackFS2;

    @FXML
    private ImageView imageFS2;

    @FXML
    private Label lblFS2;

    @FXML
    private StackPane stackFS3;

    @FXML
    private ImageView imageFS3;

    @FXML
    private Label lblFS3;

    @FXML
    private StackPane stackFS4;

    @FXML
    private ImageView imageFS4;

    @FXML
    private Label lblFS4;

    @FXML
    private StackPane stackFS5;

    @FXML
    private ImageView imageFS5;

    @FXML
    private Label lblFS5;

    @FXML
    private Button btnShowMore;

    @FXML
    private Button btnDiscoverMore;

    @FXML
    private FlowPane flowPaneFavoriteSongs1;

    @FXML
    private StackPane stackNS1;

    @FXML
    private ImageView imageNS1;

    @FXML
    private Label lblNS1;

    @FXML
    private StackPane stackNS2;

    @FXML
    private ImageView imageNS2;

    @FXML
    private Label lblNS2;

    @FXML
    private StackPane stackNS3;

    @FXML
    private ImageView imageNS3;

    @FXML
    private Label lblNS3;

    @FXML
    private StackPane stackNS4;

    @FXML
    private ImageView imageNS4;

    @FXML
    private Label lblNS4;

    @FXML
    private StackPane stackNS5;

    @FXML
    private ImageView imageNS5;

    @FXML
    private Label lblNS5;

    //Aux variables
    private final Store store = Store.getInstance();

    /**
     * Initialize for the controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void discoverMore(ActionEvent event) {

    }

    @FXML
    void showMore(ActionEvent event) {

    }
}
