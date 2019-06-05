/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenacaocomstrategy;

/**
 *
 * @author 5586658
 */
public class HeapSort implements Ordenador{

    @Override
    public void ordena(int[] array) {
        ordenaComHearSort(array, AlgoritmosDeOrdenacao.ORDEM_CRESCENTE);
    }
    
    private void ordenaComHearSort(int[] array, int ordem) {
        //build maxheap
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
        
        int n = array.length;

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, i, 0);
            maxHeapify(array, 0, --n);
        }
    }

    private void maxHeapify(int[] array, int pos, int n) {
        int max = 2 * pos + 1, right = max + 1;
        if (max < n) {
            if (right < n && array[max] < array[right]) {
                max = right;
            }
            if (array[max] > array[pos]) {
                swap(array, max, pos);
                maxHeapify(array, max, n);
            }
        }
    }

    private void swap(int[] array, int j, int aposJ) {
        int aux = 0;
        aux = array[j];
        array[j] = array[aposJ];
        array[aposJ] = aux;
    }
}
