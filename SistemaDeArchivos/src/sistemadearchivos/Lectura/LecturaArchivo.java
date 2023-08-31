package sistemadearchivos.Lectura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zaaks
 */
public class LecturaArchivo {

    String ruta;
    ArrayList<String> datos = new ArrayList<>();

    public LecturaArchivo(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Lee el archivo y lo guarda en un array List
     */
    public ArrayList<String> leer() {
        try {
            FileReader archivo = new FileReader(ruta);
            BufferedReader lectura = new BufferedReader(archivo);
            String cadena;

            while ((cadena = lectura.readLine()) != null) {
                datos.add(cadena);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }

    /**
     * Se carga los nuevos datos al archivo de sistema
     */
    public void cargar() {
        FileWriter fw = null;
        try {
            File f = new File(ruta);
            fw = new FileWriter(f);
            PrintWriter escritura = new PrintWriter(fw);
            for (int i = 0; i < datos.size(); i++) {
                escritura.println(datos.get(i));
            }
            escritura.close();
        } catch (IOException ex) {
            Logger.getLogger(LecturaArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(LecturaArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
