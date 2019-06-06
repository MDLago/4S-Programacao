/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aulathreads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author 5586658
 */
public class AulaThreads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()-> {
                int contador =0;

                while(true){
                    contador++;
                    System.out.println(Thread.currentThread().getName()+ " "+contador);
                }    
            });
        
        t1.start();
        
        }
        
        
        System.out.println("Finalizando Thread Principal!");
    }
    
}
