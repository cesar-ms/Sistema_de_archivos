/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import Arbol.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio Cesar Morales Sierra
 */
public class Herramienta {

    ArrayList<String> sistema = new ArrayList<>();
    Nodo nodo;
    NodoArbol nodoA;
    boolean bandera = false;
    private String id = "";
    private String padre = "";
    private String tipo ="";
    private String nombre = "";
    private String ruta = "";
    private int linea=0;
    /**
     * Se crea el constructor 
     * @param sistema
     * @param nodo
     * @param nodoa 
     */
    public Herramienta(ArrayList<String> sistema,Nodo nodo,NodoArbol nodoa) {
        this.sistema = sistema;
        this.nodo = nodo;
        this.nodoA = nodoa;
        this.id ="";
        this.tipo="";
        this.nombre ="";
        this.ruta ="";
    }
    /**
     * Obtiene las carpetas y archivos que se encuentran en toda nuestro sistema.
     * @param cadena 
     */
    public void informacion(String cadena){
        boolean bandera = false;
        int k=0;
        while(cadena.charAt(k) !='('){
            if((esLetra(cadena.charAt(k))) || (cadena.charAt(k)=='?')||(cadena.charAt(k)=='=')){
                 bandera = false;
                 k=0;
                 break;
            }else{
                bandera=true;
            }
            k++;
        }
        if((cadena.charAt(k) =='(')){
                int j=k;
                while(j < cadena.length() ){
                    switch (cadena.charAt(j)) {
                        case '(':
                            ++j;
                            while(cadena.charAt(j) !=')'){
                                id +=cadena.charAt(j);
                                j++;
                            }
                            break;
                        case '-':
                            ++j;
                            while(cadena.charAt(j) !='-'){
                                tipo +=cadena.charAt(j);
                                j++;
                            }
                            break;
                        case '>':
                            ++j;
                            while(cadena.charAt(j) !='<'){                    
                                nombre +=cadena.charAt(j);
                                j++;
                            }
                            break;
                        default:
                            System.out.println("ALGO SALIO MAL");
                            break;
                    }
                    j++;
                }
                
            }else{
                //System.out.println("NO puedo Acceder-->Linea: "+linea);
            }

            if(id.length() >1){
               for(int i=0; i < id.length()-2; i++){
                 padre += id.charAt(i);  
               }
            }else{
                padre="0";
            }
    }
    /**
     * Regresa la linea en la que se encutra la cadena
     * @param cadena
     * @return 
     */
    public int getLinea(String cadena){
        int i =0;
        while(i < sistema.size()){
            if(sistema.get(i).equals(cadena)){
                return i;
            }else{
                System.out.println("No existe");
            }
        }   
      return  -1;
    }
    /**
     * Crea un archivo o carpeta solo si en la ruta actual diferente de archivo
     * @param padre
     * @param tipo
     * @param nombre 
     */
    public void crear(Nodo padre, String tipo, String nombre){
        if(!padre.tipo.equals("Archivo")){
            String pa = padre.id;
            int x=1;
            boolean ban = false;
            String nuevoID =pa+"."+x;;
            for (int i = 0; i < padre.nohijos; i++){
                if(padre.hijos[i].id.equals(nuevoID)){
                    ++x;
                    ban = false;
                }else{
                    nodoA.insertarRecursivo(padre, nuevoID, padre.id, tipo, nombre);
                    ban = true;
                    break;
                }
            }
            nuevoID = pa+"."+x;
            nodoA.insertarRecursivo(padre, nuevoID, padre.id, tipo, nombre);
            
        }else{
            System.out.println("Error-->Esto es un archivo___>No puedo crear una carpeta");
        }
    }
    public void cambiarNombre(Nodo actual, String nombre){
        actual.nombre = nombre;
    }
    /**
     * Copia los archivos o carpetas de la direccion actual 
     * @param padre
     * @return 
     */
    public Nodo[] copiar(Nodo padre){
        Nodo hijosT[]=null;
        if(padre!=null){
            if(padre.nohijos > 0){
                hijosT = new Nodo[padre.nohijos+1];
                for (int i = 0; i < padre.nohijos ; i++) {
                        hijosT[i] = padre.hijos[i];
                }
            }else{
                System.out.println("No hay contenido para copiar");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No reconocible");
        }
        return hijosT;
    }
    /**
     * Mueve los elementos de una carpeta o archivo a un direccion nueva 
     * @param padre
     * @param copia 
     */
    public void mover(Nodo padre,Nodo[] copia){
        for (int i = 0; i < copia.length ; i++){
             nodoA.insertarRecursivo(padre, copia[i].id,copia[i].padre,copia[i].tipo,copia[i].nombre);
        }
       
    }
    /**
     * Este modifica el contenido de un archivo 
     * @param actual
     * @param sistema
     * @param cadenas 
     */
    public void modificarContenido(Nodo actual,ArrayList<String> sistema,String cadenas){
        if(!actual.tipo.equals("Carpeta")){
            String cad = "("+actual.id+")"+"-"+actual.tipo+"->"+actual.nombre+"<";
            int indice = sistema.indexOf(cad)+1;
            if((indice != -1)){
                while (!sistema.get(indice).equals("=====================") && (!sistema.get(indice).equals("?"))) {                
                    System.out.println(sistema.get(indice));
                    indice ++;
                }
                if(sistema.get(indice).equals("?")){
                    sistema.remove(indice);
                    sistema.add(indice, cadenas);
                }else{
                     sistema.add(indice, cadenas);
                }
            }else{
                System.out.println("No encotrado");
            }
        }else{
             System.out.println("Error, esto es una carpeta");
        }
    }
    /**
     * Este metodo lee el de un archivo.
     * @param actual
     * @param sistema 
     */
    public void leerContenido(Nodo actual, ArrayList<String> sistema){
        if(!actual.tipo.equals("Carpeta")){
            String cadena = "("+actual.id+")"+"-"+actual.tipo+"->"+actual.nombre+"<";
            int indice = sistema.indexOf(cadena)+1;
            if((indice != -1) && (!sistema.get(indice).equals("?"))){
                while (!sistema.get(indice).equals("=====================")) {                
                    System.out.println(sistema.get(indice));
                    indice ++;
                }
            }else{
                System.out.println("Sin contenido");
            }
        }else{
            System.out.println("Error, esto es una carpeta");
        }
    }
    /**
     * El metodo hace un retroceso a la direccion anterior 
     * @param ruta
     * @return 
     */
    public String atras(String ruta){
        String nuevaRuta ="";
        int contador = ruta.length()-2;
        int j =0;
        if(!ruta.equals("Z:")){
            while( (contador > 0) && (ruta.charAt(contador) != '$')){
                j++;
                contador--;
            }
            for (int i = 0; i < ruta.length()-j-1; i++) {
                nuevaRuta += ruta.charAt(i);
            }
            if(nuevaRuta.length()==1){
                return "";
            }
        }
        return nuevaRuta;
    }
    public String regresarRaiz(String ruta){
        return "";
    }
    
    /**
     * El metodo retorna el identificador que se encontro de la cadena ingresada
     * @param cadena
     * @return 
     */
    public String buscar(String cadena){
        String a ="";
        int k=0;
        while(cadena.charAt(k) !='('){
            if((esLetra(cadena.charAt(k))) || (cadena.charAt(k)=='?')||(cadena.charAt(k)=='=')){
                 bandera = false;
                 k=0;
                 break;
            }else{
                bandera=true;
            }
            k++;
        }
        if((cadena.charAt(k) =='(')){
                int j=k;
                while((j < cadena.length()) && (cadena.charAt(j)!='-')){
                    switch (cadena.charAt(j)) {
                        case '(':
                            ++j;
                            while(cadena.charAt(j) !=')'){
                                a +=cadena.charAt(j);
                                j++;
                            }
                            break;
                        default:
                            System.out.println("Algo salio mal--->verifica");
                            break;
                    }
                    j++;
                }
                return a;
        }
        return "-1";
    }
    /**
     * Metodo que determina si el caracter es letra.
     * @param c
     * @return 
     */
    public boolean esLetra( char c){
        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return false;
        }
        return true;
    }
    /**
     * El metodo soporta rutas absolutas
     * @param padre
     * @param ruta
     * @return 
     */
    public Nodo ruta(Nodo padre,String ruta){
        int i=0;
        String n ="";
        String rutaValida = "";
        boolean bandera = false;
        Nodo aux = padre;
        if(padre != null){
            while(i < ruta.length()){
                if(ruta.charAt(i) != '$'){
                    n += ruta.charAt(i);
                }else{ 
                    for (int j =0; j < aux.nohijos; j++) { 
                        if(aux.hijos[j].nombre.equals(n)){
                            aux = aux.hijos[j];
                            rutaValida += n+"$";
                            bandera = false;
                            break;
                        }else{
                            bandera = true;
                        }
                    }
                    n="";
                }
                i++;
            }
            guardarRuta(rutaValida);
            if(bandera == true){
                System.out.println("¡No se encontró la carpeta o archivo!");
            }
        }
        return aux;
    }
    public void guardarRuta(String ruta){
        this.ruta = ruta;
    }
    public  String obtenerRuta(){
        return ruta;
    }
    
    /**
     * Metodo muestra todas las carpetas o archivo en carpeta actual. 
     * @param mos 
     */
    public void mostrar(Nodo mos){
        if(mos!=null){
            if(mos.nohijos > 0){
                for (int i = 0; i < mos.nohijos ; i++) {
                        mos.hijos[i].verNodo();
                }
            }else{
                System.out.println("La carpeta esta vacia");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No reconocible");
        }
    }
    /**
     * Metodo elimina la carpeta o archivo 
     * @param padre
     * @param ruta 
     */
    public void eliminar(Nodo padre,String ruta){
       int i=0;
        String n =""; 
        boolean bandera = false;
        Nodo aux = padre;
        Nodo temp;
        if(padre != null){
            while(i < ruta.length()){
                if(ruta.charAt(i) != '$'){
                    n += ruta.charAt(i);
                }else{ 
                    for (int j =0; j < aux.nohijos; j++) { 
                        if(aux.hijos[j].nombre.equals(n)){
                            aux = aux.hijos[j];
                            if(aux.id.equals("1.1.1")){
                                 aux = null;
                            }
                            break;
                        }else{
                            System.out.println("No existe directorio");
                        }
                    }
                    n="";
                }
                
                i++;
            }
             
        }
      
    }
    /**
     * Gets y sets 
     */
    public String getId() {
        return id;
    }

    public String getPadre() {
        return padre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
