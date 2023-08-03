package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddViaController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    String numericRegex = "[-]?\\d*\\.?\\d*";

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnReturn;

    @FXML
    private TextField codigoDestino;

    @FXML
    private TextField codigoOrigen;

    @FXML
    private TextField distancia;

    @FXML
    void btnAgregarClicked(MouseEvent event) throws IOException {
        // Si todo salió bien
        root = FXMLLoader.load(getClass().getResource("../FXML/menuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnReturnClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/menuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
        if (change.getControlNewText().matches(numericRegex)) {
            return change;
        } else {
            return null;
        }
    });

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        distancia.setTextFormatter(textFormatter);
    }

}
