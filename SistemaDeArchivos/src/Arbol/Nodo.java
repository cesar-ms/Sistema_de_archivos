/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

/**
 *
 * @author Julio Cesar Morales Sierra
 */
public class Nodo {
   
   public String id;
   public String padre;
   public String tipo;
   public String nombre;
   public int nohijos =0;
   public Nodo hijosT[];
   public Nodo hijos[];
   /**
    * Se declaraencuentran el constructor
    * @param dato
    * @param padre
    * @param tipo
    * @param nombre 
    */
   public Nodo(String dato,String padre,String tipo, String nombre){
       this.id=dato;
       this.padre = padre;
       this.tipo = tipo;
       this.nombre= nombre;
       this.nohijos = 0;
       
   }
   /**
    * Se copian los nodos hijos 
    */
   public void copiarHijos(){
       hijosT=new Nodo[nohijos+1];
       for (int i = 0;  i< this.nohijos; i++) {
           hijosT[i]= hijos[i];
       }
   }
   /**
    * Aumenta el numero de nodos hijos
    * @param nodo 
    */
   public void aumentarHijo(Nodo nodo){
       copiarHijos();
       hijosT[this.nohijos]=nodo;
       hijos = hijosT;
       this.nohijos++;
   }
   /**
    * Regresa el dato id 
    * @return 
    */
   public String getDato(){
       return id;
   }
   /**
    * Establece el dato id
    * @param dato 
    */
   public void setDato(String dato){
       this.id = dato;
   }
   /**
    * Retorna el nombre de la raiz
    * @return 
    */
   public String getNombreRaiz(){
       return nombre; 
   }
   /**
    * Se muestra el nombre de los archivos y carpetas
    */
   public void verNodo(){
       System.out.println("{"+nombre+"}");
   }
   
}
