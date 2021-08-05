package controller;

import conexao.FabricaDeConexoes;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class UsuarioDAO {
    
    public void create(Usuario user) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        
        try {
            sql = con.prepareStatement("INSERT INTO usuarios (nome, login, senha) VALUES(?,?,?)");
            sql.setString(1, user.getNome());
            sql.setString(2, user.getLogin());
            sql.setString(3, user.getSenha());
            
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar usuário: " + ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
    }
    
    public List<Usuario> read() {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        ResultSet rs;

        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            sql = con.prepareStatement("SELECT * FROM usuarios");
            rs = sql.executeQuery();
            
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                
                usuarios.add(user);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
        return usuarios;
    }
    
    public List<Usuario> readLogin(String nome) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        ResultSet rs;

        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            sql = con.prepareStatement("SELECT * FROM usuarios WHERE login LIKE ?");
            sql.setString(1, "%"+nome+"%");
            
            rs = sql.executeQuery();
            
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                
                usuarios.add(user);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
        return usuarios;
    }
    
    public void update(Usuario user) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        
        try {
            sql = con.prepareStatement("UPDATE usuarios SET nome = ?, login = ?, senha = ? WHERE id = ?");
            sql.setString(1, user.getNome());
            sql.setString(2, user.getLogin());
            sql.setString(3, user.getSenha());
            sql.setInt(4, user.getId());
            
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
    }
    
    public void delete(Usuario user) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        
        try {
            sql = con.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            sql.setInt(1, user.getId());

            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
    }
}
