
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 5586658
 */
public interface CloudSystemListener {
    
    void listAllStarted();
    void listAllFinished(Collection<String> files);
    /*void listAllError();
    
    void downloadStarted(String filePath);
    void downloadFinished(String filePath);
    void downloadError();*/
}
