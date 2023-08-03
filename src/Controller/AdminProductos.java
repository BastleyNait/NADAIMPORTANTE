package Controller;

import java.util.ArrayList;
import Controller.myExceptions.ExceptionNoFound;

public class AdminProductos {

    protected AVL<Producto> arbolProductos;

    public AdminProductos() {
        arbolProductos = new AVL();
    }

    public void agregarProductos(int codigoProducto, String descripcionProducto, int stockProducto) throws ExceptionNoFound {
        arbolProductos.insert(new Producto(codigoProducto, descripcionProducto, stockProducto));
    }

    public void agregarProductos(Producto producto) throws ExceptionNoFound {
        arbolProductos.insert(producto);
    }

    public void darDeBaja(Producto producto) throws ExceptionNoFound {
        arbolProductos.remove(producto);
    }

    public Producto buscarProducto(int codigoProducto) throws ExceptionNoFound {
        return arbolProductos.search(new Producto(codigoProducto, "", 0));
    }

    public ArrayList<Producto> getProductos() {
        return arbolProductos.inOrderAVL();
    }
}
