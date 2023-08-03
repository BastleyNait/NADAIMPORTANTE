package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AllProductosController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Almacen almacen = new Almacen();

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos CSV (*.csv)", "*.csv");
    
    public static AllProductosController instance;

    @FXML
    private Button BtnAddProductoDesdeArchivo;

    @FXML
    private Button BtnAddProductoManualmente;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSearchProducto;

    @FXML
    private TableColumn<Producto, String> codigo;

    @FXML
    private TableColumn<Producto, String> descripcion;

    @FXML
    private TableColumn<Producto, String> stock;

    @FXML
    private TableColumn<Producto, Button> eliminar;

    @FXML
    private TextField searchProducto;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    void BtnAddProductoDesdeArchivoClicked(MouseEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] dato = linea.split(",");
                    almacen.getGestorProductos().arbolProductos.insert(new Producto(Integer.parseInt(dato[0]) ,dato[1],Integer.parseInt(dato[2])));
                }
                Producto.almacen = almacen;
                cargarCeldas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void BtnAddProductoManualmenteClicked(MouseEvent event) throws IOException {
        AddProductoController.almacen = almacen;
        root = FXMLLoader.load(getClass().getResource("../FXML/AddProducto.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnReturnClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/AllAlmacenes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnSearchProductoClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().add(extFilter);
        cargarCeldas();
    }

    public void cargarCeldas() {
        ArrayList<Producto> productosList = almacen.getGestorProductos().getProductos();
        ObservableList<Producto> data = FXCollections.observableArrayList(productosList);
        codigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        stock.setCellValueFactory(new PropertyValueFactory<Producto, String>("stock"));
        eliminar.setCellValueFactory(new PropertyValueFactory<Producto, Button>("btnEliminar"));
        tabla.setItems(data);
    }
}
