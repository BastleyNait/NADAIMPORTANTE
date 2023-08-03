package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuPrincipalController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static GraphLink<Almacen> almacenes = new GraphLink<>();

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos CSV (*.csv)", "*.csv");

    @FXML
    private Button btnAddAlmacenManualmente;
    @FXML
    private Button btnAddAlmacenesConArchivo;
    @FXML
    private Button btnAddViasConArchivo;
    @FXML
    private Button btnAddViasManualmente;
    @FXML
    private Button btnObtenerRutas;
    @FXML
    private Button btnTodos;

    @FXML
    void btnAddAlmacenManualmenteClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/AddAlmacen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnAddAlmacenesConArchivoClicked(MouseEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                almacenes = new GraphLink<>();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] dato = linea.split(",");
                    Almacen almacen = new Almacen(Integer.parseInt(dato[0]), dato[1], dato[2]);
                    almacenes.insertVertex(almacen);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnAddViasConArchivoClicked(MouseEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] dato = linea.split(",");
                    Almacen origen = almacenes.getVertexByCode(Integer.parseInt(dato[0]));
                    Almacen destino = almacenes.getVertexByCode(Integer.parseInt(dato[2]));
                    almacenes.insertEdge(origen, Integer.parseInt(dato[1]), destino);
                }
                System.out.println(almacenes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnAddViasManualmenteClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/AddVia.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnObtenerRutasClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/GetRutasDistribucion.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void mostrarTodosClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../FXML/AllAlmacenes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().add(extFilter);
    }
}
