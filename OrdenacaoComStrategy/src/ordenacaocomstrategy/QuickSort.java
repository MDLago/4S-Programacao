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
public class QuickSort implements Ordenador{

    @Override
    public void ordena(int[] array) {
        executaQuick(array, AlgoritmosDeOrdenacao.ORDEM_CRESCENTE);
    }
    
    public void executaQuick(int[] array, int ordem) {
        this.executaQuickSort(array, 0, array.length - 1);
    }

    private void executaQuickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int temp = this.particiona(array, inicio, fim);
            this.executaQuickSort(array, inicio, temp - 1);
            this.executaQuickSort(array, temp + 1, fim);
        }
    }

    private int particiona(int[] array, int inicio, int fim) {
        int pivo = array[inicio];
        boolean esq = true;
        while (inicio < fim) {
            if (esq) {
                if (array[fim] <= pivo) {
                    array[inicio] = array[fim];
                    inicio++;
                    esq = false;
                } else {
                    fim--;
                }
            } else {
                if (array[inicio] > pivo) {
                    array[fim] = array[inicio];
                    fim--;
                    esq = true;
                } else {
                    inicio++;
                }
            }
        }
        array[inicio] = pivo;
        return inicio;
    }
    
}
