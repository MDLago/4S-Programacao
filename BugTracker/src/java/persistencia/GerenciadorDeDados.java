package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bug;

public class GerenciadorDeDados {

    private Connection criaConexao() throws SQLException{
        String url = "jdbc:mysql://localhost/test";
        String user = "root";
        String pass = "";
        
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(url, user, pass);
    }
    
    public List<Bug> getBugs(String filtro) throws SQLException {

        List<Bug> bugs = new ArrayList<>();

       Connection conexao = criaConexao();
       String sql = "Select * From bugs";
       if(filtro != null){
           sql += filtro;
       }
       PreparedStatement st = conexao.prepareCall(sql);
       
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            boolean status = rs.getBoolean("status");
            
            Bug novoBug = new Bug(id, titulo, status);
            bugs.add(novoBug);
        }
        
        return bugs;//retorna a lista de bugs
    }

    public void cadastraNovoBug(String titulo, String descricao) throws SQLException {
        Connection c = criaConexao();
        String sql = "INSERT INTO bugs (titulo,status) VALUES(?,?)";
        PreparedStatement ps = c.prepareCall(sql);
        
        ps.setString(1, titulo);
        ps.setBoolean(2, false);
        
        ps.execute();
    }

    public void alterarStatusDoBug(int idDoBug, boolean resolvido) throws SQLException {
        Connection c = criaConexao();
        String sql = "UPDATE bugs SET status=? WHERE id=?";
        PreparedStatement ps = c.prepareCall(sql);
        
        ps.setBoolean(1, resolvido);
        ps.setInt(2, idDoBug);
        
        ps.execute();
    }

    public List<Bug> getBugsResolvidos() throws SQLException {
        String filtro = " WHERE bugs.status=true";
        return getBugs(filtro);
    }

    public List<Bug> getBugsNaoResolvidos() throws SQLException {
        String filtro = " WHERE bugs.status=false";
        return getBugs(filtro);
    }

    /**
     * *
     * Este é um metodo de conveniência que apenas chama o método getBugs(String
     * filtro) sem passar nenhum filtro. Este método vai retornar todos os bugs
     * cadastrados.
     */
    public List<Bug> getBugs() throws SQLException {
        return getBugs(null);
    }

}
