package anticataclismcloudsystem;

public class CloudServiceFactory {
    
    public static CloudService createService(){
        return new DropboxCloudService();
    }
}
