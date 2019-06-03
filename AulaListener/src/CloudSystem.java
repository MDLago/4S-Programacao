
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 5586658
 */
public class CloudSystem {
    private CloudSystemListener listener = null;
    
    public void setListener(CloudSystemListener l){
        listener = l;
    }
    
    public Collection<String> listAll(){
        listener.listAllStarted();
        Collection<String> files = new ArrayList<>();
        
        files.add("teste.txt");
        files.add("teste.avi");
        
        sleep();
        
        listener.listAllFinished(files);
        return files;
    }
    
    private void sleep(){
        try {
            int rand = (int)(Math.random() * 4000);
            Thread.sleep(1000 + rand);
        } catch (InterruptedException ex) {
            Logger.getLogger(CloudSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
