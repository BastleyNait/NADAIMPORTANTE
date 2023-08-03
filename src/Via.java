import Controller.*;
import Controller.myExceptions.ExceptionNoFound;
import javafx.event.Event;
import javafx.scene.control.Button;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Via {
    private int origenCodigo;
    private int distancia;
    private int destinoCodigo;
    @Override
    public String toString() {
        return "Via => origenCodigo = "
                + origenCodigo
                + ", distancia = " + distancia
                + ", destinoCodigo = " + destinoCodigo;
    }

    public Via(int origenCodigo, int distancia, int destinoCodigo) {
        this.origenCodigo = origenCodigo;
        this.distancia = distancia;
        this.destinoCodigo = destinoCodigo;
    }

    public int getOrigenCodigo() {
        return origenCodigo;
    }

    public void setOrigenCodigo(int origenCodigo) {
        this.origenCodigo = origenCodigo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getDestinoCodigo() {
        return destinoCodigo;
    }

    public void setDestinoCodigo(int destinoCodigo) {
        this.destinoCodigo = destinoCodigo;
    }
}
