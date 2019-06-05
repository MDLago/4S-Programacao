package ordenacaocomstrategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Ordenador ordenador = new HeapSort();
        
        int array[] = {3,1,2};
        
        ordenador.ordena(array);
        
        for (int i : array) {
            System.out.println(i);
        }
    }
}
