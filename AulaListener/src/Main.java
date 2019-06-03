
import java.util.Collection;


public class Main {

    public static void main(String[] args) {
        CloudSystem cs = new CloudSystem();
        
        cs.setListener(new CloudSystemListener() {
            @Override
            public void listAllStarted() {
                System.out.println("Started");
            }

            @Override
            public void listAllFinished(Collection<String> files) {
                System.out.println(files);
            }
        });
        
        Collection<String> files = cs.listAll();
        
        /*for (String file : files) {
            System.out.println(file);
        }*/
    }
    
}
