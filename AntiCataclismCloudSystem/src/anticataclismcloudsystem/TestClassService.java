package anticataclismcloudsystem;

import java.util.ArrayList;
import java.util.Collection;

public class TestClassService implements CloudService{

    @Override
    public Collection<String> listFiles() {
        Collection<String> files = new ArrayList<>();
        files.add("Teste.txt");
        return files;
    }

    @Override
    public void uploadFile(String filepath) {
        System.out.println("Uploading " + filepath);
    }
    
}
