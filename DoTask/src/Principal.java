
import br.univali.cc.s4.prog.m1.dotask.apresentacao.TelaPrincipal;
import br.univali.cc.s4.prog.m1.dotask.dominio.Scheduler;
import javax.swing.UIManager;

/**
 *
 * @author 5586658
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Scheduler scheduler = new Scheduler();
        
        scheduler.atualizador();
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
        
    }
    
}
