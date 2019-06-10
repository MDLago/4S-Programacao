package br.univali;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class DownloadTask implements Runnable{

    private final String url;

    private final List<DownloaderListener> listeners;

    public DownloadTask(String url) {
        this.url = url;
        listeners = new ArrayList<>();
    }

    public void addListener(DownloaderListener l) {
        listeners.add(l);
    }

    @Override
    public void run() {
        try {
            URL link = new URL(url);
            URLConnection connection = link.openConnection();
            long totalSize = connection.getContentLengthLong();
            long downloaded = 0;
            InputStream input = new BufferedInputStream(link.openStream());
            for (DownloaderListener listener : listeners) {
                listener.downloadStarted(url, totalSize);
            }
            while (downloaded < totalSize) {
                if (input.read() != -1) {
                    downloaded++;
                }
                if (downloaded % (totalSize / 100) == 0) { //a cada 1% notifica os listeners
                    for (DownloaderListener listener : listeners) {
                        listener.downloadProgress(totalSize, downloaded);
                    }
                }
            }
            for (DownloaderListener listener : listeners) {
                listener.downloadFinished();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
