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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Controller.myExceptions.ExceptionNoFound;

public class AddProductoController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    String numericRegex = "[-]?\\d*\\.?\\d*";

    public static Almacen almacen = new Almacen();
    
    private Producto producto;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnReturn;

    @FXML
    private TextField codigoProducto;

    @FXML
    private TextField descripcionProducto;

    @FXML
    private Text nombreAlmacen;

    @FXML
    private TextField stockProducto;



    @FXML
    void btnAgregarClicked(MouseEvent event) throws IOException, ExceptionNoFound {

        producto = new Producto(Integer.parseInt(codigoProducto.getText()), descripcionProducto.getText(), Integer.parseInt(stockProducto.getText()));
        producto.almacen = almacen;
        almacen.getGestorProductos().agregarProductos(producto);
        AllProductosController.almacen = almacen;

        root = FXMLLoader.load(getClass().getResource("../FXML/AllProductos.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        JOptionPane.showMessageDialog(null, "Producto agregado correctamente", "PRODUCTO AGREGADO", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    void btnReturnClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/AllProductos.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    TextFormatter<String> textFormatterCodigo = new TextFormatter<>(change -> {
        if (change.getControlNewText().matches(numericRegex)) {
            return change;
        } else {
            return null;
        }
    });

    TextFormatter<String> textFormatterStock = new TextFormatter<>(change -> {
        if (change.getControlNewText().matches(numericRegex)) {
            return change;
        } else {
            return null;
        }
    });

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        codigoProducto.setTextFormatter(textFormatterCodigo);
        stockProducto.setTextFormatter(textFormatterStock);
    }

}
