package bstreelinklistinterfgeneric;

import queue.QueueLink;
import bstreeInterface.BinarySearchTree;
import exceptions.ItemDuplicated;
import exceptions.ItemNoFound;
import nodo.Node;
import exceptions.ExceptionIsEmpty;


public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private Node<E> root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node<E> insert(Node<E> node, E data) throws ItemDuplicated {
        if (node == null) {
            return new Node<E>(data);
        }
        int cmp = data.compareTo(node.data);
        if (cmp == 0) {
            throw new ItemDuplicated("el dato '" + data + "' ya existe en el arbol.");
        } else if (cmp < 0) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        return node;
    }

    @Override
    public E search(E data) throws ItemNoFound {
        Node<E> result = search(root, data);
        if (result == null) {
            throw new ItemNoFound("el dato '" + data + "' no fue encontrado.");
        }
        return result.data;
    }

    private Node<E> search(Node<E> node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp == 0) return node;
        else if (cmp < 0) return search(node.left, data);
        else return search(node.right, data);
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) throw new ExceptionIsEmpty("el arbol está vaco.");
        root = delete(root, data);
    }

    private Node<E> delete(Node<E> node, E data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(node.left, data);
        } else if (cmp > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node<E> min = findMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    private Node<E> findMin(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root);
        return sb.toString().trim();
    }

    public void mostrarInOrden() {
        System.out.print("InOrden: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void mostrarPreOrden() {
        System.out.print("PreOrden: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void mostrarPostOrden() {
        System.out.print("PostOrden: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void mostrarArbol() {
        mostrarArbol(root, 0);
    }

    private void mostrarArbol(Node<E> node, int nivel) {
        if (node != null) {
            mostrarArbol(node.right, nivel + 1);
            for (int i = 0; i < nivel; i++) System.out.print("    ");
            System.out.println(node.data);
            mostrarArbol(node.left, nivel + 1);
        }
    }
    @SuppressWarnings("unused")
	private E findMinNode(Node<E> node) throws ItemNoFound {
        if (node == null) throw new ItemNoFound("subarbol vacío, no hay minimo.");
        while (node.left != null) {
            node = node.left;
        }
        return search(node.data); 
    }

    @SuppressWarnings("unused")
	private E findMaxNode(Node<E> node) throws ItemNoFound {
        if (node == null) throw new ItemNoFound("subarbol vacío, no hay maximo.");
        while (node.right != null) {
            node = node.right;
        }
        return search(node.data); 
    }
    public E obtenerMinimo() throws ItemNoFound {
        return findMinNode(root);
    }

    public E obtenerMaximo() throws ItemNoFound {
        return findMaxNode(root);
    }
 // a. Eliminar todos los nodos
    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        root = null; // se eliminan todos los nodos al perder la referencia
    }

    // b. Contar nodos NO hoja (internos)
    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0; // si es hoja
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);
    }

    // c. Es el mismo método
    public int countNodes() {
        return countAllNodes();
    }

 // d. Altura del subárbol con raíz que tenga valor x, iterativamente
    public int height(E x) {
    	 // Buscar el nodo con el valor x de manera iterativa
        Node<E> subraiz = buscarIterativo(x);


        // Si el nodo no existe, retornamos -1
        if (subraiz == null) {
            return -1;
        }

        // Llamada recursiva para calcular la altura
        return alturaRecursiva(subraiz);
    }

    // Método auxiliar recursivo para calcular la altura
    private int alturaRecursiva(Node<E> nodo) {
    	if (nodo == null) {
            return -1;  // Un nodo nulo no contribuye a la altura, así que retornamos -1
        }

        // Mostrar el valor del nodo actual

        // Calculamos la altura de los subárboles izquierdo y derecho
        int alturaIzquierda = alturaRecursiva(nodo.left);
        int alturaDerecha = alturaRecursiva(nodo.right);

        // La altura de un nodo es el máximo de las alturas de los subárboles + 1 (por el nodo actual)
        int altura = Math.max(alturaIzquierda, alturaDerecha) + 1;
        return altura;
    }


    private Node<E> buscarIterativo(E x) {
        Node<E> actual = root;
        while (actual != null) {
            int cmp = x.compareTo(actual.data);
            if (cmp == 0) return actual;
            else if (cmp < 0) actual = actual.left;
            else actual = actual.right;
        }
        return null;
    }

    // e. Amplitud o número de nodos en un nivel específico
    public int amplitude(int nivelObjetivo) {
        if (root == null) return 0;

        QueueLink<Node<E>> cola = new QueueLink<>();
        cola.enqueue(root);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantidad = cola.size(); // Este método ya debe existir

            if (nivel == nivelObjetivo) {
                return cantidad;
            }

            for (int i = 0; i < cantidad; i++) {
                try {
                    Node<E> actual = cola.dequeue();
                    if (actual.left != null) cola.enqueue(actual.left);
                    if (actual.right != null) cola.enqueue(actual.right);
                } catch (ExceptionIsEmpty e) {
                    e.printStackTrace();
                }
            }

            nivel++;
        }

        return 0;
    }

    
}
