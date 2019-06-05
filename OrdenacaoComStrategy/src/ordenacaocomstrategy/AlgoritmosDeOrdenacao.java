package ordenacaocomstrategy;

public class AlgoritmosDeOrdenacao {

    public static final int ORDEM_CRESCENTE = 10;
    public static final int ORDEM_DECRESCENTE = 20;
    
    //HEAP SORT
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    //SHELL SORT
    //+++++++++++++++++++++++++++++++++
    private boolean continuaIterando(int[] array, int j, int valorQueSeraMovido, int ordem){
        boolean ordemCrescenteOk = valorQueSeraMovido < array[j];
        boolean ordemDecrescenteOk = valorQueSeraMovido >= array[j];
        return (ordemCrescenteOk && (ordem == ORDEM_CRESCENTE) ) || (ordemDecrescenteOk && (ordem == ORDEM_DECRESCENTE));
    }

    public void executaOrdenacao(int[] array, int ordem) {
        int i, j, h = 1;
        int valorQueSeraMovido;
        int tamanhoDoArray = array.length;
        do {
            h = 3 * h + 1;
        } while (h < tamanhoDoArray);
        do {
            h /= 3;
            for (i = h; i < tamanhoDoArray; i++) {
                valorQueSeraMovido = array[i];
                j = i - h;
                while (  j >= 0 && continuaIterando(array, j, valorQueSeraMovido, ordem) ){//valorQueSeraMovido < this.array[j]) {
                    array[j + h] = array[j];
                    j -= h;
                }
                array[j + h] = valorQueSeraMovido;
            }
        } while (h > 1);
    }
    //+++++++++++++++++++++++++++++++++++++++++
}
