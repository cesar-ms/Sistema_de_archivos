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
public class NodoArbol {

    Nodo raiz;

    /**
     * Se declara el constructor
     *
     * @param id
     * @param nombre
     * @return
     */
    public Nodo insertarRaiz(String id, String nombre) {
        raiz = new Nodo(id, null, null, nombre);
        return raiz;
    }

    /**
     * Se mustran todos los nodos que estan el arbol
     *
     * @param nodo
     */
    public void verHijosRecursivo(Nodo nodo) {
        for (int i = 0; i < nodo.nohijos; i++) {
            nodo.hijos[i].verNodo();

            verHijosRecursivo(nodo.hijos[i]);
        }
    }

    /**
     * Se inserta los nodos con su informacion
     *
     * @param nodo
     * @param id
     * @param padre
     * @param tipo
     * @param nombre
     */
    public void insertarRecursivo(Nodo nodo, String id, String padre, String tipo, String nombre) {
        Nodo nuevo = new Nodo(id, padre, tipo, nombre);

        if (nodo.getDato().equals(padre)) {
            nodo.aumentarHijo(nuevo);
        } else {
            for (int i = 0; i < nodo.nohijos; i++) {
                if (nodo.hijos[i].getDato().equals(padre)) {
                    nodo.hijos[i].aumentarHijo(nuevo);
                } else {
                    insertarRecursivo(nodo.hijos[i], id, padre, tipo, nombre);
                }
            }
        }
    }

    /**
     * Se retorna la raiz
     *
     * @return
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * Se establece la raiz
     *
     * @param raiz
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

}
