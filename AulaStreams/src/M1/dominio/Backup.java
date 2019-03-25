/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M1.dominio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author marco
 */
public class Backup {

    /**
     * Retorno um long com o tempo de execução do programa.
     * @param entrada
     * @param saida
     * @return
     * @throws IOException
     */
    public long copiar(InputStream entrada, OutputStream saida) throws IOException{
        
        long tempoInicio = System.currentTimeMillis();
        
        int byteLido = -1;
            do {
                
                byteLido = entrada.read();
                
                if (byteLido != -1)
                    saida.write(byteLido);
            }
            while(byteLido != -1);
            
            long tempoFinal = System.currentTimeMillis();   
            saida.close();
            
            return tempoFinal-tempoInicio;
    }
    
    
}
