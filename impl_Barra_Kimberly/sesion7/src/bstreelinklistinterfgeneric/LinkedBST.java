package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNoFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node insert(Node node, E data) throws ItemDuplicated {
        if (node == null) {
            return new Node(data);
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
        Node result = search(root, data);
        if (result == null) {
            throw new ItemNoFound("el dato '" + data + "' no fue encontrado.");
        }
        return result.data;
    }

    private Node search(Node node, E data) {
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

    private Node delete(Node node, E data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(node.left, data);
        } else if (cmp > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node min = findMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    private Node findMin(Node node) {
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

    private void inOrder(Node node) {
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

    private void preOrder(Node node) {
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

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void mostrarArbol() {
        mostrarArbol(root, 0);
    }

    private void mostrarArbol(Node node, int nivel) {
        if (node != null) {
            mostrarArbol(node.right, nivel + 1);
            for (int i = 0; i < nivel; i++) System.out.print("    ");
            System.out.println(node.data);
            mostrarArbol(node.left, nivel + 1);
        }
    }
    @SuppressWarnings("unused")
	private E findMinNode(Node node) throws ItemNoFound {
        if (node == null) throw new ItemNoFound("subarbol vacío, no hay minimo.");
        while (node.left != null) {
            node = node.left;
        }
        return search(node.data); 
    }

    @SuppressWarnings("unused")
	private E findMaxNode(Node node) throws ItemNoFound {
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
    
}
