package impl_Flores_Nino.BinarySearchTree;

import impl_Flores_Nino.Excepciones.ExceptionIsEmpty;
import impl_Flores_Nino.Excepciones.ExceptionItemDuplicated;
import impl_Flores_Nino.Excepciones.ExceptionItemNotFound;
import impl_Flores_Nino.Nodes.NodeTree;

public class LinkedBST <E extends Comparable<E>> implements TADBinarySearchTree <E> {

    //Atributos
    private NodeTree<E> root;

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
        
        NodeTree<E> nuevo = new NodeTree<>(data);
        
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
        NodeTree<E> actual = this.root;
        NodeTree<E> padre = null;

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
        NodeTree<E> actual = this.root;

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

    /**
     * Elimina un elemento específico del árbol binario de búsqueda.
     *
     * @param data el elemento que se desea eliminar del árbol
     * @throws ExceptionIsEmpty si el árbol está vacío
     * @throws ExceptionItemNotFound si el elemento no se encuentra en el árbol
     */
    @Override
    public void delete(E data) throws ExceptionIsEmpty, ExceptionItemNotFound {
        if(this.isEmpty()) 
            throw new ExceptionIsEmpty("Arbol vacio");

        this.root = deleteRecursivo(this.root,data);
        

        
    }

    
    //Metodo auxiliar - Recursivo

    /**
     * Método auxiliar recursivo que elimina un nodo del árbol binario de búsqueda.
     *
     * <p>Busca el nodo con el valor proporcionado y lo elimina siguiendo las reglas del
     * BST:</p>
     * <ul>
     *   <li>Si el nodo es una hoja, simplemente se elimina.</li>
     *   <li>Si el nodo tiene un solo hijo, se reemplaza por su hijo.</li>
     *   <li>Si el nodo tiene dos hijos, se reemplaza por el nodo con el menor valor del subárbol derecho
     *       , y luego se elimina ese sucesor del subárbol derecho.</li>
     * </ul>
     *
     * @param rootSub el nodo raíz del subárbol actual
     * @param data el valor a eliminar
     * @return la nueva raíz del subárbol después de la eliminación
     * @throws ExceptionItemNotFound si el nodo con el valor no se encuentra
     */
    private NodeTree<E> deleteRecursivo(NodeTree<E> rootSub,E data) throws ExceptionItemNotFound {
        if(rootSub == null) 
            throw new ExceptionItemNotFound("Item no encontrado");

        int cmp = data.compareTo(rootSub.getData());

        if(cmp < 0) {
            rootSub.setLeft(deleteRecursivo(rootSub.getLeft(), data));
        }else if(cmp > 0) {
            rootSub.setRight(deleteRecursivo(rootSub.getRight(), data));
        }else {
            //Se encontro el nodo

            //Caso 1: Es un nodo hoja
            if(rootSub.getLeft() == null && rootSub.getRight() == null) {
                return null;
            }

            //Caso 2: Nodo con un solo hijo
            if(rootSub.getRight() == null) {
                return rootSub.getLeft();
            }

            if(rootSub.getLeft() == null) {
                return rootSub.getRight();
            }

            //caso 3: Nodo con dos hijos

            NodeTree<E> minSubRight = this.getNodeMin(rootSub.getRight());
            rootSub.setRight(deleteRecursivo(rootSub.getRight(), minSubRight.getData()));
            
            minSubRight.setLeft(rootSub.getLeft());
            minSubRight.setRight(rootSub.getRight());

            return minSubRight;
        }

        return rootSub;
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

    /**
     * Elimina todos los nodos del árbol binario de búsqueda.
     * 
     * <p>Este método destruye todo el árbol asignando {@code null} a la raíz,
     * lo que permite que el recolector de basura de Java libere la memoria
     * ocupada por los nodos, siempre que no existan referencias externas a ellos.</p>
     *
     * @throws ExceptionIsEmpty si el árbol ya está vacío
     */
    @Override
    public void destroyNodes() throws ExceptionIsEmpty {
        if(this.isEmpty()) throw new ExceptionIsEmpty("Arbol vacío");

        this.root = null;
    }

    /**
     * Cuenta la cantidad de nodos no-hoja (nodos internos) en el árbol binario de búsqueda.
     *
     * <p>Un nodo no-hoja es aquel que tiene al menos un hijo. Este método hace uso de una
     * función auxiliar recursiva para recorrer el árbol y contar sólo los nodos que cumplen
     * esa condición.</p>
     *
     * @return el número total de nodos no-hoja en el árbol
     */
    @Override
    public int countAllNodes() {
        return countNodes(this.root);
    }

    //Metodo recursivo para contar los nodos
    private int countNodes(NodeTree<E> node) {
        if(node == null || 
                node.getLeft() == null && node.getRight() == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight()); 
    }

    @Override
    public int height(E subRoot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'height'");
    }

    @Override
    public int amplitude(int level) throws IllegalArgumentException {
        if(level < 0) throw new IllegalArgumentException("El nivel no puede ser negativo");

        return -1;
    }


    //Representación en cadena de texto del arbol
    @Override
    public String toString(){
        return "[ " + this.inOrden(this.root) + "]";
    }

    //Metodos Privados de uso auxiliar

    //--------------------------Recorridos
    private StringBuilder inOrden(NodeTree<E> node) {
        
        StringBuilder sb = new StringBuilder();

        if (node != null) {
            sb.append(inOrden(node.getLeft()));         // Subárbol izquierdo
            sb.append(node.getData()).append(" ");      // Nodo actual
            sb.append(inOrden(node.getRight()));        // Subárbol derecho
        }
        return sb;
    }

    @SuppressWarnings("unused")
    private StringBuilder preOrden(NodeTree<E> node) {
        
        StringBuilder sb = new StringBuilder();

        if (node != null) {
            sb.append(node.getData()).append(" ");      // Nodo actual
            sb.append(preOrden(node.getLeft()));         // Subárbol izquierdo
            sb.append(preOrden(node.getRight()));        // Subárbol derecho
        }
        return sb;
    }

    @SuppressWarnings("unused")
    private StringBuilder postOrden(NodeTree<E> node) {
        
        StringBuilder sb = new StringBuilder();

        if (node != null) {
            sb.append(postOrden(node.getLeft()));         // Subárbol izquierdo
            sb.append(postOrden(node.getRight()));        // Subárbol derecho
            sb.append(node.getData()).append(" ");      // Nodo actual
        }
        return sb;
    }


    //Funciones auxiliares
    //Obtiene el nodo con menor valor en un subarbol
    private NodeTree<E> getNodeMin(NodeTree<E> rootSub) {
        NodeTree<E> actual = rootSub;

        while(actual.getLeft() != null) {
            actual = actual.getLeft();
        }

        return actual;
    }

    //Obtiene el nodo con mayor valor en un subarbol
    @SuppressWarnings("unused")
    private NodeTree<E> getNodeMax(NodeTree<E> rootSub) {
        NodeTree<E> actual = rootSub;

        while(actual.getRight() != null) {
            actual = actual.getRight();
        }

        return actual;
    }
}
