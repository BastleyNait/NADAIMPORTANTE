package Controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManejoCSV {

    private String archivoCSV;

    public ManejoCSV(String archivoCSV) {
        this.archivoCSV = archivoCSV;
    }

    /*public void actualizarCSV(AVL<Producto> arbolProductos) throws CsvException {
        ArrayList<String> data = arbolProductos.inOrden();
        for (String linea : data) {
            String[] item = linea.split(",");
            actualizarCSV(Integer.parseInt(item[0]), item[1], Integer.parseInt(item[2]));
        }
    }*/

    private void actualizarCSV(int id, String descripcion, int stock) throws CsvException {
        try {
            // Leer el contenido actual del archivo CSV
            CSVReader reader = new CSVReader(new FileReader(archivoCSV));
            List<String[]> registros = reader.readAll();
            reader.close();

            // Verificar si el ID ya existe
            for (String[] registro : registros) {
                if (registro.length == 3 && Integer.parseInt(registro[0]) == id) {
                    System.out.println("El ID ya existe en el archivo CSV. No se puede insertar.");
                    return;
                }
            }

            // Agregar el nuevo registro al final del archivo
            registros.add(new String[]{String.valueOf(id), descripcion, String.valueOf(stock)});

            // Escribir los cambios de nuevo al archivo CSV
            CSVWriter writer = new CSVWriter(new FileWriter(archivoCSV));
            writer.writeAll(registros);
            writer.close();

            System.out.println("Registro insertado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void editar(int id, String nuevaDescripcion, int nuevoStock) throws CsvException {
        try {
            // Leer el contenido actual del archivo CSV
            CSVReader reader = new CSVReader(new FileReader(archivoCSV));
            List<String[]> registros = reader.readAll();
            reader.close();

            // Modificar el registro correspondiente al ID proporcionado
            boolean encontrado = false;
            for (String[] registro : registros) {
                if (registro.length == 3 && Integer.parseInt(registro[0]) == id) {
                    registro[1] = nuevaDescripcion;
                    registro[2] = String.valueOf(nuevoStock);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró el ID especificado en el archivo CSV.");
                return;
            }

            // Escribir los cambios de nuevo al archivo CSV
            CSVWriter writer = new CSVWriter(new FileWriter(archivoCSV));
            writer.writeAll(registros);
            writer.close();

            System.out.println("Registro editado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String[] buscarPorId(int id) throws CsvException{
        try {
            // Leer el contenido actual del archivo CSV
            CSVReader reader = new CSVReader(new FileReader(archivoCSV));
            List<String[]> registros = reader.readAll();
            reader.close();

            // Buscar el registro correspondiente al ID proporcionado
            for (String[] registro : registros) {
                if (registro.length == 3 && Integer.parseInt(registro[0]) == id) {
                    String[] resultado = new String[3];
                    resultado[0] = registro[0];
                    resultado[1] = registro[1];
                    resultado[2] = registro[2];
                    return resultado;
                }
            }

            System.out.println("No se encontró el ID especificado en el archivo CSV.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }*/
}
