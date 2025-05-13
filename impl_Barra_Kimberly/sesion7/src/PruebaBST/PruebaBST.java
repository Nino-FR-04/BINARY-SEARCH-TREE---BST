package PruebaBST;

import bstreelinklistinterfgeneric.LinkedBST;
import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNoFound;

public class PruebaBST{
    public static void main(String[] args) throws ExceptionIsEmpty, ItemNoFound {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        try {
            arbol.insert(40);
            arbol.insert(20);
            arbol.insert(60);
            arbol.insert(10);
            arbol.insert(30);
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(15);
            arbol.insert(41);
            arbol.insert(9);

            System.out.println("arbol:");
            arbol.mostrarArbol();

            arbol.mostrarInOrden();
            arbol.mostrarPreOrden();
            arbol.mostrarPostOrden();

        } catch (ItemDuplicated e) {
            System.out.println("error: " + e.getMessage());
        }
        
        try {
            System.out.println("valor min: " + arbol.obtenerMinimo());
            System.out.println("valor max: " + arbol.obtenerMaximo());
        } catch (ItemNoFound e) {
            System.out.println("error al obtener mínimo o máximo: " + e.getMessage());
        }
        try {
            int valorBuscado = 45;
            System.out.println("¿existe el valor " + valorBuscado + "? " + arbol.search(valorBuscado));
        } catch (ItemNoFound e) {
            System.out.println("No se encontró el valor: " + e.getMessage());
        }

        try {
            int valorEliminar = 40;
            System.out.println("eliminando valor: " + valorEliminar);
            arbol.delete(valorEliminar);

            System.out.println("arbol después de eliminar " + valorEliminar + ":");
            arbol.mostrarArbol();

            arbol.mostrarInOrden();
            arbol.mostrarPreOrden();
            arbol.mostrarPostOrden();
        } catch (ExceptionIsEmpty e) {
            System.out.println("error: " + e.getMessage());
        }

    }
}