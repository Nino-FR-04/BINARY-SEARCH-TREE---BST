package impl_Flores_Nino;

import impl_Flores_Nino.BinarySearchTree.LinkedBST;
import impl_Flores_Nino.ClasesAuxiliares.QueueLink;
import impl_Flores_Nino.Nodes.NodeTree;

public class Main {
    public static void main(String[] args) {
        LinkedBST<Integer> ar = new LinkedBST<>();

        System.out.println("Vacio: " + ar);

        ar.insert(10);
        ar.insert(5);
        ar.insert(20);
        ar.insert(3);
        ar.insert(7);
        ar.insert(25);


        System.out.println("h: " + ar.height(100));

    }
}
