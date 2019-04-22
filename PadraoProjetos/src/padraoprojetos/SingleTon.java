/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padraoprojetos;

/**
 *
 * @author 5586658
 */
public class SingleTon {

    private static SingleTon instancia;
    
    public static SingleTon getInstance() throws InterruptedException{
        if(instancia == null){
            instancia = new SingleTon();
        }
        return instancia;
    }
    
    private SingleTon() throws InterruptedException {
        System.out.println("Criando...");
        Thread.sleep(5000);
        System.out.println("Criação finalizada...");
    }
    
}
