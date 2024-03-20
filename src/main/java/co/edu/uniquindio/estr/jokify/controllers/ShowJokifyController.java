package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ArrayList;
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
    private User currentUser;
    private final Store store = Store.getInstance();

    /**
     * Get some content of the menuController and init content
     * @param currentUser
     */
    public void init(User currentUser) {
        this.currentUser = currentUser;
        ArrayList<Song> songs1 = store.getFiveSongs();
        showRecomendedSongs(songs1);
    }

    /**
     * Shows the songs in the flowPane
     * @param songs
     */
    private void showRecomendedSongs(ArrayList<Song> songs) {
        if (songs.size() != 0) {
            for (int i = 0; i < songs.size(); i++) {
                if (i == 0 && songs.get(i) != null) {
                    lblNS1.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS1.setImage(aux);
                } else if (i == 1 && songs.get(i) != null) {
                    lblNS2.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS2.setImage(aux);
                } else if (i == 2 && songs.get(i) != null) {
                    lblNS3.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS3.setImage(aux);
                } else if (i == 3 && songs.get(i) != null) {
                    lblNS4.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS4.setImage(aux);
                } else if (i == 4 && songs.get(i) != null) {
                    lblNS5.setText(songs.get(i).getName());
                    Image aux = new Image(songs.get(i).getCover());
                    imageNS5.setImage(aux);
                }
            }
        }
    }

    /*private void showRecomendedSongs(ArrayList<Song> songs) {
        if (songs.size() != 0) {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i) != null) {
                    // Configurar el texto de la etiqueta para el nombre de la canción
                    switch (i) {
                        case 0:
                            lblNS1.setText(songs.get(i).getName());
                            imageNS1 = new ImageView(songs.get(i).getCover());
                            imageNS1.setFitWidth(100); // Establecer el ancho deseado en píxeles
                            imageNS1.setFitHeight(100); // Establecer la altura deseada en píxeles
                            break;
                        case 1:
                            lblNS2.setText(songs.get(i).getName());
                            imageNS2 = new ImageView(songs.get(i).getCover());
                            imageNS2.setFitWidth(100);
                            imageNS2.setFitHeight(100);
                            break;
                        case 2:
                            lblNS3.setText(songs.get(i).getName());
                            imageNS3 = new ImageView(songs.get(i).getCover());
                            imageNS3.setFitWidth(100);
                            imageNS3.setFitHeight(100);
                            break;
                        case 3:
                            lblNS4.setText(songs.get(i).getName());
                            imageNS4 = new ImageView(songs.get(i).getCover());
                            imageNS4.setFitWidth(100);
                            imageNS4.setFitHeight(100);
                            break;
                        case 4:
                            lblNS5.setText(songs.get(i).getName());
                            imageNS5 = new ImageView(songs.get(i).getCover());
                            imageNS5.setFitWidth(100);
                            imageNS5.setFitHeight(100);
                            break;
                        default:
                            break;
                    }
                }
            }
            // Agregar las ImageView al FlowPane
            flowPaneFavoriteSongs1.getChildren().addAll(imageNS1, imageNS2, imageNS3, imageNS4, imageNS5);
        }
    }*/
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
