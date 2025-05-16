package impl_Flores_Nino;

import impl_Flores_Nino.BinarySearchTree.LinkedBST;

public class Main {

    public static <E extends Comparable<E>> boolean sameArea(LinkedBST<E> ar1, LinkedBST<E> ar2) {
        return ar1.areaBST() == ar2.areaBST();
    }


    public static void main(String[] args) {
        
        LinkedBST<Integer> ar1 = new LinkedBST<>();
        LinkedBST<Integer> ar2 = new LinkedBST<>();

        //Insercion

        for(int i=0;i<10;i++) {
            ar1.insert(i);
            ar2.insert(i);
        }

        System.out.println("Tienen misma area? : " + sameArea(ar1, ar2));

        //Insercion en ar2
        ar2.insert(25);
        ar2.insert(24);

        System.out.println("Tienen misma area? : " + sameArea(ar1, ar2));

        ar1.destroyNodes();

        System.out.println("Tienen misma area? : " + sameArea(ar1, ar2));

    }
}
