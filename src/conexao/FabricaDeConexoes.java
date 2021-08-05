package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class FabricaDeConexoes {
    
    private static final String URL = "jdbc:mysql://localhost:3306/controle_de_vendas";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    public static Connection iniciarConexao() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão:", ex);
        }
    }
    
    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricaDeConexoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
