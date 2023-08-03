package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class AllAlmacenesController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TableView<Almacen> table;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSearchAlmacen;

    @FXML
    private Button btnSearchProducto;

    @FXML
    private TextField searchAlmacen;

    @FXML
    private TextField searchProducto;

    @FXML
    private TableColumn<Almacen, String> codigo;

    @FXML
    private TableColumn<Almacen, String> direccion;

    @FXML
    private TableColumn<Almacen, String> nombre;

    @FXML
    private TableColumn<Almacen, Button> eliminar;

    @FXML
    private TableColumn<Almacen, Button> ver;

    public static AllAlmacenesController instance;

    @FXML
    void btnReturnClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/menuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnSearchAlmacenClicked(MouseEvent event) {

    }

    @FXML
    void btnSearchProductoClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        cargarCeldas();
    }

    public void cargarCeldas() {
        ObservableList<Almacen> data = FXCollections.observableArrayList();
        int i = 0;

        while (MenuPrincipalController.almacenes.getVertexAtIndex(i) != null) {
            data.add(MenuPrincipalController.almacenes.getVertexAtIndex(i).getData());
            i++;
        }

        codigo.setCellValueFactory(new PropertyValueFactory<Almacen, String>("codigoAlmacen"));
        nombre.setCellValueFactory(new PropertyValueFactory<Almacen, String>("nombreAlmacen"));
        direccion.setCellValueFactory(new PropertyValueFactory<Almacen, String>("direccionAlmacen"));
        eliminar.setCellValueFactory(new PropertyValueFactory<Almacen, Button>("btnEliminar"));
        ver.setCellValueFactory(new PropertyValueFactory<Almacen, Button>("btnVer"));

        table.setItems(data);
    }
}
