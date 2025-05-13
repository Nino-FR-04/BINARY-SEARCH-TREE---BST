package impl_Flores_Nino.BinarySearchTree;

import impl_Flores_Nino.Excepciones.ExceptionIsEmpty;
import impl_Flores_Nino.Excepciones.ExceptionItemDuplicated;
import impl_Flores_Nino.Excepciones.ExceptionItemNotFound;
import impl_Flores_Nino.Nodes.Node;

public class LinkedBST <E extends Comparable<E>> implements TADBinarySearchTree <E> {

    //Atributos
    private Node<E> root;

    //Constructor
    public LinkedBST() {
        this.root = null;
    }

    /**
     * Inserta un nuevo elemento en el árbol binario de búsqueda.
     *
     * <p>Si el árbol está vacío, el nuevo nodo se convierte en la raíz.
     * Si el valor ya existe en el árbol, se lanza una excepción para evitar duplicados.
     *
     * @param data el elemento que se desea insertar en el árbol
     * @throws ExceptionItemDuplicated si el elemento ya existe en el árbol
     * @throws IllegalArgumentException si el valor proporcionado es {@code null}
     */
    @Override
    public void insert(E data) throws ExceptionItemDuplicated {
        
        Node<E> nuevo = new Node<>(data);
        
        //Caso 1: Raiz vacia. Se le asigna un nuevo nodo a la raiz.
        if(this.root == null){
            this.root = nuevo;
            return;
        }

        //Caso 2: Mas nodos
        /*
         * Actual: Nodo actual - Sirve para recorrer el arbol
         * Padre: Nodo que sirve para hacer un seguimiento al padre del
         * nodo actual. 
         */
        Node<E> actual = this.root;
        Node<E> padre = null;

        while(true) {
            
            padre = actual;

            /*
             * Si el resultado de compareTo es 0, se inserta un dato
             * duplicado por lo que lanza esa excepcion
             */
            if(data.compareTo(actual.getData()) == 0) 
                throw new ExceptionItemDuplicated("No se puede insertar un valor duplicado");

            /*
             * Si la data a insertar es mayor que mi nodo actual (1) se
             * continua por el lado derecho. Caso contrario se actualiza
             * actual a su nodo izquierdo.
             */
            if(data.compareTo(actual.getData()) > 0) {
                actual = actual.getRight();
            }else {
                actual = actual.getLeft();
            }

            /*
             * Si mi var actual es null se llego al final del recorrido
             * por lo que se rompe el bucle
             */
            if(actual == null) break;
        }

        /*
         * Comparacion final: si la data a insertar es mayor que el valor de la
         * data del nodo padre, se inserta a la derecha, caso contrario la insercio
         * se hace a la izquierda.
         */
        if(data.compareTo(padre.getData()) > 0) {
            padre.setRight(nuevo);
        }else {
            padre.setLeft(nuevo);
        }

    }

    /**
     * Busca un elemento en el árbol binario de búsqueda.
     *
     * <p>El recorrido se realiza de forma iterativa desde la raíz.
     * Si el árbol está vacío o el elemento no se encuentra, se lanzan las excepciones correspondientes.
     *
     * @param data el elemento a buscar en el árbol
     * @return el dato almacenado que coincide con {@code data}
     * @throws ExceptionIsEmpty si el árbol está vacío
     * @throws ExceptionItemNotFound si el elemento no se encuentra en el árbol
     */
    @Override
    public E search(E data) throws ExceptionItemNotFound, ExceptionIsEmpty {
        if(this.isEmpty())
            throw new ExceptionIsEmpty("El arbol se encuentra vacio");
        
        //Sirve para recorrer el arbol
        Node<E> actual = this.root;

        while(actual != null) {
            
            if(data.compareTo(actual.getData()) == 0) {
                return actual.getData();
            }

            /*
             * Si la data a buscar es mayor que mi nodo actual (1) se
             * continua por el lado derecho. Caso contrario se actualiza
             * actual a su nodo izquierdo.
             */
            if(data.compareTo(actual.getData()) > 0) {
                actual = actual.getRight();
            }else {
                actual = actual.getLeft();
            }
        }

        /*
         * Si mi var actual es null se llego al final del recorrido
         * por lo que se lanza la excepcion que corresponde.
         */
        throw new ExceptionItemNotFound("Item no encontrado");
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if(this.isEmpty()) throw new ExceptionIsEmpty("Arbol vacio");


    }

    /**
     * Verifica si el árbol binario de búsqueda está vacío.
     *
     * @return {@code true} si el árbol no contiene ningún nodo, {@code false} en caso contrario
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }
}
