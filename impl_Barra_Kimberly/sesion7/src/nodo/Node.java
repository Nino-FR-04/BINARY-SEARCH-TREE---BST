package nodo;

public class Node<E> {
	public E data;
    public Node<E> left;
    public Node<E> right;

    public Node(E data) {
        this(data, null, null);
    }

    public Node(E data, Node<E> left, Node<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}
