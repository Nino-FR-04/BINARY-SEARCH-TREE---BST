package impl_Flores_Nino.Nodes;

/**
 * Clase que representa el nodo de un BST este contiene el valor
 * a guardar y referencias a otros dos nodos.
 * @param E valor generico que representa el valor a guardar.
 */

public class Node <E> {
    
    //Atributos
    private E data;
    private Node<E> right;
    private Node<E> left;

    //Constructor
    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    //Getters y Setters
    public E getData() {return this.data;}
    public Node<E> getLeft() {return this.left;}
    public Node<E> getRight() {return this.right;}

    public void setLeft(Node<E> left) {this.left = left;}
    public void setRight(Node<E> right) {this.right = right;}

}