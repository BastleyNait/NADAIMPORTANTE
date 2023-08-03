package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Almacen implements Comparable<Almacen> {

    private int codigoAlmacen;
    private String nombreAlmacen;
    private String direccionAlmacen;
    private AdminProductos gestorProductos;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Button btnEliminar = new Button("Eliminar");
    private Button btnVer = new Button("Ver");

    private AllAlmacenesController allAlmacenesController;

    public Almacen(int codigoAlmacen, String nombreAlmacen, String direccionAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
        this.nombreAlmacen = nombreAlmacen;
        this.direccionAlmacen = direccionAlmacen;
        this.gestorProductos = new AdminProductos();
        this.btnVer.setOnMouseReleased((Event event) -> {
            try {
                AllProductosController.almacen = Almacen.this;
                root = FXMLLoader.load(getClass().getResource("../FXML/AllProductos.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btnEliminar.setOnMouseReleased((Event arg0) -> {
            MenuPrincipalController.almacenes.removeVertex(Almacen.this);
            if (AllAlmacenesController.instance != null) {
                AllAlmacenesController.instance.cargarCeldas();
            }
        });
    }

    public Almacen() {
    }

    public void setAllAlmacenesController(AllAlmacenesController allAlmacenesController) {
        this.allAlmacenesController = allAlmacenesController;
    }

    public int getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(int codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public String getDireccionAlmacen() {
        return direccionAlmacen;
    }

    public void setDireccionAlmacen(String direccionAlmacen) {
        this.direccionAlmacen = direccionAlmacen;
    }

    public Button getBtnVer() {
        return btnVer;
    }

    public void setBtnVer(Button btnVer) {
        this.btnVer = btnVer;
    }

    public void setBtnEliminar(Button btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    @Override
    public String toString() {
        return "Controller.Almacen [codigoAlmacen=" + codigoAlmacen;
    }

    public AdminProductos getGestorProductos() {
        return gestorProductos;
    }

    @Override
    public int compareTo(Almacen o) {
        if (this.codigoAlmacen > o.getCodigoAlmacen()) {
            return 1;
        } else if (this.codigoAlmacen == o.getCodigoAlmacen()) {
            return 0;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Almacen almacen = (Almacen) o;

        return this.codigoAlmacen == almacen.codigoAlmacen;
    }

}
