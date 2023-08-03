package Controller;

import java.io.*;
import java.util.ArrayList;

public class LectorCSV {
    public static ArrayList<String> leerArchivo(String ruta) throws IOException {
        ArrayList<String> datos=new ArrayList<String>();
        File archivo = new File(ruta);
        FileReader leerArchivo = new FileReader(archivo);
        BufferedReader buffer = new BufferedReader(leerArchivo);
        String linea;
        buffer.readLine();
        while((linea=buffer.readLine())!= null){
            String [] lineaVector = linea.split(",");
            datos.add(lineaVector[0]);
            datos.add(lineaVector[1]);
            datos.add(lineaVector[2]);
        }
        buffer.close();
        leerArchivo.close();
        return datos;
    }

}
