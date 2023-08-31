/*
 *En esta clase se simula el sistema de archivo
 * Para moverte de carpeta en carpeta debes colocar el símbolo “$” al final del nombre de 
    la carpeta, este funciona similar a la barra “\” de Windows. 
    
    Simbología:
    mos->muestra el contenido de una carpeta
    at->regresa una carpeta atrás. 
    root->regresa a la carpeta raíz
    hom->cambia el nombre de una carpeta o archivo
    ler->lee el contenido de un archivo
    add->añade contenido al archivo
    mak->crea una carpeta o archivo
    cpy->copia
    mov->mueve
    elm->elimina
    ex->sale del sistema de archivos.
 */
package sistemadearchivos;

import Arbol.Nodo;
import Arbol.NodoArbol;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemadearchivos.Lectura.LecturaArchivo;
import Herramientas.Herramienta;
import java.util.Scanner;

/**
 *
 * @author Julio Cesar Morales Sierra
 *
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<String> sistema = new ArrayList<>();
        LecturaArchivo l = new LecturaArchivo("..\\SistemaDeArchivos\\sistema.txt");
        NodoArbol arbol = new NodoArbol();
        Nodo nodo = arbol.insertarRaiz("0", "Z:");
        Herramienta h = new Herramienta(sistema, nodo, arbol);
        ArrayList<String> tem = new ArrayList<>();

        Scanner entrada = new Scanner(System.in);
        sistema = l.leer();

        String cadena = "";
        // Recorremos el arrayList
        /*
        for (int i = 0; i < sistema.size(); i++) {
            System.out.println(sistema.get(i));
        }
        */
        // Ingresamos al arbol las carpetas y archivos
        for (int i = 0; i < sistema.size(); i++) {
            cadena = sistema.get(i);
            h.informacion(cadena);
            if (h.getId() != "") {
                arbol.insertarRecursivo(nodo, h.getId(), h.getPadre(), h.getTipo(), h.getNombre());
            }
            h.setLinea(i);
            h.setId("");
            h.setPadre("");
            h.setTipo("");
            h.setNombre("");
        }
        //Simulacion de una terminal
        System.out.println("---------------------------------------------------------------------------------------------------------");
        String op = "";
        String ruta = "";
        do {
            System.out.print("\n"+arbol.getRaiz().nombre + "$" + ruta);
            op = entrada.nextLine();
            if (!op.equals("ex") && !op.equals("mos") && !op.equals("elm") && !op.equals("at") && !op.equals("raiz") && !op.equals("hom") && !op.equals("ler")) {
                ruta += op;
            }
           
            Nodo padre = h.ruta(arbol.getRaiz(), ruta);
            ruta = h.obtenerRuta();
            switch (op) {
                case "mos":
                    h.mostrar(padre);
                    break;
                case "at":
                    ruta = h.atras(ruta);
                    h.ruta(arbol.getRaiz(), ruta);
                    break;
                case "root":
                    ruta = h.regresarRaiz(ruta);
                    break;
                case "hom":
                    System.out.println("Digita el nuevo nombre: ");
                    String nombre = entrada.nextLine();
                    h.cambiarNombre(padre, nombre);
                    break;
                case "ler":
                    h.leerContenido(padre, sistema);
                    break;
                case "add":
                    System.out.println("Digita tu cadena de texo");
                    String cad = entrada.nextLine();
                    h.modificarContenido(padre, sistema, cad);
                    break;
                case "mak":
                    System.out.println("Ingresa el nombre: ");
                    String nom = entrada.nextLine();
                    System.out.println("Ingresa el tipo archivo");
                    String tip = entrada.next();
                    h.crear(padre, tip, nom);
                    break;
                case "cpy":
                    h.copiar(padre);
                    break;
                case "mov":
                    h.mover(padre, h.copiar(padre));
                    break;
                case "elm":
                    h.eliminar(arbol.getRaiz(), ruta);
                    break;
            }
            
        } while (!"ex".equals(op));
        
    }

}
