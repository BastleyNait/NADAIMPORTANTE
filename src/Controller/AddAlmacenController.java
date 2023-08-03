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
import javax.swing.JOptionPane;

public class AddAlmacenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    String numericRegex = "[-]?\\d*\\.?\\d*";

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnReturn;

    @FXML
    private TextField codigo;

    @FXML
    private TextField direccion;

    @FXML
    private TextField nombre;

    @FXML
    void btnAgregarClicked(MouseEvent event) throws IOException {
        Almacen almacen = new Almacen(Integer.parseInt(codigo.getText()), nombre.getText(), direccion.getText());
       
        MenuPrincipalController.almacenes.insertVertex(almacen);

        root = FXMLLoader.load(getClass().getResource("../FXML/menuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        JOptionPane.showMessageDialog(null, "Almacén agregado correctamente", "ALMACÉN AGREGADO", JOptionPane.INFORMATION_MESSAGE);
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
        codigo.setTextFormatter(textFormatter);
    }
}
