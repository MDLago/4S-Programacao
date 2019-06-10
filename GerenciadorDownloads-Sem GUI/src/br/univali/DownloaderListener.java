package br.univali;

public interface DownloaderListener {

    void downloadStarted(String link, long totalSize);
    void downloadFinished();
    void downloadProgress(long totalSize, long downloadedBytes);
    
}
