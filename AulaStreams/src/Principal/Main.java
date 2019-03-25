package Principal;

import M1.dominio.Backup;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    
    /*Antes de utilizar*/
    public static void main(String[] args)  {
        
        /*Local do arquivo a ser lido*/
        String localArquivo = "f50mb";
        
        try {
            /*Utilizando o BufferedStream*/
            InputStream entrada = new BufferedInputStream(new FileInputStream(localArquivo));
            OutputStream saida = new BufferedOutputStream(new FileOutputStream("saida"));
            
            /*Utilizando o FileStream*/
            //InputStream entrada =new FileInputStream(localArquivo);
            //OutputStream saida = new FileOutputStream("saida");
            
            Backup backupeador = new Backup();
            
            long tempo = backupeador.copiar(entrada, saida);
            
            System.out.println("Tempo de execução em ms: "+ tempo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
