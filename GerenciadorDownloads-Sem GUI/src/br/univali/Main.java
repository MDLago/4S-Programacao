package br.univali;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) {

        Map<String, String> links = new HashMap<>();
        links.put("FireFox 1", "https://download-installer.cdn.mozilla.net/pub/firefox/releases/46.0.1/win32/pt-BR/Firefox%20Setup%2046.0.1.exe");
        links.put("FireFox 2", "https://download-installer.cdn.mozilla.net/pub/firefox/releases/46.0.1/win32/pt-BR/Firefox%20Setup%2046.0.1.exe");
        links.put("FireFox 3", "https://download-installer.cdn.mozilla.net/pub/firefox/releases/46.0.1/win32/pt-BR/Firefox%20Setup%2046.0.1.exe");

        //cria uma tarefa de download para cada link
        for (String nomeDoPrograma : links.keySet()) {
            String link = links.get(nomeDoPrograma);
            DownloadTask tarefaDownload = new DownloadTask(link);
            tarefaDownload.addListener(new Listener(nomeDoPrograma));
            
            Thread t = new Thread(tarefaDownload);
            t.start();
        }

        System.out.println("DOWNLOADS FINALIZADOS!");
    }

    
    
    private static class Listener implements DownloaderListener {

        private final String nomeDoPrograma;

        public Listener(String nomeDoPrograma) {
            this.nomeDoPrograma = nomeDoPrograma;
        }
        
        @Override
        public void downloadStarted(String link, long totalSize) {
            System.out.println("Iniciando download do " + nomeDoPrograma);
        }

        @Override
        public void downloadFinished() {
            System.out.println("Download do " + nomeDoPrograma + " FINALIZADO!");
        }

        @Override
        public void downloadProgress(long totalSize, long downloadedBytes) {
            double progresso = (double)downloadedBytes/totalSize * 100f;
            System.out.println(nomeDoPrograma + " " + ((int)progresso) + "%");
        }

    }

}
