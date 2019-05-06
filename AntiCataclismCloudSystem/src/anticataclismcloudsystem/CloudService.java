package anticataclismcloudsystem;

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
public interface CloudService {
    
    Collection<String> listFiles();
    
    void uploadFile(String filepath);
    
    
}
