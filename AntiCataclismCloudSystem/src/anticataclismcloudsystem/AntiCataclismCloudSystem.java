package anticataclismcloudsystem;

import java.util.Collection;

public class AntiCataclismCloudSystem {

    private final CloudService service;

    public AntiCataclismCloudSystem() {
        service = CloudServiceFactory.createService();
    }
    
    
    
    public static void main(String[] args) {
        AntiCataclismCloudSystem system = new AntiCataclismCloudSystem();
        system.listFiles();
        
        system.listFiles().forEach((file) -> {
            System.out.println(file);
        });
        
        system.upload("test/teste.txt");
    }
    
    public Collection<String> listFiles(){
        return service.listFiles();
    }
    
    public void upload(String filePath){
        service.uploadFile(filePath);
    }
}
