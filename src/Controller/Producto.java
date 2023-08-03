package Controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Button;
import Controller.myExceptions.ExceptionNoFound;

public class Producto implements Comparable<Producto> {

    private int codigo;
    private String descripcion;
    private int stock;
    private Button btnEliminar = new Button("Eliminar");

    static Almacen almacen;

    private AllProductosController allProductosController;

    public Producto(int codigo, String descripcion, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.btnEliminar.setOnMouseReleased((Event arg0) -> {
            try {
                System.out.println("elim");
                almacen.getGestorProductos().darDeBaja(Producto.this);
                if (AllProductosController.instance != null) {
                    AllProductosController.instance.cargarCeldas();
                }
            } catch (ExceptionNoFound ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public Producto() {
    }
    
    public void setAllProductosController(AllProductosController allProductosController) {
        this.allProductosController = allProductosController;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    @Override
    public int compareTo(Producto o) {
        if (o instanceof Producto) {
            Producto comparaProducto = o;
            if (this.codigo < comparaProducto.getCodigo()) {
                return -1;
            } else if (this.codigo == comparaProducto.getCodigo()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        }
    }

    @Override
    public String toString() {
        return this.codigo + "," + this.descripcion + "," + this.stock;
    }
}
