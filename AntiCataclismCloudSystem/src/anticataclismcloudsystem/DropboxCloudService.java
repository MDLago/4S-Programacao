package anticataclismcloudsystem;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class DropboxCloudService implements CloudService {

    private DbxClientV2 client;
    private final String ACCESS_TOKEN = "";

    public DropboxCloudService() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }
    
    @Override
    public Collection<String> listFiles() {
        Collection<String> files = new ArrayList<>();
        try {
            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    files.add(metadata.getPathLower());
                }

                if (!result.getHasMore()) {
                    break;
                }

                result = client.files().listFolderContinue(result.getCursor());
            }
        } catch (DbxException ex) {
            ex.printStackTrace();
        }
        return files;
    }

    @Override
    public void uploadFile(String filepath) {
        try {
            InputStream in = new FileInputStream(filepath);
            client.files().uploadBuilder("/"+filepath).uploadAndFinish(in);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
    
}
