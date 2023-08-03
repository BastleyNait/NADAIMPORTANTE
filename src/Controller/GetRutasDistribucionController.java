package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GetRutasDistribucionController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField almacenDestino;

    @FXML
    private TextField almacenOrigen;

    @FXML
    private TextField almacenesAVisitar;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnRuta;

    @FXML
    private Pane grafico;

    @FXML
    private TextArea rutaCorta;

    @FXML
    void btnReturnClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/menuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnRutaClicked(MouseEvent event) {

    }
}
