package PruebaBST;

import exceptions.ItemDuplicated;
import exceptions.ItemNoFound;
import bstreelinklistinterfgeneric.LinkedBST;
import exceptions.ExceptionIsEmpty;

public class PruebaBST{
	 public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        try {
            // Insertar elementos
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(60);
            arbol.insert(80);

            // Mostrar árbol
            System.out.println("Árbol en forma estructurada:");
            arbol.mostrarArbol();

            // Recorridos
            arbol.mostrarInOrden();    // 20 30 40 50 60 70 80
            arbol.mostrarPreOrden();   // 50 30 20 40 70 60 80
            arbol.mostrarPostOrden();  // 20 40 30 60 80 70 50

            // Buscar un elemento
            System.out.println("Buscar 40: " + arbol.search(40));

            // Altura del subárbol cuya raíz es 30
            System.out.println("Altura del subárbol con raíz 30: " + arbol.height(30));

            // Amplitud en nivel 2
            System.out.println("Nodos en el nivel 1: " + arbol.amplitude(1));

            // Mínimo y máximo
            System.out.println("Mínimo: " + arbol.obtenerMinimo());
            System.out.println("Máximo: " + arbol.obtenerMaximo());

            // Contar nodos internos
            System.out.println("Nodos internos: " + arbol.countNodes());

            // Eliminar nodo
            arbol.delete(70);
            System.out.println("Árbol luego de eliminar 70:");
            arbol.mostrarArbol();

        } catch (ItemDuplicated | ItemNoFound | ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}